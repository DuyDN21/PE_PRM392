package com.example.pe_prm392.Models;

public class Country {
    private String name;
    private int rank;
    private long gdppc;
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
