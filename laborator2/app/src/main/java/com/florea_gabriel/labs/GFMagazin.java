package com.florea_gabriel.labs;

import java.io.Serializable;
import java.util.Date;

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

    public Date data;

    public GFMagazin(String nume, Boolean faliment, int profit, TipMagazin tipMagazin, Date data) {
        this.nume = nume;
        this.faliment = faliment;
        this.profit = profit;
        this.tipMagazin = tipMagazin;
        this.data = data;
    }

    @Override
    public String toString() {
        return "Nume: " + nume +
                "\nFaliment: " + (faliment ? "Da" : "Nu") +
                "\nProfit: " + profit +
                "\nTip magazin: " + tipMagazin +
                "\nData: " + data;
    }
}