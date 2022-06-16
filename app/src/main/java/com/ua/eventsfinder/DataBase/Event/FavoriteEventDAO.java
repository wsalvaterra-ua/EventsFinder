package com.ua.eventsfinder.DataBase.Event;

import androidx.room.*;

import com.ua.eventsfinder.DataBase.Event.FavoriteEvent;

import java.util.List;

@Dao
public interface FavoriteEventDAO {
    @Query("SELECT * FROM FavoriteEvent")
    List<FavoriteEvent> getAll();

    @Query("SELECT * FROM FavoriteEvent WHERE  eventID = :eventID LIMIT 1")
    FavoriteEvent findEventByID(long eventID);

    @Insert
    void insertEvent(FavoriteEvent favoriteEvent);

    @Update
    void updateFavoriteEvent(FavoriteEvent favoriteEvent);
    
    @Delete
    void delete(FavoriteEvent favoriteEvent);
}
