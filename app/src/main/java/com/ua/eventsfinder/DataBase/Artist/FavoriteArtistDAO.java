package com.ua.eventsfinder.DataBase.Artist;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

@Dao
public interface FavoriteArtistDAO {
    @Query("SELECT * FROM FavoriteArtist WHERE following = 1")
    List<FavoriteArtist> getAll();

    @Query("SELECT * FROM FavoriteArtist WHERE  artistID = :artistID LIMIT 1")
    FavoriteArtist findArtistByID(long artistID);

    @Insert
    void insertArtist(FavoriteArtist favoriteArtist);
    @Update
    void updateFavoriteArtist(FavoriteArtist favoriteArtist);
    @Delete
    void delete(FavoriteArtist favoriteArtist);
}
