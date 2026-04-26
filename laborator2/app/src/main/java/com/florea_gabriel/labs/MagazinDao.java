package com.florea_gabriel.labs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MagazinDao {

    // Metoda 1: inserare
    @Insert
    void insert(MagazinEntity magazin);

    // Metoda 2: selectie toate
    @Query("SELECT * FROM magazine")
    List<MagazinEntity> getAll();

    // Metoda 3: selectie dupa nume (string egal cu parametru)
    @Query("SELECT * FROM magazine WHERE nume = :nume")
    List<MagazinEntity> getByNume(String nume);

    // Metoda 4: selectie dupa profit intr-un interval
    @Query("SELECT * FROM magazine WHERE profit BETWEEN :profitMin AND :profitMax")
    List<MagazinEntity> getByProfitInterval(int profitMin, int profitMax);

    // Metoda 5: stergere inregistrari cu profit mai mare decat parametru
    @Query("DELETE FROM magazine WHERE profit > :profitMax")
    void deleteWhereProfit(int profitMax);

    // Metoda 6: creste cu 1 profitul pentru magazinele al caror nume incepe cu o litera
    @Query("UPDATE magazine SET profit = profit + 1 WHERE nume LIKE :litera || '%'")
    void incrementProfitByLitera(String litera);
}
