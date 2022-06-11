package com.ua.eventsfinder.Atividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.ua.eventsfinder.R;

import ru.blizzed.opensongkick.ApiCallException;
import ru.blizzed.opensongkick.ApiErrorException;
import ru.blizzed.opensongkick.OpenSongKickContext;
import ru.blizzed.opensongkick.SongKickApi;
import ru.blizzed.opensongkick.params.SongKickParams;

public class ArtistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);

        double lat = 48.1550547;
        double lon = 11.4017526;
        System.out.print(" SD\n\n\n\n\n\nkkkkkkkkkkk\n\n\n\n\n" +
                "\n\n\n\n\n\n\n\n\n\n" +
                "\n\n\n\n\n" +
                "\n\n\n\n\n\n\n\n\n\n" +
                "\n\n\n\n\n" +
                "\n\n\n\n\n" +
                "\n\n\n\n\n" +
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "kLLLL\ndddddddddd");
        OpenSongKickContext.initialize("lKLDro9R9AqqXm1b");
        try {
            SongKickApi.eventSearch()
                    .byLocation(
                            SongKickParams.LOCATION_GEO.of(lat, lon),
                            SongKickParams.ARTIST_NAME.of("Cradle Of Filth"),
                            SongKickParams.PAGE.of(2)
                    )
                    .execute()
                    .getResults()
                    .forEach(System.out::println);
        } catch (ApiCallException e) {
            e.printStackTrace();
        } catch (ApiErrorException e) {
            e.printStackTrace();
        }

        setSupportActionBar(findViewById(R.id.topBar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowTitleEnabled(false);
//        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }


}