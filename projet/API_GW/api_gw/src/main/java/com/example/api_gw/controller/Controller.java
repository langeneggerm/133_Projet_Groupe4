package com.example.api_gw.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_gw.services.Rest1Service;
import com.example.api_gw.services.Rest2Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;
//test

@RestController
public class Controller {

    private final Rest1Service rest1;
    private final Rest2Service rest2;

    @Autowired
    public Controller() {
        rest1 = new Rest1Service();
        rest2 = new Rest2Service();
    };

    @GetMapping("/")
    public @ResponseBody String base() {
        return "test";
    }

    @GetMapping("/getAllNerf")
    public @ResponseBody String getAllNerf() {
        return rest1.getAllNerf();
    }

    @GetMapping("/getNerf")
    public @ResponseBody ResponseEntity<String> getNerf(@RequestParam int id) {
        return rest1.getNerf(id);
    }

    @PutMapping("/newStock")
    public @ResponseBody ResponseEntity<String> fullStock(HttpSession session, @RequestParam int id,
            @RequestParam int addedQty) {
        ResponseEntity<String> result = null;
        if (session.getAttribute("login") != null) {
            if (session.getAttribute("isAdm").equals(1)) {
                result = rest1.fullNerfStock(id, addedQty);
            } else {
                HttpStatus httpCode = HttpStatus.UNAUTHORIZED;
                result = new ResponseEntity<>("Only administrators are authorized to change the store.", httpCode);
            }
        } else {
            HttpStatus httpCode = HttpStatus.UNAUTHORIZED;
            result = new ResponseEntity<>("You are not logged in. Try again when logged!", httpCode);
        }
        return result;
    }

    @PostMapping("/addNerf")
    public @ResponseBody ResponseEntity<String> addNerf(HttpSession session, @RequestParam String nom,
            @RequestParam String description, @RequestParam String typeTir, @RequestParam double prix,
            @RequestParam int quantite, @RequestParam byte[] img) {
        ResponseEntity<String> result = null;
        if (session.getAttribute("login") != null) {
            if (session.getAttribute("isAdm").equals(1)) {
                result = rest1.addNewNerf(nom, description, typeTir, prix, quantite, img);
            } else {
                HttpStatus httpCode = HttpStatus.UNAUTHORIZED;
                result = new ResponseEntity<>("Only administrators are authorized to change the store.", httpCode);
            }
        } else {
            HttpStatus httpCode = HttpStatus.UNAUTHORIZED;
            result = new ResponseEntity<>("You are not logged in. Try again when logged!", httpCode);
        }
        return result;
    }

    @PutMapping("/newPrice")
    public @ResponseBody ResponseEntity<String> changerPrix(HttpSession session, @RequestParam int id,
            @RequestParam double prix) {
        ResponseEntity<String> result = null;
        if (session.getAttribute("login") != null) {
            if (session.getAttribute("isAdm").equals(1)) {
                result = rest1.changerPrixNerf(id, prix);
            } else {
                HttpStatus httpCode = HttpStatus.UNAUTHORIZED;
                result = new ResponseEntity<>("Only administrators are authorized to change the store.", httpCode);
            }
        } else {
            HttpStatus httpCode = HttpStatus.UNAUTHORIZED;
            result = new ResponseEntity<>("You are not logged in. Try again when logged!", httpCode);
        }
        return result;
    }

    @PostMapping("/achatNerf")
    public @ResponseBody ResponseEntity<String> achatNerf(HttpSession session, @RequestParam Date date,
            @RequestParam int idNerf, @RequestParam int idUser, @RequestParam double prix) {
        ResponseEntity<String> result = null;
        ResponseEntity<String> temp = null;
        if (session.getAttribute("login") != null) {
            double prixNeg = -1 * prix;
            temp = rest2.changerSolde(idUser, prixNeg);
            if (temp.getBody().equals("true")) {
                temp = rest1.sellOne(idNerf);
                if (!(temp.getStatusCode() != HttpStatusCode.valueOf(200))) {
                    Date today = new Date();
                    temp = rest2.addNewCommande(today, idNerf, idUser, prixNeg);
                }
            }
        } else {
            HttpStatus httpCode = HttpStatus.UNAUTHORIZED;
            temp = new ResponseEntity<>("You are not logged in. Try again when logged!", httpCode);
        }
        if (temp.getStatusCode() != HttpStatusCode.valueOf(200)) {
            result = temp;
        } else {
            result = new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        return result;
    }

    @GetMapping("/getCommandes")
    public @ResponseBody ResponseEntity<String> getAllCommandes(HttpSession session) {
        ResponseEntity<String> result = null;
        if (session.getAttribute("login") != null) {
            int idUser = Integer.parseInt(session.getAttribute("idUser").toString());
            result = rest2.findAllCommandes(idUser);
        }
        return result;
    }

    @PostMapping("/login")
    public @ResponseBody ResponseEntity<String> login(HttpSession session, String username, String pwd) {
        ResponseEntity<String> result = null;
        if (session.getAttribute("login") == null) {
            result = rest2.login(username, pwd);
            if (result.getStatusCode().equals(HttpStatus.ACCEPTED)) {
                session.setAttribute("login", "true");
                int bodyToInt = Integer.parseInt(result.getBody());
                ResponseEntity<String> user = rest2.getUser(bodyToInt);
                ObjectMapper map = new ObjectMapper();
                try {
                    JsonNode json = map.readTree(user.getBody());
                    int isAdmin = json.get("admin").asInt();
                    int idUser = json.get("id").asInt();
                    session.setAttribute("idUser", idUser);
                    session.setAttribute("isAdm", isAdmin);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
            }
        } else {
            result = new ResponseEntity<>("You already are logged in. Please logout.", HttpStatus.CONFLICT);
        }
        return result;
    }

    @PostMapping("/logout")
    public @ResponseBody ResponseEntity<String> logout(HttpSession session) {
        ResponseEntity<String> result = null;
        if(session.getAttribute("login") != null){
            session.invalidate();
            result = new ResponseEntity<>("You logged out succesfully.", HttpStatus.ACCEPTED);
        } else {
            result = new ResponseEntity<>("You need to be logged in in order to logout.", HttpStatus.BAD_REQUEST);
        }
        return result;
    }

}