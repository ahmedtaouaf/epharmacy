package com.app.Epharmacy.Services;

import com.app.Epharmacy.Entity.CustomUserDetails;
import com.app.Epharmacy.Entity.Login;
import com.app.Epharmacy.Repository.LoginRepo;
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
    private LoginRepo loginRepo;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "SELECT id, username, password, role FROM Login WHERE username = ?";
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
}
