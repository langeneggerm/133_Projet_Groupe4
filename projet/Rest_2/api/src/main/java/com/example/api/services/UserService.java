package com.example.api.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
    public String addNewUser(String mdp, int solde, String userName, int admin) {
        User newUser = new User();
        newUser.setMDP(mdp);
        newUser.setSolde(solde);
        newUser.setuserName(userName);
        newUser.setAdmin(admin);

        UserRepo.save(newUser);
        return "saved";
    }

    @Transactional
    public String setSoldeUser(int pk_user, int montant) {
        User newUser = new User();

        Optional<User> optionalUser = UserRepo.findById(pk_user);
        if (optionalUser.isPresent()) {
            newUser = optionalUser.get();
            int newSolde = newUser.getSolde() + montant;
            if (newSolde >= 0) {
                newUser.setSolde(newSolde);

                UserRepo.save(newUser);
                return "Solde changé : " + newSolde;
            }
        }
        return "Solde inchangé";
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
    public String login(int pk_user, String password) {
        User newUser = new User();

        Optional<User> optionalUser = UserRepo.findById(pk_user);
        if (optionalUser.isPresent()) {
            newUser = optionalUser.get();
            String UserPassword = newUser.getMDP();
            // verifier password en comparant les hash
            if (password.equals(UserPassword) ) {
                return "Connection réussie !";
            }
        }
        return "Connection refusée, mot de passe ou nom d'utilisateur incorrect";

    }

}