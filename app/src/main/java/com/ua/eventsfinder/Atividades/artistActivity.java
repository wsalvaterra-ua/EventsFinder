package com.ua.eventsfinder.Atividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.ua.eventsfinder.Adapters.EventoViewLargeGridAdapter2;
import com.ua.eventsfinder.Adapters.EventoViewThinAdapter2;
import com.ua.eventsfinder.R;

import java.util.ArrayList;
import java.util.Objects;

import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.OpenSongKickContext;
import ru.blizzed.opensongkick.SongKickApi;
import ru.blizzed.opensongkick.models.Artist;
import ru.blizzed.opensongkick.models.Event;
import ru.blizzed.opensongkick.models.ResultsPage;

public class artistActivity extends AppCompatActivity {
    private Artist artist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        setSupportActionBar(findViewById(R.id.topBar));
//        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();

        OpenSongKickContext.initialize("lKLDro9R9AqqXm1b");

        if (extras != null)
            this.artist=new Gson().fromJson(extras.getString("artist"),Artist.class) ;
        fillElements();

    }
    private  void fillElements(){
        ((TextView) findViewById(R.id.textViewName)).setText(artist.getDisplayName());

        ((TextView) findViewById(R.id.textViewDate)).setText("Touring Until: " + artist.getOnTourUntil());
        String url = "https://images.sk-static.com/images/media/profile_images/artists/"+artist.getId()+"/huge_avatar";
        Picasso.get()
                .load(url).placeholder(R.drawable.default_event).error(R.drawable.default_event)
                .into((ImageView)findViewById(R.id.imageView));
        loadUpcomingEvents(this);
        loadSimiliarArtists(this);

    }


    public void loadUpcomingEvents(artistActivity context){

        SongKickApi.artistCalendar().byId(String.valueOf(artist.getId()))
                .execute(new ApiCaller.Listener<ResultsPage<Event>>() {
                    @Override
                    public void onComplete(ResultsPage<Event> result, ApiCaller<ResultsPage<Event>> apiCaller) {

                        ArrayList<Object> eventos = new ArrayList(result.getResults());
                        RecyclerView recyclerView = (RecyclerView) context.findViewById(R.id.recyclerViewUpcomingEvents);

                        EventoViewThinAdapter2 adapter = new EventoViewThinAdapter2(context,eventos);
                        recyclerView.setAdapter(adapter);
                    }
                });
    }
    public void loadSimiliarArtists(artistActivity context){

        SongKickApi.similarArtists(String.valueOf(artist.getId()))
                .execute(new ApiCaller.Listener<ResultsPage<Artist>>() {
                    @Override
                    public void onComplete(ResultsPage<Artist> result, ApiCaller<ResultsPage<Artist>> apiCaller) {
                        ArrayList<Object> eventos = new ArrayList(result.getResults());
                        RecyclerView recyclerView = (RecyclerView) context.findViewById(R.id.recyclerViewSimiliar);

                        EventoViewLargeGridAdapter2 adapter = new EventoViewLargeGridAdapter2(eventos,context);
                        recyclerView.setAdapter(adapter);
                    }
                });
    }

    public void goBack(View view) {
        finish();
    }

}