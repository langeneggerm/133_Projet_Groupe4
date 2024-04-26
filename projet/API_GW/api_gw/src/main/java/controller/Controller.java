package controller;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.api_gw.services.Rest1Service;
import com.example.api_gw.services.Rest2Service;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.util.JSONPObject;

import jakarta.servlet.http.HttpSession;


@RestController
public class Controller {

    private final Rest1Service rest1;
    private final Rest2Service rest2;
    
    @Autowired
    public Controller(Rest1Service rest1, Rest2Service rest2){
        this.rest1 = rest1;
        this.rest2 = rest2;
    };

    @GetMapping("/getAllNerf")
    public @ResponseBody String getAllNerf() {
        return rest1.getAllNerf();
    }

    @GetMapping("/getNerf")
    public @ResponseBody ResponseEntity<String> getNerf(@RequestParam int id) {
        return rest1.getNerf(id);
    }

    @PutMapping("/newStock")
    public @ResponseBody ResponseEntity<String> fullStock(HttpSession session, @RequestParam int id, @RequestParam int addedQty) {
        ResponseEntity<String> result = null;
        if(session.getAttribute("login") != null){
            if(session.getAttribute("isAdm").equals(1)){
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
    public @ResponseBody ResponseEntity<String> addNerf(HttpSession session, @RequestParam String nom, @RequestParam String description, @RequestParam String typeTir, @RequestParam double prix, @RequestParam int quantite, @RequestParam byte[] img) {
        ResponseEntity<String> result = null;
        if(session.getAttribute("login") != null){
            if(session.getAttribute("isAdm").equals(1)){
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
    public @ResponseBody ResponseEntity<String> changerPrix(HttpSession session, @RequestParam int id, @RequestParam double prix) {
        ResponseEntity<String> result = null;
        if(session.getAttribute("login") != null){
            if(session.getAttribute("isAdm").equals(1)){
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
    public @ResponseBody ResponseEntity<String> achatNerf(HttpSession session, @RequestParam Date date, @RequestParam int idNerf, @RequestParam int idUser, @RequestParam double prix) {
        ResponseEntity<String> result = null;
        if(session.getAttribute("login") != null){
                String json = rest2.getUser(idUser).getBody();
                try {
                    JSONObject jsonObject = new JSONObject(json);
                    String soldeJson = jsonObject.getString("solde");
                    double solde = Double.parseDouble(soldeJson);
                    if(solde >= prix){
                        
                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        } else {
            HttpStatus httpCode = HttpStatus.UNAUTHORIZED;
            result = new ResponseEntity<>("You are not logged in. Try again when logged!", httpCode);
        }
        return result;
    }



}