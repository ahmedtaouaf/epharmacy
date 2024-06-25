package com.app.Epharmacy.Services;


import com.app.Epharmacy.Entity.ClientInfo;
import com.app.Epharmacy.Entity.Login;
import com.app.Epharmacy.Repository.ClientInfoRepository;
import com.app.Epharmacy.Repository.LoginRepository;
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
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Login login = loginRepository.findByUsername(username);

        if (login == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }


        UserDetails userDetails = User.withUsername(login.getUsername())
                .password(login.getPassword())
                .roles(login.getRole())
                .build();

        return userDetails;
    }
}
