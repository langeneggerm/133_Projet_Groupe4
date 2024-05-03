package com.example.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.api.model.User;
import com.example.api.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

    private UserRepository UserRepo;

    @Autowired
    public UserService(UserRepository UserRepo) {
        this.UserRepo = UserRepo;
    }

    @Transactional
    public String addNewUser(String mdp, Double solde, String userName, int admin) {
        User newUser = new User();
        newUser.setMDP(mdp);
        newUser.setSolde(solde);
        newUser.setuserName(userName);
        newUser.setAdmin(admin);

        UserRepo.save(newUser);
        return "saved";
    }

    @Transactional
    public boolean  setSoldeUser(int pk_user, double montant) {
        User newUser = new User();
boolean verif = false;
        Optional<User> optionalUser = UserRepo.findById(pk_user);
        if (optionalUser.isPresent()) {
            newUser = optionalUser.get();
            double newSolde = newUser.getSolde() + montant;
            if (newSolde >= 0) {
                newUser.setSolde(newSolde);

                UserRepo.save(newUser);
                verif = true;
            }
        }
        return verif ;
    }

    @Transactional

    public User getUser(int pk_user) {
        User newUser = new User();

        Optional<User> optionalUser = UserRepo.findById(pk_user);
        if (optionalUser.isPresent()) {
            newUser = optionalUser.get();
            return newUser;
        }
        return null;
    }

    @Transactional
    public int login(String nom_user, String password) {
        int reponse = -1;
        User newUser = new User();

        Optional<User> optionalUser = UserRepo.findByUserName(nom_user);
        if (optionalUser.isPresent()) {
            newUser = optionalUser.get();
            String UserPassword = newUser.getMDP();
            BCryptPasswordEncoder decodeur = new BCryptPasswordEncoder();

            // verifier password en comparant les hash
            if (decodeur.matches(password, UserPassword)) {
                reponse = newUser.getId();
            }
        }
        return reponse;

    }

}