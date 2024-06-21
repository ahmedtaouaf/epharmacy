package com.app.Epharmacy.Services;


import com.app.Epharmacy.Entity.ClientInfo;
import com.app.Epharmacy.Repository.ClientInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT id, username, password, role FROM login WHERE username = ?";
        List<UserDetails> users = jdbcTemplate.query(sql, new String[]{username}, (ResultSet rs, int rowNum) -> {
            String role = rs.getString("role");
            return User.withUsername(rs.getString("username"))
                    .password(rs.getString("password"))
                    .roles(role)
                    .build();
        });
        if (users.isEmpty()) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return users.get(0);
    }

    /*@Autowired
    private ClientInfoRepository clientInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ClientInfo clientInfo = clientInfoRepository.findByEmail(email);
        if (clientInfo == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User.builder()
                .username(clientInfo.getEmail())
                .password(clientInfo.getPassword())
                .roles("USER")
                .build();
    }*/


}
