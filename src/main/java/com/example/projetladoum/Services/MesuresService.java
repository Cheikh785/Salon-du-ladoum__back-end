/**
 * Classe MesuresService
 * Since 2021-11-20
 */
package com.example.projetladoum.Services;

import com.example.projetladoum.Models.Mesures;
import com.example.projetladoum.Models.Ovins;
import com.example.projetladoum.dao.MesuresDao;
import com.example.projetladoum.dao.OvinsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MesuresService {

    @Autowired
    private MesuresDao mesuresDao;

    @Autowired
    private OvinsService ovinsService;

    @Autowired
    private OvinsDao ovinsDao;


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

            updateScore(existingMesure.getId());

            Mesures mesureUpdated = mesuresDao.save(existingMesure);

//            updateScore(mesureUpdated.getId());

            return mesureUpdated;
        }
        return null;
    }


    public Ovins getOvinOfMesures(int idMesure) {
        return this.mesuresDao.getOvinOfMesures(idMesure);
    }

    public Double getGreaterHg(String genre) {
        return mesuresDao.getGreaterHg(genre);
    }

    public Double getGreaterLoi(String genre) {
        return mesuresDao.getGreaterLoi(genre);
    }


    /**
     * this function update the score of the ovine by calculate the mesures of the ovine
     *
     * @param idMesure
     * @return void
     */
    public void updateScore(int idMesure) {
        Ovins ovins = getOvinOfMesures(idMesure);

        Double maxHg = getGreaterHg(ovins.getGenre());
        Double maxLoi = getGreaterLoi(ovins.getGenre());

        Double currentScoreHg;
        Double currentScoreLoi;


        double finalScore = 0.0;
        int finalRang = 0;

        if (ovins.getScore() == 0) {
            /**
             * Note obtain by the ovine on his HG(on 44points). To obtain it, we divide 44(the maximal points that we can obtain the HG criterion) then we multiply the all by the Hg of the ovine.
             */
            Double mesureHg = ovins.getMesures().getHg();
            Double mesureLoi = ovins.getMesures().getLoi();

//            System.out.println("Mesures Hg : "+mesureHg + "\n Max Hg : "+maxHg);
//            System.out.println("Mesures Loi : "+mesureLoi + "\n Max loi : "+maxLoi);
            if (mesureHg <= maxHg) {
                currentScoreHg = ((44.0 / maxHg) * mesureHg);
            } else {
                maxHg = mesureHg;
                currentScoreHg = 44.0;
                adjustScoreByHg(maxHg, ovins.getGenre());
//                System.out.println("On est dans le else");
            }
//            System.out.println("Current Hg : "+currentScoreHg);

            if (mesureLoi <= maxLoi) {
                currentScoreLoi = (41.0 / maxLoi) * mesureLoi;
            } else {
                maxLoi = mesureLoi;
                currentScoreLoi = 41.0;
                adjustScoreByLoi(maxLoi, ovins.getGenre());
//                System.out.println("On est dans le else");
            }
//            System.out.println("Current Loi : "+currentScoreLoi);

            List<Double> listEsthetiqueScoreOfOvins = getScoreOfEsthetiqueMesures(ovins);
            Double sumEsthetiqueScores = 0.0;

            for (int i=0; i<listEsthetiqueScoreOfOvins.size(); i++)
                sumEsthetiqueScores += listEsthetiqueScoreOfOvins.get(i);

            finalScore = currentScoreHg + currentScoreLoi + sumEsthetiqueScores;

//            System.out.println("Score final : "+finalScore);

            ovins.setScore(finalScore);
//            ovinsService.updateOvin(ovins, ovins.getId()); // Crée une boucle infinie, pareceque que dans **updateOvin**, on fait appel à **updateMesures** qui lui à son tour fait appel à cette fontion(**updateScore**)
            ovinsDao.save(ovins);

            List<Double> listScore = mesuresDao.getScoreByDesc(ovins.getGenre());
//            for (int i=0; i<listScore.size(); i++)
//                System.out.println(listScore.get(i));

            finalRang = getRangOfOvine(listScore, finalScore);
//            System.out.println("Rang final : "+finalRang);

            ovins.setRang(finalRang);
//            ovinsService.updateOvin(ovins, ovins.getId());
            ovinsDao.save(ovins);
        }

//        System.out.println(ovins);
    }


    public int getRangOfOvine(List<Double> listScore, double score) {
        List<Integer> listRang = new ArrayList<>();
        int rang = 1;

        if (!listScore.isEmpty()) {
            int i=0; int k=1;
            while (i<listScore.size()-1) {
                if (listScore.get(i) != listScore.get(i+1)) {
                    listRang.add(k);
                    k++;
                }else {
                    if (listScore.get(i) == listScore.get(i+1))
                        listRang.add(k);
                }
                i++;
            }
            listRang.add(k);

            int j=0;
            while (j<listScore.size()-1 && listScore.get(j) != score)
                j=j+1;
            rang = listRang.get(j);
        }

        return rang;
    }



    public void adjustScoreByHg(Double maxHg, String genre) {
        List<Integer> listId = ovinsDao.getAllIdOfOvins(genre);

        for (int i=0; i<listId.size(); i++) {
            Ovins ovin = ovinsDao.findOvinsById(listId.get(i));
            Double mesureHg = ovin.getMesures().getHg();
            Double mesureLoi = ovin.getMesures().getLoi();
            Double newScoreHg = (44/maxHg)*mesureHg;

            List<Double> listEsthetiqueScores = getScoreOfEsthetiqueMesures(ovin);
            Double sumEsthetiqueScores = 0.0;
            for (int j=0; j<listEsthetiqueScores.size(); j++)
                    sumEsthetiqueScores += listEsthetiqueScores.get(j);

            Double maxLoi = getGreaterLoi(ovin.getGenre());
            mesureLoi = (41/maxLoi) * mesureLoi;

            Double newFinalScore = 0.0;
            newFinalScore = newScoreHg + mesureLoi + sumEsthetiqueScores;

//            System.out.print(i+1 + " => Score avant Hg: "+ovin.getScore() + "\t Score Après Hg: "+newFinalScore+ "\n\n");
//            ovinsDao.adjustScoreByHg(listId.get(i), newFinalScore);
            ovin.setScore(newFinalScore);
            ovinsDao.save(ovin);
        }

        for (int k=0; k<listId.size(); k++) {
            Ovins ovin = ovinsDao.findOvinsById(listId.get(k));
            List<Double> listScore = mesuresDao.getScoreByDesc(ovin.getGenre());

            int newRang = getRangOfOvine(listScore, ovin.getScore());

//            System.out.println(k+1 + " => Rang avant Hg: "+ovin.getRang() + "\t Rang Après Hg: "+newRang+"\n\n");
//            ovinsDao.adjustRang(listId.get(k), newRang);
            ovin.setRang(newRang);
            ovinsDao.save(ovin);
        }
    }

    public void adjustScoreByLoi(Double maxLoi, String genre) {
        List<Integer> listId = ovinsDao.getAllIdOfOvins(genre);

        int i=0;
        while (i < listId.size()) {
            Ovins ovins = ovinsDao.findOvinsById(listId.get(i));
            Double mesureLoi = ovins.getMesures().getLoi();
            Double mesureHg = ovins.getMesures().getHg();
            Double newScoreLoi = (41.0 / maxLoi) * mesureLoi;

            List<Double> listEsthetiqueScores = getScoreOfEsthetiqueMesures(ovins);
            Double sumEsthetiqueScores = 0.0;
            for (int j = 0; j < listEsthetiqueScores.size(); j++)
                sumEsthetiqueScores += listEsthetiqueScores.get(j);

            Double maxHg = getGreaterHg(ovins.getGenre());
            mesureHg = (44/maxHg) * mesureHg;

            Double newFinalScore = 0.0;
            newFinalScore = newScoreLoi + mesureHg + sumEsthetiqueScores;

//            ovinsDao.adjustScoreByHg(listId.get(i), newFinalScore);
            ovins.setScore(newFinalScore);
            ovinsDao.save(ovins);
            i++;
        }

        int l=0;
        while (l < listId.size()) {
            Ovins ovins = ovinsDao.findOvinsById(listId.get(l));
            List<Double> listOfScore = mesuresDao.getScoreByDesc(ovins.getGenre());

            int rang = getRangOfOvine(listOfScore, ovins.getScore());

//            ovinsDao.adjustRang(listId.get(l), rang);
            ovins.setRang(rang);
            ovinsDao.save(ovins);
            l++;
        }
    }










    public List<Double> getScoreOfEsthetiqueMesures(Ovins ovins) {
        List<Double> listEsthetiqueScore = new ArrayList<>();

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

        //Note obtain by the ovine on his Ld (on 1points)
        switch (ovins.getMesures().getLigneDeDos()) {
            case "ld-ensellee":
                currentScoreLd = 0.25;
                break;
            case "ld-peu-ensellee":
                currentScoreLd = 0.5;
                break;
            case "ld-droite-forte":
                currentScoreLd = 1;
                break;
            default: currentScoreLd = 0; break;
        }

        //Note obtain by the ovine on his hair (on 1points)
        switch (ovins.getMesures().getPoils()) {
            case "poils-terne":
                currentScorePoils = 0.25;
                break;
            case "poils-propre":
                currentScorePoils = 0.5;
                break;
            case "poils-propre-brillant":
                currentScorePoils = 1;
                break;
            default: currentScorePoils = 0; break;
        }

        //Note obtain by the ovine on his incurvation (on 1points)
        switch (ovins.getMesures().getIncurvationTete()) {
            case "incurvation-grignard":
                currentScoreIncurvation = 0.25;
                break;
            case "incurvation-moyenne":
                currentScoreIncurvation = 0.5;
                break;
            case "incurvation-brusquee":
                currentScoreIncurvation = 0.75;
                break;
            case "incurvation-convexe":
                currentScoreIncurvation = 1;
                break;
            default: currentScoreIncurvation = 0; break;
        }


        //Note obtain by the ovine on his hornes (on 1points)
        switch (ovins.getMesures().getCornes()) {
            case "cornes-mince":
                currentScoreCornes = 0.25;
                break;
            case "cornes-moyennes":
                currentScoreCornes = 0.5;
                break;
            case "cornes-grosses":
                currentScoreCornes = 1;
                break;
            default: currentScoreCornes = 0; break;
        }


        //Note obtain by the ovine on his paws(pattes) (on 1points)
        switch (ovins.getMesures().getPattesAvantArriere()) {
            case "pattes-crampees":
                currentScorePattes = 0.25;
                break;
            case "pattes-trop-serrees":
                currentScorePattes = 0.5;
                break;
            case "pattes-droite-bien-positionnees":
                currentScorePattes = 1;
                break;
            default: currentScorePattes = 0; break;
        }

        //Note obtain by the ovine on his pastern (on 1points)
        switch (ovins.getMesures().getPaturons()) {
            case "paturons-cassees":
                currentScorePaturons = 0.25;
                break;
            case "paturons-peu-laches":
                currentScorePaturons = 0.5;
                break;
            case "paturons-fort-et-droit":
                currentScorePaturons = 1;
                break;
            default: currentScorePaturons = 0; break;
        }


        //Note obtain by the ovine on his scrotum (on 1points)
        switch (ovins.getMesures().getScrotum()) {
            case "scrotum-async":
                currentScoreScrotum = 0.5;
                break;
            case "scrotum-sync":
                currentScoreScrotum = 1;
                break;
            default: currentScoreScrotum = 0; break;
        }


        //Note obtain by the ovine on his queue (on 1points)
        switch (ovins.getMesures().getQueue()) {
            case "queue-courte":
                currentScoreQueue = 0.25;
                break;
            case "queue-moyenne":
                currentScoreQueue = 0.5;
                break;
            case "queue-bonne":
                currentScoreQueue = 0.75;
                break;
            case "queue-bien-longue":
                currentScoreQueue = 1;
                break;
            default: currentScoreQueue = 0; break;
        }


        //Note obtain by the ovine on his skeleton (on 1points)
        switch (ovins.getMesures().getOssature()) {
            case "ossature-faible":
                currentOssature = 0.25;
                break;
            case "ossature-moyenne":
                currentOssature = 0.5;
                break;
            case "ossature-forte":
                currentOssature = 0.75;
                break;
            case "ossature-tres-forte":
                currentOssature = 1;
                break;
            default: currentOssature = 0; break;
        }


        //Note obtain by the ovine on his musculature (on 1points)
        switch (ovins.getMesures().getMusculature()) {
            case "musculature-faible":
                currentMusculature = 0.25;
                break;
            case "musculature-moyenne":
                currentMusculature = 0.5;
                break;
            case "musculature-forte":
                currentMusculature = 0.75;
                break;
            case "musculature-tres-forte":
                currentMusculature = 1;
                break;
            default: currentMusculature = 0; break;
        }

        listEsthetiqueScore.add(currentScoreLd);
        listEsthetiqueScore.add(currentScorePoils);
        listEsthetiqueScore.add(currentScoreIncurvation);
        listEsthetiqueScore.add(currentScoreCornes);
        listEsthetiqueScore.add(currentScorePattes);
        listEsthetiqueScore.add(currentScorePaturons);
        listEsthetiqueScore.add(currentScoreScrotum);
        listEsthetiqueScore.add(currentScoreQueue);
        listEsthetiqueScore.add(currentOssature);
        listEsthetiqueScore.add(currentMusculature);


        return  listEsthetiqueScore;
    }


}















