package com.example.api.model;

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
@Table (name = "t_user")
public class User {
    
    @Id
    @Column(name = "PK_User", length = 50)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "MDP", length = 100)
    private String mdp;

    @Column(name = "Solde" , length = 50)
    private int solde;

    @Column(name = "UserName", length = 50)
    private String userName;

    @Column(name = "Admin", length = 50)
    private int admin;

    public int getId(){
        return id;
    }

    public void setId(int pk){
        this.id = pk;
    }

    public String getMDP(){
        return mdp;
    }
    
    public void setMDP(String MDP){
        this.mdp = MDP;
    }

    public int getSolde(){
        return solde;
    }

    public void setSolde(int solde){
        this.solde = solde;
    }

    public String getuserName(){
        return userName;
    }

    public void setuserName(String UserName){
this.userName = UserName;
    }

    public int getAdmin(){
        return admin;
    }

    public void setAdmin(int Admin){

        this.admin = Admin;
    }
}

