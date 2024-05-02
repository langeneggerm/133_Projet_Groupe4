package com.example.api.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.api.dto.CommandeDTO;
import com.example.api.dto.UserDTO;
import com.example.api.model.User;
import com.example.api.repository.UserRepository;
import com.example.api.services.CommandeService;
import com.example.api.services.UserService;
import com.google.gson.Gson;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class Controller {

    private final UserService userService;
    private final CommandeService commandeService;

    @Autowired
    public Controller(UserService userService, CommandeService commandeService) {
        this.userService = userService;
        this.commandeService = commandeService;
    }

    @GetMapping("/")
    public String getNothing() {
        return "test";
    }

    @PostMapping(path = "/acheterNerf")
    public ResponseEntity<String> addNewCommande(@RequestParam Date date, @RequestParam int fk_nerf,
            @RequestParam int fk_user, @RequestParam int montant) {
       if(userService.setSoldeUser(fk_user, montant)){
       return ResponseEntity.ok(new Gson().toJson(commandeService.addNewCommande(date, fk_nerf, fk_user)));
      
       } else{
        return ResponseEntity.badRequest().body(" erreur lors de l'achat" + "\"}");
       }

    }

    @GetMapping(path = "/getCommandes")
    public ResponseEntity<String> getAllCommandesByUser(@RequestParam int pk_user) {
        // This returns a JSON or XML with the users
        return ResponseEntity.ok(new Gson().toJson(commandeService.findAllCommandes(pk_user)));
    }

    @GetMapping(path = "/getUser")
    public @ResponseBody User getUser(@RequestParam int pk_user) {

        return userService.getUser(pk_user);
    }

    @PostMapping(path = "/changeSolde")
    public ResponseEntity<String> changerSolde(@RequestParam int pk_user, @RequestParam Double montant) {

      if(userService.setSoldeUser(pk_user, pk_user)){
        return ResponseEntity.ok(new Gson().toJson(new Object[]{true}));
      }else{
        return ResponseEntity.badRequest().body("Le montant est trop élevé");
      }
      
    }

    @PostMapping(path = "/login")
    public String login(@RequestParam int pk_user, @RequestParam String password) {

        boolean verif =  userService.login(pk_user, password);
        if(verif){
            return "Connexion réussi !";
        }else{
            return "La connexion a échoué !";
        }
    }

}
