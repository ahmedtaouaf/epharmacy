package com.app.Epharmacy.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Commandeart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Commande commande;

    private Long medicamentId;

}
