package com.example.rest1.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_nerfs")
public class Nerf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PK_Nerf")
    private Integer id;
    
    @Column(nullable = false, name = "Nom", length = 100)
    private String nom;

    @Column(nullable = false, name = "Description", length = 200)
    private String description;

    @Column(nullable = false, name = "TypeTir", length = 45)
    private String typeTir;

    @Column(nullable = false, name = "Prix")
    private double prix;

    @Column(nullable = false, name = "Quantite")
    private Integer quantite;

    @Lob
    @Column(nullable = true, name = "Img", columnDefinition="LONGBLOB")
    private byte[] img;

    // Getters et setters pour id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters et setters pour nom
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    // Getters et setters pour description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Getters et setters pour typeTir
    public String getTypeTir() {
        return typeTir;
    }

    public void setTypeTir(String typeTir) {
        this.typeTir = typeTir;
    }

    // Getters et setters pour prix
    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    // Getters et setters pour quantite
    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    // Getters et setters pour img
    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String toString(){
        return this.nom + " - " + this.prix + " CHF";
    }

}