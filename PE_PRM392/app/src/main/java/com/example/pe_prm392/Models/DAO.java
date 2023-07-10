package com.example.pe_prm392.Models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import kotlin.text.UStringsKt;

@Dao
public interface DAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Country country);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertOrReplace(Country... country);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateCountry(Country country);

    @Query("SELECT * FROM Country")
    public List<Country> getAllCountries();

    @Delete
    void delete(Country country);

    @Query("Select * from Country where name = :name")
    Country getCountryByName(String name);

    @Query("Select * from Country where name LIKE '%' || :name || '%' AND year LIKE '%' || :year || '%' AND rank = :rank AND gdppc = :gdppc")
    List<Country> searchCountry(String name, String year, int rank, long gdppc);

    @Query("Select * from Country where name LIKE '%' || :name || '%' AND year LIKE '%' || :year || '%'")
    List<Country> searchCountry1(String name, String year);

    @Query("Select * from Country where name LIKE '%' || :name || '%' AND year LIKE '%' || :year || '%' AND gdppc = :gdppc")
    List<Country> searchCountry2(String name, String year, long gdppc);

    @Query("Select * from Country where name LIKE '%' || :name || '%' AND year LIKE '%' || :year || '%' AND rank = :rank ")
    List<Country> searchCountry3(String name, String year, int rank);
}
