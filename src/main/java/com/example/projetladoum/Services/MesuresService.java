/**
 * Classe MesuresService
 * Since 2021-11-20
 */
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

    public int getGreaterHg(String genre) {
        return  mesuresDao.getGreaterHg(genre);
    }

    public int getGreaterLoi(String genre) {
        return mesuresDao.getGreaterLoi(genre);
    }





    /**
     * this function update the score of the ovine by calculate the mesures of the ovine
     * @param idMesure
     * @return void
     */
    public void updateScore(int idMesure) {
        Ovins ovins = getOvinOfMesures(idMesure);

        int maxHg = getGreaterHg(ovins.getGenre());
        int maxLoi = getGreaterLoi(ovins.getGenre());

        double currentScoreHg;
        double currentScoreLoi;
        double currentScoreLd = 0;
        double currentScorePoils = 0;
        double currentScoreIncurvation = 0;
        double currentScoreCornes = 0;
        double currentScorePattes = 0;
        double currentScorePaturons = 0;
        double currentScoreScrotum = 0;
        double currentScoreQueue = 0;
        double currentOssature = 0;
        double currentMusculature = 0;

        double finalScore = 0;

        if (ovins.getScore() == 0) {
            /**
             * Note obtain by the ovine on his HG(on 44points). To obtain it, we divide 44(the maximal points that we can obtain the HG criterion) then we multiply the all by the Hg of the ovine.
             */
            int mesureHg = ovins.getMesures().getHg();
            int mesureLoi = ovins.getMesures().getLoi();

            currentScoreHg = ((44.0/maxHg)*mesureHg);
            currentScoreLoi = (41.0/maxLoi)*mesureLoi;

            System.out.println(maxHg);
            System.out.println(maxLoi);
            System.out.println(mesureHg);
            System.out.println(mesureLoi);

            System.out.println(currentScoreHg);
            System.out.println(currentScoreLoi);

            //Note obtain by the ovine on his Ld (on 1points)
            switch (ovins.getMesures().getLigneDeDos()) {
                case "ld-ensellee": currentScoreLd=0.25;
                case "ld-peu-ensellee" : currentScoreLd=0.5;
                case "ld-droite-forte": currentScoreLd=1;
            }

            //Note obtain by the ovine on his hair (on 1points)
            switch (ovins.getMesures().getPoils()) {
                case "poils-terne": currentScorePoils=0.25;
                case "poils-propre": currentScorePoils=0.5;
                case "poils-propre-brillant": currentScorePoils=1;
            }

            //Note obtain by the ovine on his incurvation (on 1points)
            switch (ovins.getMesures().getIncurvationTete()) {
                case "incurvation-grignard": currentScoreIncurvation=0.25;
                case "incurvation-moyenne": currentScoreIncurvation=0.5;
                case "incurvation-brusquee": currentScoreIncurvation=0.75;
                case "incurvation-convexe": currentScoreIncurvation=1;
            }


            //Note obtain by the ovine on his hornes (on 1points)
            switch (ovins.getMesures().getCornes()) {
                case "cornes-mince": currentScoreCornes=0.25;
                case "cornes-moyennes": currentScoreCornes=0.5;
                case "cornes-grosses": currentScoreCornes=1;
            }


            //Note obtain by the ovine on his paws(pattes) (on 1points)
            switch (ovins.getMesures().getPattesAvantArriere()) {
                case "pattes-crampees": currentScorePattes=0.25;
                case "pattes-trop-serrees": currentScorePattes=0.5;
                case "pattes-droite-bien-positionnees": currentScorePattes=1;
            }

            //Note obtain by the ovine on his pastern (on 1points)
            switch (ovins.getMesures().getPaturons()) {
                case "paturons-cassees": currentScorePaturons=0.25;
                case "paturons-peu-laches": currentScorePaturons=0.5;
                case "paturons-fort-et-droit": currentScorePaturons=1;
            }


            //Note obtain by the ovine on his scrotum (on 1points)
            switch (ovins.getMesures().getScrotum()) {
                case "scrotum-async": currentScoreScrotum=0.5;
                case "scrotum-sync": currentScoreScrotum=1;
            }


            //Note obtain by the ovine on his queue (on 1points)
            switch (ovins.getMesures().getQueue()) {
                case "queue-courte": currentScoreQueue=0.25;
                case "queue-moyenne": currentScoreQueue=0.5;
                case "queue-bonne": currentScoreQueue=0.75;
                case "queue-bien-longue": currentScoreQueue=1;
            }


            //Note obtain by the ovine on his skeleton (on 1points)
            switch (ovins.getMesures().getOssature()) {
                case "ossature-faible": currentOssature=0.25;
                case "ossature-moyenne": currentOssature=0.5;
                case "ossature-forte": currentOssature=0.75;
                case "ossature-tres-forte": currentOssature=1;
            }


            //Note obtain by the ovine on his musculature (on 1points)
            switch (ovins.getMesures().getMusculature()) {
                case "musculature-faible": currentMusculature=0.25;
                case "musculature-moyenne": currentMusculature=0.5;
                case "musculature-forte": currentMusculature=0.75;
                case "musculature-tres-forte": currentMusculature=1;
            }

            finalScore = currentScoreHg + currentScoreLoi + currentScoreLd + currentScorePoils + currentScoreIncurvation + currentScoreCornes + currentScorePattes + currentScorePaturons + currentScoreScrotum + currentScoreQueue + currentOssature + currentMusculature;

            System.out.println(finalScore);

            ovins.setScore(finalScore);

            ovinsService.updateOvin(ovins, ovins.getId());
        }

        System.out.println(ovins);
    }

}















