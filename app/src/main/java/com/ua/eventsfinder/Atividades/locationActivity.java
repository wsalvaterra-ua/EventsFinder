package com.ua.eventsfinder.Atividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ua.eventsfinder.Adapters.EventoViewThinAdapter2;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.OpenSongKickContext;
import ru.blizzed.opensongkick.SongKickApi;
import ru.blizzed.opensongkick.models.Event;
import ru.blizzed.opensongkick.models.MetroArea;
import ru.blizzed.opensongkick.models.ResultsPage;

public class locationActivity extends AppCompatActivity {
    private MetroArea location;
    String locationID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        OpenSongKickContext.initialize("lKLDro9R9AqqXm1b");
        Bundle extras = getIntent().getExtras();
         if (extras != null)
            this.location =new Gson().fromJson(extras.getString("location"), MetroArea.class) ;
        fillElements();
        locationID=String.valueOf(location.getId()); //location.getCountry().getDisplayName() + location.getDisplayName();

    }
    private  void fillElements(){
        ((TextView) findViewById(R.id.textViewEventsHappeningIn)).setText(getString(R.string.events_near_city,location.getDisplayName()));

        ((TextView) findViewById(R.id.textViewName)).setText(getString(R.string.metroarea_withData , location.getCountry().getDisplayName() , location.getDisplayName()));

        loadEventsNearLocation(this);

    }
    public void loadEventsNearLocation(locationActivity context){

        SongKickApi.metroAreaCalendar().byId(String.valueOf(location.getId()))
                .execute(new ApiCaller.Listener<ResultsPage<Event>>() {
                    @Override
                    public void onComplete(ResultsPage<Event> result, ApiCaller<ResultsPage<Event>> apiCaller) {

                        ArrayList<Object> eventos = new ArrayList(result.getResults());
                        RecyclerView recyclerView = (RecyclerView) context.findViewById(R.id.recyclerViewEventsNear);

                        EventoViewThinAdapter2 adapter = new EventoViewThinAdapter2(context,eventos);
                        recyclerView.setAdapter(adapter);
                    }
                });
    }
    public void goBack(View view) {
        finish();
    }
}