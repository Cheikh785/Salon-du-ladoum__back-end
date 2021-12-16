package com.example.projetladoum.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mesures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int hg;
    private int loi;
    private String ligneDeDos;
    private String poils;
    private String incurvationTete;
    private String cornes;
    private String pattesAvantArriere;
    private String paturons;
    private String scrotum;
    private String queue;
    private String ossature;
    private String musculature;

    public Mesures() { }

    public Mesures(int id, int hg, int loi, String ligneDeDos, String poils, String incurvationTete, String cornes, String pattesAvantArriere, String paturons, String scrotum, String queue, String ossature, String musculature) {
        this.id = id;
        this.hg = hg;
        this.loi = loi;
        this.ligneDeDos = ligneDeDos;
        this.poils = poils;
        this.incurvationTete = incurvationTete;
        this.cornes = cornes;
        this.pattesAvantArriere = pattesAvantArriere;
        this.paturons = paturons;
        this.scrotum = scrotum;
        this.queue = queue;
        this.ossature = ossature;
        this.musculature = musculature;
    }

    public int getId() {
        return id;
    }

    public int getHg() {
        return hg;
    }

    public void setHg(int hg) {
        this.hg = hg;
    }

    public int getLoi() {
        return loi;
    }

    public void setLoi(int loi) {
        this.loi = loi;
    }

    public String getLigneDeDos() {
        return ligneDeDos;
    }

    public void setLigneDeDos(String ligneDeDos) {
        this.ligneDeDos = ligneDeDos;
    }

    public String getPoils() {
        return poils;
    }

    public void setPoils(String poils) {
        this.poils = poils;
    }

    public String getIncurvationTete() {
        return incurvationTete;
    }

    public void setIncurvationTete(String incurvationTete) {
        this.incurvationTete = incurvationTete;
    }

    public String getCornes() {
        return cornes;
    }

    public void setCornes(String cornes) {
        this.cornes = cornes;
    }

    public String getPattesAvantArriere() {
        return pattesAvantArriere;
    }

    public void setPattesAvantArriere(String pattesAvantArriere) {
        this.pattesAvantArriere = pattesAvantArriere;
    }

    public String getPaturons() {
        return paturons;
    }

    public void setPaturons(String paturons) {
        this.paturons = paturons;
    }

    public String getScrotum() {
        return scrotum;
    }

    public void setScrotum(String scrotum) {
        this.scrotum = scrotum;
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getOssature() {
        return ossature;
    }

    public void setOssature(String ossature) {
        this.ossature = ossature;
    }

    public String getMusculature() {
        return musculature;
    }

    public void setMusculature(String musculature) {
        this.musculature = musculature;
    }

    @Override
    public String toString() {
        return "Mesures{" +
                "id=" + id +
                ", hg=" + hg +
                ", loi=" + loi +
                ", ligneDeDos='" + ligneDeDos + '\'' +
                ", poils='" + poils + '\'' +
                ", incurvationTete='" + incurvationTete + '\'' +
                ", cornes='" + cornes + '\'' +
                ", pattesAvantArriere='" + pattesAvantArriere + '\'' +
                ", paturons='" + paturons + '\'' +
                ", scrotum='" + scrotum + '\'' +
                ", queue='" + queue + '\'' +
                ", ossature='" + ossature + '\'' +
                ", musculature='" + musculature + '\'' +
                '}';
    }
}
