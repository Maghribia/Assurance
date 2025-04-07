package com.esprit.ms.job;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id=0;


    private String service;


    private boolean etat;

    public Job() {
    }

    public Job(String service, boolean etat) {
        this.service = service;
        this.etat = etat;
    }

    public int getId() {
        return id;
    }



    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public boolean isEtat() {
        return etat;
    }

    public void setEtat(boolean etat) {
        this.etat = etat;
    }
}
