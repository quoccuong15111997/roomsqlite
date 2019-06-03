package com.nqc.demoroom;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Movies {
    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "movieId")
    private String movieId;
    @ColumnInfo(name = "movieName")
    private String movieName;

    public Movies() {
    }

    public String getMovieId() { return movieId; }
    public void setMovieId(String movieId) { this.movieId = movieId; }
    public String getMovieName() { return movieName; }
    public void setMovieName (String movieName) { this.movieName = movieName; }
}
