package com.weebly.leeboxuan.songs;

import java.io.Serializable;

public class Song implements Serializable {

    private int id;
    private String title;
    private String singer;
    private int year;
    private int stars;

    public Song(int id, String title, String singer, int year, int stars) {
        this.id = id;
        this.title = title;
        this.singer = singer;
        this.stars = stars;
        this.year = year;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", "
                + title + ", "
                + singer + ", "
                + year + ", "
                + stars;
    }

    public int getId() { return id; }

    public String getTitle() {
        return title;
    }

    public String getSinger() {
        return singer;
    }

    public int getYear() {
        return year;
    }

    public int getStars() {
        return stars;
    }

    public void setTitle(String title) { this.title = title; }

    public void setSinger(String singer) { this.singer = singer; }

    public void setYear(int year) { this.year = year; }

    public void setStars(int stars) { this.stars = stars; }
}
