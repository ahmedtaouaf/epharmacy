package com.app.Epharmacy;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EpharmacyApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(EpharmacyApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
       /* Posseder posseder = possederRepository.getLastDiplome("M","70a7dfc9-8492-4047-ba7f-8e4da8c58924");
        Militaire militaire = militaireRepository.findMilitaireById("70a7dfc9-8492-4047-ba7f-8e4da8c58924");
        militaire.setDiplomeMilitaire(posseder.getDiplome());
        diplomeService.attribuerDiplome("70a7dfc9-8492-4047-ba7f-8e4da8c58924",Long.valueOf(8000) , Long.valueOf(2), Long.valueOf(45), null, null);
        militaireService.updateDiplomeMilitaire("70a7dfc9-8492-4047-ba7f-8e4da8c58924");
        System.out.println(militaire.getDiplomeMilitaire());

        */



    }


}
