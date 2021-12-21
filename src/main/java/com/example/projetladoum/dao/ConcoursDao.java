package com.example.projetladoum.dao;

import com.example.projetladoum.Models.Concours;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcoursDao extends JpaRepository<Concours, Integer> {
    public List<Concours> findAll();
    public Concours findConcoursById(int id);
}
