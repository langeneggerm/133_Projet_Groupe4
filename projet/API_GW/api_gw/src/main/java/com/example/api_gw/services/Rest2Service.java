package com.example.api_gw.services;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class Rest2Service{
    private final RestTemplate restTemplate = new RestTemplate();
    //private final static String restUrl = "http://host.docker.internal:8082"; //à changer quand mis en place
    private final static String restUrl = "http://rest-2:8080"; //à changer quand mis en place
    
    //test
    public Rest2Service(){
    }

    public ResponseEntity<String> addNewCommande(Date date, int idNerf, int user, double prix) {
        Map<String, Object> body = new HashMap<>();
        body.put("date", date);
        body.put("fk_nerf", idNerf);
        body.put("fk_user", idNerf);
        body.put("montant", prix);
        ResponseEntity<String> jsonResponse = restTemplate.postForEntity(restUrl + "/acheterNerf", body, String.class);
        return jsonResponse;
    }
    //trouver comment faire pour envoyer un body avec restTemplate

    public ResponseEntity<String> getUser(int id) {
        ResponseEntity<String> jsonResponse = restTemplate.getForEntity(restUrl + "/getUser?pk_user=" + id, String.class);
        return jsonResponse;
    }

    public ResponseEntity<String> changerSolde(int id, double prix) {
        Map<String, Object> body = new HashMap<>();
        body.put("pk_user", id);
        body.put("montant", prix);
        ResponseEntity<String> jsonResponse = restTemplate.postForEntity(restUrl + "/changeSolde", body, String.class);
        return jsonResponse;
    }

    public ResponseEntity<String> changerPrixNerf(int id, double newPrice) {
        Map<String, Object> body = new HashMap<>();
        body.put("id", id);
        body.put("prix", newPrice);
        ResponseEntity<String> jsonResponse = restTemplate.postForEntity(restUrl + "/newPrice", body, String.class);
        return jsonResponse;
    }

    public ResponseEntity<String> findAllCommandes(int id) {
        Map<String, Integer> body = new HashMap<>();
        body.put("pk_user", id);
        ResponseEntity<String> jsonResponse = restTemplate.postForEntity(restUrl + "/getCommande", body, String.class);
        return jsonResponse;
    }

    public ResponseEntity<String> login(String username, String mdp) {
        Map<String, String> body = new HashMap<>();
        body.put("username", username);
        body.put("password", mdp);
        ResponseEntity<String> jsonResponse = restTemplate.postForEntity(restUrl + "/login", body, String.class);
        return jsonResponse;
    }

    public ResponseEntity<String> getHash(String mdp) {
        Map<String, String> body = new HashMap<>();
        body.put("password", mdp);
        ResponseEntity<String> jsonResponse = restTemplate.postForEntity(restUrl + "/mdphash", body, String.class);
        return jsonResponse;
    }

    



}