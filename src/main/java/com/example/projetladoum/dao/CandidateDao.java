package com.example.projetladoum.dao;

import com.example.projetladoum.Models.Candidate;
import com.example.projetladoum.Models.Mesures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidateDao extends JpaRepository<Candidate, Integer> {
    public List<Candidate> findAll();
    public Candidate findCandidateById(int id);
    @Query(value = "select candidate.id, candidate.prenoms, candidate.nom, candidate.adresse, ovin.nomOvin, ovin.genre, ovin.score from Candidate candidate, Ovins ovin where candidate.ovin.id = ovin.id and ovin.genre = :type ")
    public List<Object[]> findCandidateByType(@Param("type") String type);

    @Query(value = "select mesure from Candidate candidate, Ovins ovin, Mesures mesure where candidate.id=:id and candidate.ovin.id=ovin.id and ovin.mesures.id=mesure.id")
    public Mesures findMesureOfOvinOfCandidate(@Param("id") int id);
}
