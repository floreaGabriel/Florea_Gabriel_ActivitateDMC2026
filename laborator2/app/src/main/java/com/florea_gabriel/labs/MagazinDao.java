package com.florea_gabriel.labs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MagazinDao {

    @Insert
    void insert(MagazinEntity magazin);

    @Query("SELECT * FROM magazine")
    List<MagazinEntity> getAll();

    @Query("SELECT * FROM magazine WHERE nume = :nume")
    List<MagazinEntity> getByNume(String nume);

    @Query("SELECT * FROM magazine WHERE profit BETWEEN :profitMin AND :profitMax")
    List<MagazinEntity> getByProfitInterval(int profitMin, int profitMax);

    @Query("DELETE FROM magazine WHERE profit > :profitMax")
    void deleteWhereProfit(int profitMax);

    @Query("UPDATE magazine SET profit = profit + 1 WHERE nume LIKE :litera || '%'")
    void incrementProfitByLitera(String litera);
}
