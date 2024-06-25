package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Entity.ClientInfo;
import com.app.Epharmacy.Entity.Login;
import com.app.Epharmacy.Repository.ClientInfoRepository;
import com.app.Epharmacy.Repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClientController {

    @Autowired
    private ClientInfoRepository clientInfoRepository;


    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("client", new ClientInfo());
        return "register-client";
    }

    @PostMapping("/saveClient")
    public String saveClient(@ModelAttribute("client") ClientInfo clientInfo) {


        String encryptedPassword = passwordEncoder.encode(clientInfo.getLogin().getPassword());
        clientInfo.getLogin().setPassword(encryptedPassword);
        clientInfo.getLogin().setRole("USER");


        Login login = clientInfo.getLogin();
        login.setClientInfo(clientInfo);
        clientInfo.setLogin(login);

        clientInfoRepository.save(clientInfo);
        return "redirect:/registerSuccess";
    }

    @GetMapping("/registerSuccess")
    public String showRegistrationSuccess() {
        return "register-success";
    }
}
