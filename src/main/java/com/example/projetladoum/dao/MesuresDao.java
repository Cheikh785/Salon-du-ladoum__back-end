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

    @Query(value = "select mesure.hg from Mesures mesure, Ovins ovin where ovin.mesures.id=mesure.id and ovin.genre=:genre and mesure.hg >= all(select m.hg from Mesures m, Ovins o where o.mesures.id=m.id and o.genre=:genre)")
    public int getGreaterHg(@Param("genre") String genre);

    @Query(value = "select mesure.loi from Mesures mesure, Ovins ovin where ovin.mesures.id=mesure.id and ovin.genre=:genre and mesure.loi >= all(select m.loi from Mesures m, Ovins o where o.mesures.id=m.id and o.genre=:genre)")
    public int getGreaterLoi(@Param("genre") String genre);

}
