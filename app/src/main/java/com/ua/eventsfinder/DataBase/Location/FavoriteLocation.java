package com.ua.eventsfinder.DataBase.Location;

import androidx.room.*;

import ru.blizzed.opensongkick.models.Location;

@Entity
public class FavoriteLocation
{

    @PrimaryKey
    private float locationID;

    @ColumnInfo()
    private String location;

    @ColumnInfo()
    private boolean following;

    public FavoriteLocation(float locationID, String location) {
        this.locationID = locationID;
        this.location = location;
        this.following = true;
    }

    public float getLocationID() {
        return locationID;
    }

    public void setLocationID(float locationID) {
        this.locationID = locationID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }
}
