package com.example.projetladoum.Services;

import com.example.projetladoum.Models.Mesures;
import com.example.projetladoum.Models.Ovins;
import com.example.projetladoum.dao.MesuresDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MesuresService {

    @Autowired
    private MesuresDao mesuresDao;
    @Autowired
    private OvinsService ovinsService;


    public List<Mesures> getAllMesures() {
        return mesuresDao.findAll();
    }

    public Mesures getMesuresById(int id) {
        Mesures existingMesure = mesuresDao.findMesuresById(id);

        if (existingMesure != null)
            return existingMesure;
        return null;
    }

    public Mesures saveMesures(Mesures mesureToSave) {
        return mesuresDao.save(mesureToSave);
    }

    public Mesures updateMesures(Mesures mesureToUpdate, int id) {
        Mesures existingMesure = getMesuresById(id);

        if (existingMesure != null) {
            if (mesureToUpdate.getHg() != 0)
                existingMesure.setHg(mesureToUpdate.getHg());

            if (mesureToUpdate.getLoi() != 0)
                existingMesure.setLoi(mesureToUpdate.getLoi());

            if (mesureToUpdate.getLigneDeDos() != null && !mesureToUpdate.getLigneDeDos().isEmpty())
                existingMesure.setLigneDeDos(mesureToUpdate.getLigneDeDos());

            if (mesureToUpdate.getPoils() != null && !mesureToUpdate.getPoils().isEmpty())
                existingMesure.setPoils(mesureToUpdate.getPoils());

            if (mesureToUpdate.getIncurvationTete() != null && !mesureToUpdate.getIncurvationTete().isEmpty())
                existingMesure.setIncurvationTete(mesureToUpdate.getIncurvationTete());

            if (mesureToUpdate.getCornes() != null && !mesureToUpdate.getCornes().isEmpty())
                existingMesure.setCornes(mesureToUpdate.getCornes());

            if (mesureToUpdate.getPattesAvantArriere() != null && !mesureToUpdate.getPattesAvantArriere().isEmpty())
                existingMesure.setPattesAvantArriere(mesureToUpdate.getPattesAvantArriere());

            if (mesureToUpdate.getPaturons() != null && !mesureToUpdate.getPaturons().isEmpty())
                existingMesure.setPaturons(mesureToUpdate.getPaturons());

            if (mesureToUpdate.getScrotum() != null && !mesureToUpdate.getScrotum().isEmpty())
                existingMesure.setScrotum(mesureToUpdate.getScrotum());

            if (mesureToUpdate.getQueue() != null && !mesureToUpdate.getQueue().isEmpty())
                existingMesure.setQueue(mesureToUpdate.getQueue());

            if (mesureToUpdate.getOssature() != null && !mesureToUpdate.getOssature().isEmpty())
                existingMesure.setOssature(mesureToUpdate.getOssature());

            if (mesureToUpdate.getMusculature() != null && !mesureToUpdate.getMusculature().isEmpty())
                existingMesure.setMusculature(mesureToUpdate.getMusculature());

            Mesures mesureUpdated = mesuresDao.save(existingMesure);

            updateScore(mesureUpdated.getId());

            return mesureUpdated;
        }
        return null;
    }

    public Ovins getOvinOfMesures(int idMesure) {
        return this.mesuresDao.getOvinOfMesures(idMesure);
    }

    public void updateScore(int idMesure) {
        Ovins ovins = getOvinOfMesures(idMesure);

        if (ovins.getScore() == 0) {
            ovins.setScore(95);
            ovinsService.updateOvin(ovins, ovins.getId());
        }

        System.out.println(ovins);
    }

}
