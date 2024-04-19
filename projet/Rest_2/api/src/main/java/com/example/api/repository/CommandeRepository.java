package com.example.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.api.model.Commande;


// This will be AUTO IMPLEMENTED by Spring into a Bean called SkieurRepository
// CRUD refers Create, Read, Update, Delete

public interface CommandeRepository extends CrudRepository<Commande, Integer> {

}