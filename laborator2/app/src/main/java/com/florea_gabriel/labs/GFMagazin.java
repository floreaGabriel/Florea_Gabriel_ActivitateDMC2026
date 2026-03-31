package com.florea_gabriel.labs;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class GFMagazin implements Parcelable {
    String nume;
    boolean faliment;
    int profit;
    TipMagazin tipMagazin;
    Date data;

    public enum TipMagazin {
        MEDICAL,
        ELECTRONICS,
        COMPUTERS
    }

    public GFMagazin(String nume, Boolean faliment, int profit, TipMagazin tipMagazin, Date data) {
        this.nume = nume;
        this.faliment = faliment;
        this.profit = profit;
        this.tipMagazin = tipMagazin;
        this.data = data;
    }

    protected GFMagazin(Parcel in) {
        nume = in.readString();
        faliment = in.readByte() != 0;
        profit = in.readInt();

        String tip = in.readString();
        if (tip != null) {
            tipMagazin = TipMagazin.valueOf(tip);
        } else {
            tipMagazin = null;
        }

        long timp = in.readLong();
        data = new Date(timp);
    }

    public static final Creator<GFMagazin> CREATOR = new Creator<GFMagazin>() {
        @Override
        public GFMagazin createFromParcel(Parcel in) {
            return new GFMagazin(in);
        }

        @Override
        public GFMagazin[] newArray(int size) {
            return new GFMagazin[size];
        }
    };

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public boolean getFaliment() {
        return faliment;
    }

    public void setFaliment(Boolean faliment) {
        this.faliment = faliment;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public TipMagazin getTipMagazin() {
        return tipMagazin;
    }

    public void setTipMagazin(TipMagazin tipMagazin) {
        this.tipMagazin = tipMagazin;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nume);
        dest.writeByte((byte) (faliment ? 1 : 0));
        dest.writeInt(profit);
        dest.writeString(tipMagazin != null ? tipMagazin.name() : null);
        dest.writeLong(data != null ? data.getTime() : 0);
    }
}
