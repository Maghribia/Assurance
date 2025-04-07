package com.maghrebia.Devis;

public enum EtatDevis {
    EN_ATTENTE("EN_ATTENTE"),
    VALIDE("VALIDE"),
    REFUSE("REFUSE"),
    ANNULE("ANNULE");

    private final String value;

    EtatDevis(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}