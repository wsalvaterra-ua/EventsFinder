package com.ua.eventsfinder.DataBase.SearchHistory;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ua.eventsfinder.DataBase.Artist.FavoriteArtist;

import java.util.List;

@Dao
public interface SearchHistoryDAO {
    @Query("SELECT * FROM SearchHistory ORDER BY date DESC LIMIT 30")
    List<SearchHistory> getAll();

    @Query("SELECT * FROM SearchHistory WHERE  id = :SearchHistoryID LIMIT 1")
    SearchHistory findSearchHistoricByID(long SearchHistoryID);

    @Insert
    void insertSearchHistoric(SearchHistory searchHistory);
    @Update
    void updateSearchHistoric(SearchHistory searchHistory);
    @Delete
    void delete(SearchHistory searchHistory);
}
