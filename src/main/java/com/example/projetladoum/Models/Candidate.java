package com.example.projetladoum.Models;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true)
    private String prenoms;

    @Column(nullable = true)
    private String nom;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String adresse;

    @Column(nullable = true)
    private Date birth;

    @Column(nullable = true, unique = true)
    private String telephone;

    //@JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ovin_id", referencedColumnName = "id")
    private Ovins ovin;

    public Candidate() { }

    public Candidate(String prenoms, String nom, String email, String adresse, Date birth, String telephone, Ovins ovin) {
        this.prenoms = prenoms;
        this.nom = nom;
        this.email = email;
        this.adresse = adresse;
        this.birth = birth;
        this.telephone = telephone;
        this.ovin = ovin;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Ovins getOvin() {
        return ovin;
    }

    public void setOvin(Ovins ovin) {
        this.ovin = ovin;
    }
}
