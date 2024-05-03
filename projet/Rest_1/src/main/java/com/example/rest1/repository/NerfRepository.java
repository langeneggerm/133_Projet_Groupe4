package com.example.rest1.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.rest1.Model.Nerf;

// This will be AUTO IMPLEMENTED by Spring into a Bean called SkieurRepository
// CRUD refers Create, Read, Update, Delete

public interface NerfRepository extends CrudRepository<Nerf, Integer> {

}