package com.example.api.controller;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.CommandeDTO;
import com.example.api.services.CommandeService;
import com.example.api.services.UserService;

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
        return "";
    }

        @PostMapping(path = "/addCommande")
    public @ResponseBody String addNewCommande(@RequestParam Date date, @RequestParam int fk_nerf, @RequestParam int fk_user) {
        return commandeService.addNewCommande(date,fk_nerf,fk_user);
    }

    @GetMapping(path = "/getCommande")
    public @ResponseBody Iterable<CommandeDTO> getAllCommandes() {
        // This returns a JSON or XML with the users
        return commandeService.findAllCommandes();
    }

}
