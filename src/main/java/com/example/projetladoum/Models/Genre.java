package com.example.projetladoum.Models;

public enum Genre {
    MA("MaleAdulte"),
    FA("FemelleAdulte"),
    ME("MaleEspoir"),
    FE("FemelleEspoir"),
    Agn("Agneaux"),
    agl("Agnelle");

    public final String label;

    private Genre(String label) {
        this.label = label;
    }
}
