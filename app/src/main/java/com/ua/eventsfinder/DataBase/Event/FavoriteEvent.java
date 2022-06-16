package com.ua.eventsfinder.DataBase.Event;

import androidx.room.*;

import ru.blizzed.opensongkick.models.Event;

@Entity
public class FavoriteEvent {

    @PrimaryKey
    private float eventID;

    @ColumnInfo()
    private String event;

    @ColumnInfo()
    private boolean following;

    public FavoriteEvent(float eventID, String event) {
        this.eventID = eventID;
        this.event = event;
        this.following = true;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public float getEventID() {
        return eventID;
    }

    public void setEventID(float eventID) {
        this.eventID = eventID;
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

}
