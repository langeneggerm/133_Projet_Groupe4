package com.example.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity
@Table (name = "t_user")
public class User {
    
    @Id
    @Column(name = "pk_user", length = 50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer pk;

    @Column(name = "mdp", length = 250)
    private String mdp;

    @Column(name = "solde" , length = 50)
    private double solde;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "admin", length = 50)
    private int admin;

    public int getPk(){
        return pk;
    }

    public void setPk(int pk){
        this.pk = pk;
    }

    public String getMDP(){
        return mdp;
    }
    
    public void setMDP(String mdp){
        this.mdp = mdp;
    }

    public double getSolde(){
        return solde;
    }

    public void setSolde(Double solde){
        this.solde = solde;
    }

    public String getuserName(){
        return username;
    }

    public void setuserName(String useruame){
this.username = useruame;
    }

    public int getAdmin(){
        return admin;
    }

    public void setAdmin(int Admin){

        this.admin = Admin;
    }
}

