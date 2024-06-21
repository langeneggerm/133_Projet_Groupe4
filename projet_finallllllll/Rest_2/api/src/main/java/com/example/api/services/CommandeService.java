package com.example.api.services;

import java.sql.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.model.Commande;
import com.example.api.repository.CommandeRepository;
import com.example.api.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class CommandeService {

    private CommandeRepository commandeRepo;
 

    @Autowired
    public CommandeService(CommandeRepository commandeRepo, UserRepository userRepo) {
        this.commandeRepo = commandeRepo;
    
    }

    @Transactional
    public String addNewCommande(Date date, int fk_nerf, int fk_user) {
        Commande newCommande = new Commande();
        newCommande.setDateCommande(date);
        newCommande.setFk_nerf(fk_nerf);
        newCommande.setFk_user(fk_user);

        commandeRepo.save(newCommande);
        return "saved";
    }

    public Iterable<Commande> findAllCommandes(int pk_user) {
       return commandeRepo.findByfkUser(pk_user);
            
    }

}
