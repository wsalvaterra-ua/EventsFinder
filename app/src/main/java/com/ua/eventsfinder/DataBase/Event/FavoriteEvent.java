package com.ua.eventsfinder.DataBase.Event;

import androidx.room.*;

@Entity
public class FavoriteEvent {

    @PrimaryKey
    private long eventID;

    @ColumnInfo()
    private String event;

    @ColumnInfo()
    private boolean following;

    public FavoriteEvent(long eventID, String event) {
        this.eventID = eventID;
        this.event = event;
        this.following = false;
    }


    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public long getEventID() {
        return eventID;
    }

    public void setEventID(long eventID) {
        this.eventID = eventID;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

}
