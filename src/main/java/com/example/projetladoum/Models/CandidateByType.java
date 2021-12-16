package com.example.projetladoum.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CandidateByType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String prenoms;
    private String nom;
    private String adresse;
    private String nomOvin;
    private String genre;

    public CandidateByType() { }


    public CandidateByType(String prenoms, String nom, String adresse, String nomOvin, String genre) {
        this.prenoms = prenoms;
        this.nom = nom;
        this.adresse = adresse;
        this.nomOvin = nomOvin;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public String getPrenoms() {
        return prenoms;
    }

    public void setPrenoms(String prenoms) {
        this.prenoms = prenoms;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNomOvin() {
        return nomOvin;
    }

    public void setNomOvin(String nomOvin) {
        this.nomOvin = nomOvin;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
