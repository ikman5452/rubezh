package com.polytech.blog.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Mahsulot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom, srok;
    private int narkh;
    private int kol_pokupok;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSrok() {
        return srok;
    }

    public void setSrok(String srok) {
        this.srok = srok;
    }

    public int getNarkh() {
        return narkh;
    }

    public void setNarkh(int narkh) {
        this.narkh = narkh;
    }

    public int getKolPokupok() {
        return kol_pokupok;
    }

    public void setKolPokupok(int views) {
        this.kol_pokupok = kol_pokupok;
    }
public Mahsulot(){

}

    public Mahsulot(String nom, String srok, int narkh) {
        this.nom = nom;
        this.srok = srok;
        this.narkh = narkh;
    }
}
