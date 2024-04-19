package com.example.api.model;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


@Entity
@Table (name = "t_commande")
public class Commande {
    
    @Id
    @Column(name = "PK_Commande", length = 50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "DateCommande", length = 50)
    private Date DateCommande;

    @Column(name = "FK_Nerf")
    private int fk_nerf;

    @Column(name = "FK_User")
    private int fk_user;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateCommande() {
        return this.DateCommande;
    }

    public void setDateCommande(Date DateCommande) {
        this.DateCommande = DateCommande;
    }

    public int getFk_nerf() {
        return this.fk_nerf;
    }

    public void setFk_nerf(int fk_nerf) {
        this.fk_nerf = fk_nerf;
    }

    public int getFk_user() {
        return this.fk_user;
    }

    public void setFk_user(int fk_user) {
        this.fk_user = fk_user;
    }

}
