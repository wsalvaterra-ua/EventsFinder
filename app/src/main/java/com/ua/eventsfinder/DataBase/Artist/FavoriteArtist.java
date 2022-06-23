package com.ua.eventsfinder.DataBase.Artist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class FavoriteArtist
{

    @PrimaryKey
    private float artistID;

    @ColumnInfo()
    private String artist;

    @ColumnInfo()
    private boolean following;

    public FavoriteArtist(float artistID, String artist) {
        this.artistID = artistID;
        this.artist = artist;
        this.following = false;
    }

    public float getArtistID() {
        return artistID;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }
}
