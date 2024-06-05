package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CommandeController {

    @Autowired
    private CommandeRepository commandeRepository;



}
