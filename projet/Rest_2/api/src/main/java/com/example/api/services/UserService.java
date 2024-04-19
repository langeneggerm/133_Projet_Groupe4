package com.example.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.api.model.User;
import com.example.api.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {
    
    private  UserRepository UserRepo;

    @Autowired
    public UserService(UserRepository UserRepo){
        this.UserRepo = UserRepo;
    }

    @Transactional
    public String addNewUser(String mdp, int solde, String userName, int admin){
       User newUser = new User();
       newUser.setMDP(mdp);
       newUser.setSolde(solde);
       newUser.setuserName(userName);
       newUser.setAdmin(admin);

       UserRepo.save(newUser);
        return "saved";  
    }


}