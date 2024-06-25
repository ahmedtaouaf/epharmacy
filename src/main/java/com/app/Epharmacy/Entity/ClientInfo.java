package com.app.Epharmacy.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ClientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;

    @OneToOne(mappedBy = "clientInfo", cascade = CascadeType.ALL)
    private Login login;



}
