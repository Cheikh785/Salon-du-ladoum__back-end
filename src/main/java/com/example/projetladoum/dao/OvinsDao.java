package com.example.projetladoum.dao;

import com.example.projetladoum.Models.Candidate;
import com.example.projetladoum.Models.Ovins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OvinsDao extends JpaRepository<Ovins, Integer> {
    public List<Ovins> findAll();
    public Ovins findOvinsById(int id);
    @Query(value = "select candidate from Ovins ovin, Candidate candidate where ovin.id = candidate.ovin.id and ovin.genre = :genre order by ovin.rang asc")
    public List<Candidate> getRangByGenre(@Param("genre") String genre);

    @Query(value = "select ovin.id from Ovins ovin where ovin.genre=:genre")
    public List<Integer> getAllIdOfOvins(@Param("genre") String genre);

    @Query(value = "update Ovins ovin set ovin.score=:score where ovin.id=:id")
    public void adjustScoreByHg(@Param("id") Integer id, @Param("score") Double score);

    @Query(value = "update Ovins ovin set ovin.rang=:rang where ovin.id=:id")
    public void adjustRang(@Param("id") Integer id, @Param("rang") Integer rang);
}






























