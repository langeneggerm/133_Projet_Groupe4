package com.example.api_gw.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class Rest1Service{
    private RestTemplate restTemplate;
    //private final static String restUrl = "http://host.docker.internal:8081/";
    private final static String restUrl = "http://localhost:8081/";
    
    public Rest1Service(){
        restTemplate = new RestTemplate();
    }

    public String getAllNerf() {
        String jsonResponse = restTemplate.getForObject(restUrl + "getAllNerf", String.class);
        return jsonResponse;
    }
    //trouver comment faire pour envoyer un body avec restTemplate

    public ResponseEntity<String> getNerf(int id) {
        ResponseEntity<String> jsonResponse = restTemplate.getForEntity(restUrl + "getNerf?id=" + id, String.class);
        return jsonResponse;
    }

    public ResponseEntity<String> fullNerfStock(int id, int addedQty) {
        Map<String, Integer> body = new HashMap<>();
        body.put("id", id);
        body.put("qty", addedQty);
        ResponseEntity<String> jsonResponse = restTemplate.postForEntity(restUrl + "newStock", body, String.class);
        return jsonResponse;
    }

    public ResponseEntity<String> addNewNerf(String nom, String description, String typeTir, double prix, int quantite, byte[] img) {
        Map<String, Object> body = new HashMap<>();
        body.put("nom", nom);
        body.put("description", description);
        body.put("typeTir", typeTir);
        body.put("prix", prix);
        body.put("quantite", quantite);
        body.put("img", img);
        ResponseEntity<String> jsonResponse = restTemplate.postForEntity(restUrl + "addNerf", body, String.class);
        return jsonResponse;
    }

    public ResponseEntity<String> changerPrixNerf(int id, double newPrice) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("prix", newPrice);
        ResponseEntity<String> jsonResponse = restTemplate.postForEntity(restUrl + "newPrice", body, String.class);
        return jsonResponse;
    }

    public ResponseEntity<String> sellOne(int id) {
        Map<String, Integer> body = new HashMap<>();
        body.put("id", id);
        ResponseEntity<String> jsonResponse = restTemplate.postForEntity(restUrl + "sellOne", body, String.class);
        return jsonResponse;
    }



}