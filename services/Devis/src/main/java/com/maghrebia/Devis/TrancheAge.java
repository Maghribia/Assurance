package com.maghrebia.Devis;

public enum TrancheAge {

    DE_1_A_60_ANS("1-60 ans"),
    DE_61_A_75_ANS("61-75 ans"),
    DE_76_A_85_ANS("76-90 ans");

    private final String libelle;

    TrancheAge(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }
}
