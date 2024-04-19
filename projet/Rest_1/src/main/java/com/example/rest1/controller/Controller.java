package com.example.rest1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest1.Model.Nerf;
import com.example.rest1.service.NerfService;

@RestController
public class Controller {
    private final NerfService nerfService;

    @Autowired
    public Controller(NerfService nerfService) {
        this.nerfService = nerfService;
    }

    // Handler pour GET
    @GetMapping("/getNerf")
    public @ResponseBody Iterable<Nerf> getNerf() {
        return nerfService.getAllNerf();
    }

    @PostMapping("/addNerf")
    public @ResponseBody String addNerf(@RequestParam String nom, @RequestParam String description, @RequestParam String typeTir, @RequestParam double prix, @RequestParam Integer quantite, @RequestParam byte[] img){
        return nerfService.addNewNerf(nom, description, typeTir, prix, quantite, img);
    }

    @PutMapping("/newStock")
    public @ResponseBody String addStock(@RequestParam Integer id, @RequestParam Integer qty){
        return nerfService.fullStockNerf(id, qty);
    }

    @PutMapping("/newPrice")
    public @ResponseBody String addStock(@RequestParam Integer id, @RequestParam double prix){
        return nerfService.changerPrixNerf(id, prix);
    }

}