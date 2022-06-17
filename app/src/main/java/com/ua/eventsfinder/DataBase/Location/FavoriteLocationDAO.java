package com.ua.eventsfinder.DataBase.Location;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ua.eventsfinder.DataBase.Event.FavoriteEvent;

import java.util.List;

@Dao
public interface FavoriteLocationDAO {
    @Query("SELECT * FROM FavoriteLocation  WHERE following = 1")
    List<FavoriteLocation> getAll();

    @Query("SELECT * FROM FavoriteLocation WHERE  locationID = :locationID LIMIT 1")
    FavoriteLocation findLocationByID(long locationID);

    @Insert
    void insertLocation(FavoriteLocation favoriteLocation);
    @Update
    void updateFavoriteLocation(FavoriteLocation favoriteLocation);
    @Delete
    void delete(FavoriteLocation favoriteLocation);
}
