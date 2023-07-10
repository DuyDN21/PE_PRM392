package com.example.pe_prm392.Models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Country {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String name;
    @ColumnInfo(name = "rank")
    private int rank;
    @ColumnInfo(name = "gdppc")
    private long gdppc;
    @ColumnInfo(name = "year")
    private String year;

    public Country() {
    }

    public Country(String name, int rank, long gdppc, String year) {
        this.name = name;
        this.rank = rank;
        this.gdppc = gdppc;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public long getGdppc() {
        return gdppc;
    }

    public void setGdppc(long gdppc) {
        this.gdppc = gdppc;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
}
