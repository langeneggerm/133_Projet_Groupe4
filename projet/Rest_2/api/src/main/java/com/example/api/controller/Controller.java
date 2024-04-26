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
    public @ResponseBody String addNewCommande(@RequestParam Date date, @RequestParam int fk_nerf,
            @RequestParam int fk_user, @RequestParam int montant) {
        userService.setSoldeUser(fk_user, montant);
        return commandeService.addNewCommande(date, fk_nerf, fk_user);
    }

    @GetMapping(path = "/getCommande")
    public Iterable<CommandeDTO> getAllCommandes() {
        // This returns a JSON or XML with the users
        return commandeService.findAllCommandes();
    }

    @GetMapping(path = "/getUser")
    public @ResponseBody User getUser(@RequestParam int pk_user) {

        return userService.getUser(pk_user);
    }

    @PostMapping(path = "/changeSolde")
    public @ResponseBody String changerSolde(@RequestParam int pk_user, @RequestParam int montant) {

        return userService.setSoldeUser(pk_user, montant);
    }

    @PostMapping(path = "/login")
    public String postMethodName(@RequestParam int pk_user, @RequestParam String password) {

        boolean verif =  userService.login(pk_user, password);
        if(verif){
            return "Connexion réussi !";
        }else{
            return "La connexion a échoué !";
        }
    }

    @GetMapping(path = "/mdphash")
       public @ResponseBody String getHash( @RequestParam String password) {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
      String hashedPassword = bCryptPasswordEncoder.encode(password);
      return hashedPassword;
      
    }

}
