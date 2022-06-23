package com.ua.eventsfinder.Atividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.ua.eventsfinder.Adapters.EventoViewThinAdapter;
import com.ua.eventsfinder.DataBase.Location.FavoriteLocation;
import com.ua.eventsfinder.DataBase.MyRoomDatabase;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

import api.blizzed.opensongkick.ApiCaller;
import api.blizzed.opensongkick.OpenSongKickContext;
import api.blizzed.opensongkick.SongKickApi;
import api.blizzed.opensongkick.models.Event;
import api.blizzed.opensongkick.models.Location;
import api.blizzed.opensongkick.models.MetroArea;
import api.blizzed.opensongkick.models.ResultsPage;

public class locationActivity extends AppCompatActivity {
    private MetroArea metroArea;
    private Location location;
    String locationID;
    private MyRoomDatabase myRoomDatabase;
    private FavoriteLocation favoriteLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        OpenSongKickContext.initialize("lKLDro9R9AqqXm1b");
        Bundle extras = getIntent().getExtras();
         if (extras != null)
            this.location =new Gson().fromJson(extras.getString("location"),Location.class) ;
         metroArea = location.getMetroArea();
        fillElements();
        locationID=String.valueOf(metroArea.getId()); //location.getCountry().getDisplayName() + location.getDisplayName();
    }
    private  void fillElements(){
        ((TextView) findViewById(R.id.textViewEventsHappeningIn)).setText(getString(R.string.events_near_city, metroArea.getDisplayName()));
        ((TextView) findViewById(R.id.textViewName)).setText(getString(R.string.metroArea_withData, metroArea.getCountry().getDisplayName() , metroArea.getDisplayName()));
        loadEventsNearLocation(this);

        Chip chipFavorite = ((Chip) findViewById(R.id.chipFavorite));
        this.myRoomDatabase = MyRoomDatabase.getDbInstance(this);
        this.favoriteLocation = myRoomDatabase.favoriteLocationDAO().findLocationByID(metroArea.getId());
        if (favoriteLocation != null) {
            chipFavorite.setChecked(favoriteLocation.isFollowing());
            chipFavorite.setText((this.favoriteLocation.isFollowing())
                    ? getString(R.string.following) : getString(R.string.follow));
        } else {
            this.favoriteLocation = new FavoriteLocation(metroArea.getId(),
                    (new Gson()).toJson(location));
            myRoomDatabase.favoriteLocationDAO().insertLocation(favoriteLocation);
        }
    }
    public void loadEventsNearLocation(locationActivity context){

        SongKickApi.metroAreaCalendar().byId(String.valueOf(metroArea.getId()))
                .execute(new ApiCaller.Listener<ResultsPage<Event>>() {
                    @Override
                    public void onComplete(ResultsPage<Event> result, ApiCaller<ResultsPage<Event>> apiCaller) {

                        ArrayList<Object> eventos = new ArrayList(result.getResults());
                        RecyclerView recyclerView = (RecyclerView) context.findViewById(R.id.recyclerViewEventsNear);

                        EventoViewThinAdapter adapter = new EventoViewThinAdapter(context,eventos);
                        recyclerView.setAdapter(adapter);
                    }
                });
    }
    public void goBack(View view) {
        finish();
    }
    public void followUnfollow(View view) {
        this.favoriteLocation.setFollowing(!this.favoriteLocation.isFollowing());

        Chip chip = ((Chip) this.findViewById(R.id.chipFavorite));
        chip.setChecked(this.favoriteLocation.isFollowing());
        chip.setText((this.favoriteLocation.isFollowing()) ? getString(R.string.following) : getString(R.string.follow));
        myRoomDatabase.favoriteLocationDAO().updateFavoriteLocation(this.favoriteLocation);
    }
}