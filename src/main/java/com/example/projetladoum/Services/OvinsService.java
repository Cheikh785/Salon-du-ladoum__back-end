package com.example.projetladoum.Services;

import com.example.projetladoum.Models.Candidate;
import com.example.projetladoum.Models.Mesures;
import com.example.projetladoum.Models.Ovins;
import com.example.projetladoum.dao.OvinsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OvinsService {

    @Autowired
    private OvinsDao ovinsDao;
    @Autowired
    private MesuresService mesuresService;

    public List<Ovins> getAllOvins() {
        return ovinsDao.findAll();
    }



    public Ovins getOvinsById(int id) {
        Ovins existingOvin = ovinsDao.findOvinsById(id);

        if (existingOvin != null)
            return existingOvin;
        return null;
    }



    public Ovins registerOvin(Ovins ovinToRegister) {
        return ovinsDao.save(ovinToRegister);
    }



    public Ovins updateOvin(Ovins ovinToUpdate, int id) {
        Ovins existingOvin = getOvinsById(id);

        if (existingOvin != null) {
            if (ovinToUpdate.getNomOvin() != "" && ovinToUpdate.getNomOvin() != null)
                existingOvin.setNomOvin(ovinToUpdate.getNomOvin());

            if (ovinToUpdate.getAge() != 0)
                existingOvin.setAge(ovinToUpdate.getAge());

            if (ovinToUpdate.getNbDents() != 0)
                existingOvin.setNbDents(ovinToUpdate.getNbDents());

            if (ovinToUpdate.getGenre() != "" && ovinToUpdate.getGenre() != null)
                existingOvin.setGenre(ovinToUpdate.getGenre());

            if (ovinToUpdate.getScore() != 0)
                existingOvin.setScore(ovinToUpdate.getScore());

            if (ovinToUpdate.getRang() != 0)
                existingOvin.setRang(ovinToUpdate.getRang());

            if (ovinToUpdate.getMesures() != null) {
                Mesures mesureUpdated = mesuresService.updateMesures(ovinToUpdate.getMesures(), existingOvin.getMesures().getId());
                existingOvin.setMesures(mesureUpdated);
            }

            Ovins ovinUpadated = registerOvin(existingOvin);

            return ovinUpadated;
        }
        return null;
    }



    public int deleteOvin(int id) {
        Ovins existingOvin = getOvinsById(id);

        if (existingOvin != null) {
            ovinsDao.delete(existingOvin);
            return id;
        }
        return -1;
    }


    public List<Candidate> getScoreByGenre(String genre) {
        return this.ovinsDao.getRangByGenre(genre);
    }



}
















