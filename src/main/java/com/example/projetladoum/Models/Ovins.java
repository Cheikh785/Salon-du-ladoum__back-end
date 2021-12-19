package com.example.projetladoum.Models;

import javax.persistence.*;

@Entity
public class Ovins {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true, unique = true)
    private String nomOvin;

    @Column(nullable = true)
    private int nbDents;

    @Column(nullable = true)
    private String genre;

    @Column(nullable = true)
    private int age;

    @Column(nullable = true)
    private double score;

    @Column(nullable = true)
    private int rang;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "mesures_id", referencedColumnName = "id")
    private Mesures mesures;

    //@JsonIgnore
    //@OneToOne(fetch = FetchType.LAZY, mappedBy = "ovin")
    //@JoinColumn(name = "proprietaire_id")
    //private Candidate candidate;

    public Ovins() { }

    public Ovins(String nomOvin, int nbDents, String genre, int age, double score, int rang, Mesures mesures) {
        this.nomOvin = nomOvin;
        this.nbDents = nbDents;
        this.genre = genre;
        this.age = age;
        this.score = score;
        this.rang = rang;
        this.mesures = mesures;
    }

    public int getId() {
        return id;
    }


    public String getNomOvin() {
        return nomOvin;
    }

    public void setNomOvin(String nomOvin) {
        this.nomOvin = nomOvin;
    }

    public int getNbDents() {
        return nbDents;
    }

    public void setNbDents(int nbDents) {
        this.nbDents = nbDents;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public Mesures getMesures() {
        return mesures;
    }

    public void setMesures(Mesures mesures) {
        this.mesures = mesures;
    }

    @Override
    public String toString() {
        return "Ovins{" +
                "id=" + id +
                ", nomOvin='" + nomOvin + '\'' +
                ", nbDents=" + nbDents +
                ", genre='" + genre + '\'' +
                ", age=" + age +
                ", score=" + score +
                ", rang=" + rang +
                ", mesures=" + mesures +
                '}';
    }
}
