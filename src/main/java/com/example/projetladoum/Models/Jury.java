package com.example.projetladoum.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Jury {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 255, unique = true, nullable = true)
    private String login;

    @Column(length = 255, nullable = true)
    private String password;

    @Column(nullable = true)
    private String prenoms;

    @Column(nullable = true)
    private String nom;

    private Date birth;

    private String role;

    private String adresse;

    @Column(length = 255, unique = true)
    private String email;

    @Column(length = 255, unique = true)
    private int tel;

    public Jury() {
    }

    public Jury(String login, String password, String prenoms, String nom, Date birth, String role, String adresse, String email, int tel) {
        this.login = login;
        this.password = password;
        this.prenoms = prenoms;
        this.nom = nom;
        this.birth = birth;
        this.role = role;
        this.adresse = adresse;
        this.email = email;
        this.tel = tel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }
}
