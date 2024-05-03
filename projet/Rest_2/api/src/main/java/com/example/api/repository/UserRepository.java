package com.example.api.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.api.model.User;



// This will be AUTO IMPLEMENTED by Spring into a Bean called SkieurRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Integer> {


     Optional<User> findByUserName(String userName);
}
