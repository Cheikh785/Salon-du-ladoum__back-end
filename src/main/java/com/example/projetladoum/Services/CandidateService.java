package com.example.projetladoum.Services;

import com.example.projetladoum.Models.Candidate;
import com.example.projetladoum.Models.Mesures;
import com.example.projetladoum.Models.Ovins;
import com.example.projetladoum.dao.CandidateDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {

    @Autowired
    private CandidateDao candidateDao;
    @Autowired
    private OvinsService ovinsService;


    //Get all the candidate
    public List<Candidate> getAllCandidate() {
        return candidateDao.findAll();
    }

    //Get a candidate by id
    public Candidate getCandidateById(int id) {
        return candidateDao.findCandidateById(id);
    }

    //Regiister a candidate
    public Candidate registerCandidate(Candidate candidate) {
        Mesures mesures = new Mesures();
        candidate.getOvin().setMesures(mesures);
        System.out.println(candidate.getOvin().getMesures().getId() + candidate.getOvin().getMesures().getMusculature());
        return candidateDao.save(candidate);
    }

    //Update candidate
    public Candidate updateCandidate(Candidate candidateToUpdate, int id) {
        Candidate existingCandidate = getCandidateById(id);

        if(existingCandidate != null) {
            if (candidateToUpdate.getPrenoms() != "" && candidateToUpdate.getPrenoms() != null)
                existingCandidate.setPrenoms(candidateToUpdate.getPrenoms());

            if (candidateToUpdate.getNom() != "" && candidateToUpdate.getNom() != null  )
                existingCandidate.setNom(candidateToUpdate.getNom());

            if (candidateToUpdate.getEmail() != "" && candidateToUpdate.getEmail() != null  )
                existingCandidate.setEmail(candidateToUpdate.getEmail());

            if (candidateToUpdate.getAdresse() != "" && candidateToUpdate.getAdresse() != null)
                existingCandidate.setAdresse(candidateToUpdate.getAdresse());

            if (candidateToUpdate.getBirth() != null)
                existingCandidate.setBirth(candidateToUpdate.getBirth());

            if (candidateToUpdate.getTelephone() != "" && candidateToUpdate.getTelephone() != null)
                existingCandidate.setTelephone(candidateToUpdate.getTelephone());

            if (candidateToUpdate.getOvin() != null) {
                Ovins ovinUpdated = ovinsService.updateOvin(candidateToUpdate.getOvin(), existingCandidate.getOvin().getId());
                existingCandidate.setOvin(ovinUpdated);
            }

            Candidate candidateUpdated = registerCandidate(existingCandidate);

            return candidateUpdated;
        }
        return null;
    }

    public int deleteCandidate(int id) {
        Candidate existingCandidate = getCandidateById(id);

        if (existingCandidate != null) {
            candidateDao.delete(existingCandidate);
            return id;
        } else
            return -1;
    }


    public List<Object[]> getCandidateByTypeOvin(String type) {
        return candidateDao.findCandidateByType(type);
    }



    public Mesures getMesureOfOvinOfCandidate(int id) {
        Mesures mesure = candidateDao.findMesureOfOvinOfCandidate(id);
        if (mesure != null)
            return mesure;
        return null;
    }
}
