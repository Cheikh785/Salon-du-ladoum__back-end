package com.example.projetladoum.dao;

import com.example.projetladoum.Models.Mesures;
import com.example.projetladoum.Models.Ovins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MesuresDao extends JpaRepository<Mesures, Integer> {
    public List<Mesures> findAll();
    public Mesures findMesuresById(int id);
    @Query(value = "select ovin from Ovins ovin, Mesures mesure where ovin.mesures.id = mesure .id and mesure.id=:idMesure")
    public Ovins getOvinOfMesures(@Param("idMesure") int idMesure);
}
