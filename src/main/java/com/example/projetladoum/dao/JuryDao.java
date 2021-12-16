package com.example.projetladoum.dao;

import com.example.projetladoum.Models.Jury;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JuryDao extends JpaRepository<Jury, Integer> {
    public Jury findJuryById(int id);
    public Jury findJuryByLogin(String login);
    public Jury findJuryByEmail(String email);
    public List<Jury> findAll();

}
