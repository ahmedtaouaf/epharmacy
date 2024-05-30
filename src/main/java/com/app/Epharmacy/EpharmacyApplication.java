package com.app.Epharmacy;


import com.app.Epharmacy.Entity.Commande;
import com.app.Epharmacy.Repository.CommandeRepository;
import com.app.Epharmacy.Services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class EpharmacyApplication implements CommandLineRunner {




    public static void main(String[] args) {

        SpringApplication.run(EpharmacyApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {



    }


}
