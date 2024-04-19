package com.example.rest1.service;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest1.Model.Nerf;
import com.example.rest1.repository.NerfRepository;

@Service
public class NerfService {
    private final NerfRepository nerfRepository;

    @Autowired
    public NerfService(NerfRepository nerfRepository) {
        this.nerfRepository = nerfRepository;
    }

    @Transactional
    public String addNewNerf(String nom, String description, String typeTir, double prix, int quantite, byte[] img) {
        Nerf nerf = new Nerf();
        nerf.setNom(nom);
        nerf.setDescription(description);
        nerf.setTypeTir(typeTir);
        nerf.setPrix(prix);
        nerf.setQuantite(quantite);
        nerf.setImg(img);
        nerfRepository.save(nerf);
        return "saved";
    }

    @Transactional
    public String fullStockNerf(int id, int addedQty) {
        Nerf nerf = nerfRepository.findById(id).orElse(null);
        if(nerf == null){
            return "Nerf not found";
        }
        int currentQty = nerf.getQuantite();
        nerf.setQuantite(currentQty + addedQty);
        nerfRepository.save(nerf);
        return "Changes were applied";
    }

    @Transactional
    public String changerPrixNerf(int id, double newPrice) {
        Nerf nerf = nerfRepository.findById(id).orElse(null);
        if(nerf == null){
            return "Nerf not found";
        }
        nerf.setPrix(newPrice);
        nerfRepository.save(nerf);
        return "Changes were applied";
    }

    @Transactional
    public Iterable<Nerf> getAllNerf() {
        return nerfRepository.findAll();
    }

}
