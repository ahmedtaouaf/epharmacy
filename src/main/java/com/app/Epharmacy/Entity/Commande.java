package com.app.Epharmacy.Entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "`commande`")
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal total;
    private int nbrproduit;
    private boolean status ;

    @ManyToOne
    @JoinColumn(name = "client_info_id")
    private ClientInfo clientInfo;

    @ManyToOne
    @JoinColumn(name = "pharmacie_id")
    private Pharmacie pharmacie;

    private Date orderDate;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    private List<Commandeart> commandearts;


}
