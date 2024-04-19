package com.example.api.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.dto.CommandeDTO;
import com.example.api.model.Commande;
import com.example.api.repository.CommandeRepository;

import jakarta.transaction.Transactional;


@Service
public class CommandeService {
    
    private  CommandeRepository commandeRepo;

    @Autowired
    public CommandeService(CommandeRepository commandeRepo){
        this.commandeRepo = commandeRepo;
    }

    @Transactional
    public String addNewCommande(Date date, int fk_nerf, int fk_user){
       Commande newCommande = new Commande();
       newCommande.setDateCommande(date);
       newCommande.setFk_nerf(fk_nerf);
       newCommande.setFk_user(fk_user);

       commandeRepo.save(newCommande);
        return "saved";  
    }

    public Iterable<CommandeDTO> findAllCommandes(){
        Iterable<Commande> commandes = commandeRepo.findAll();
        List<CommandeDTO> commandesDTO = new ArrayList<>();

        for (Commande commande : commandes){
            CommandeDTO commandeDTO = new CommandeDTO(
            commande.getId(),
            commande.getDateCommande(),
            commande.getFk_nerf(),
            commande.getFk_user()
            );
            commandesDTO.add(commandeDTO);
        }
        return commandesDTO;
    }


}
