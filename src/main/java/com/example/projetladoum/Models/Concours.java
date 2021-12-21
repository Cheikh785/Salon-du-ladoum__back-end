package com.example.projetladoum.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Concours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private int edition;
    private Date date;
    private String lieu;

    public Concours() { }

    public Concours(int edition, Date date, String lieu) {
        this.edition = edition;
        this.date = date;
        this.lieu = lieu;
    }


    public int getId() {
        return id;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
