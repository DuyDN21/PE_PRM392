package com.example.pe_prm392.Models;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

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
}
