package com.ua.eventsfinder.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ua.eventsfinder.DataBase.Artist.FavoriteArtist;
import com.ua.eventsfinder.DataBase.Artist.FavoriteArtistDAO;
import com.ua.eventsfinder.DataBase.Event.FavoriteEvent;
import com.ua.eventsfinder.DataBase.Event.FavoriteEventDAO;
import com.ua.eventsfinder.DataBase.Location.FavoriteLocation;
import com.ua.eventsfinder.DataBase.Location.FavoriteLocationDAO;
import com.ua.eventsfinder.DataBase.SearchHistory.SearchHistory;
import com.ua.eventsfinder.DataBase.SearchHistory.SearchHistoryDAO;

@Database(entities = {FavoriteEvent.class, FavoriteLocation.class, FavoriteArtist.class, SearchHistory.class}, version = 1, exportSchema = false)
public abstract class MyRoomDatabase extends RoomDatabase {
    public abstract FavoriteArtistDAO favoriteArtistDAO();
    public abstract FavoriteLocationDAO favoriteLocationDAO();
    public abstract FavoriteEventDAO favoriteEventDAO();
    public abstract SearchHistoryDAO searchHistoryDAO();

    private static MyRoomDatabase INSTANCE;
    public static MyRoomDatabase getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), MyRoomDatabase.class, "DB_NAME233")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }

}
