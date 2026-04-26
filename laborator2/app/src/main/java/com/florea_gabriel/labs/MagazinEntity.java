package com.florea_gabriel.labs;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "magazine")
public class MagazinEntity {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nume;
    public boolean faliment;
    public int profit;
    public String tipMagazin;

    public MagazinEntity(String nume, boolean faliment, int profit, String tipMagazin) {
        this.nume = nume;
        this.faliment = faliment;
        this.profit = profit;
        this.tipMagazin = tipMagazin;
    }

    @Override
    public String toString() {
        return "ID:" + id + " | " + nume + " | Profit:" + profit + " | " + tipMagazin + " | Faliment:" + (faliment ? "Da" : "Nu");
    }
}
