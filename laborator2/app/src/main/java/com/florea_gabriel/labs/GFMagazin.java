package com.florea_gabriel.labs;

import java.io.Serializable;

public class GFMagazin implements Serializable {
    String nume;
    Boolean faliment;
    int profit;
    TipMagazin tipMagazin;

    public enum TipMagazin {
        MEDICAL,
        ELECTRONICS,
        COMPUTERS
    }

    public GFMagazin(String nume, Boolean faliment, int profit, TipMagazin tipMagazin) {
        this.nume = nume;
        this.faliment = faliment;
        this.profit = profit;
        this.tipMagazin = tipMagazin;
    }

    @Override
    public String toString() {
        return "Nume: " + nume +
                "\nFaliment: " + (faliment ? "Da" : "Nu") +
                "\nProfit: " + profit +
                "\nTip magazin: " + tipMagazin;
    }
}