package com.app.Epharmacy.Controllers;

import com.app.Epharmacy.Repository.CommandeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CommandeController {

    @Autowired
    private CommandeRepository commandeRepository;



}
