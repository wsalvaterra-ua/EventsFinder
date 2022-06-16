package com.ua.eventsfinder.Atividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.ua.eventsfinder.Adapters.EventoViewThinAdapter2;
import com.ua.eventsfinder.DataBase.Event.FavoriteEvent;
import com.ua.eventsfinder.DataBase.Location.FavoriteLocation;
import com.ua.eventsfinder.DataBase.MyRoomDatabase;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

import ru.blizzed.opensongkick.OpenSongKickContext;
import ru.blizzed.opensongkick.models.Event;
import ru.blizzed.opensongkick.models.MetroArea;

public class eventActivity extends AppCompatActivity {
    private Event event;
    private MyRoomDatabase myRoomDatabase;
    private FavoriteEvent favoriteEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        Bundle extras = getIntent().getExtras();

        OpenSongKickContext.initialize("lKLDro9R9AqqXm1b");

        if (extras != null)
            this.event =new Gson().fromJson(extras.getString("event"), Event.class) ;
        fillElements();
    }
    private  void fillElements(){
        String titulo = (event.getDisplayName().lastIndexOf(" at ")>0) ?
                event.getDisplayName().substring(0,event.getDisplayName()
                        .lastIndexOf(" at ")):event.getDisplayName();
        ((TextView) findViewById(R.id.textViewName)).setText(titulo);


        String localizacao =(event.getDisplayName().lastIndexOf(" at ")
                >0&&event.getDisplayName().lastIndexOf("(") >0)
                ? event.getDisplayName().substring(
                event.getDisplayName().lastIndexOf(" at ")+4,
                event.getDisplayName().lastIndexOf("(")-1
        ):event.getVenue().getDisplayName()+", "+
                event.getVenue().getMetroArea().getDisplayName()
                +", " + event.getVenue().getMetroArea().getCountry().getDisplayName();
        ((TextView) findViewById(R.id.textViewLocation)).setText(localizacao);

        String data =(event.getDisplayName().lastIndexOf("(")>0)? event.getDisplayName().substring(
                event.getDisplayName().lastIndexOf("(")+1,event.getDisplayName().length()-2)
                :event.getStart().getDate();

        ((TextView) findViewById(R.id.textViewDate)).setText(getString(R.string.data_with_data,  data));
        int idParaFoto =(int) ((event.getType() ==Event.Type.CONCERT )?
                event.getPerformances().get(0).getArtist().getId():event.getId());
        String tipodeEventoLink = ((event.getType() ==Event.Type.CONCERT )? "artists":"events");

        String url = "https://images.sk-static.com/images/media/profile_images/"
                +tipodeEventoLink+"/"+idParaFoto+"/huge_avatar";
        Picasso.get()
                .load(url).placeholder(R.drawable.default_event).error(R.drawable.default_event)
                .into((ImageView)findViewById(R.id.imageView));

        loadLineUpArtist(this);

        Chip chipFavorite = ((Chip) findViewById(R.id.chipFavorite));
        this.myRoomDatabase = MyRoomDatabase.getDbInstance(this);
        this.favoriteEvent = myRoomDatabase.favoriteEventDAO().findEventByID(event.getId());
        if (favoriteEvent != null) {
            chipFavorite.setChecked(favoriteEvent.isFollowing());
            chipFavorite.setText((this.favoriteEvent.isFollowing())
                    ? getString(R.string.following) : getString(R.string.follow));
        } else {
            this.favoriteEvent = new FavoriteEvent(event.getId(),
                    (new Gson()).toJson(event));
            myRoomDatabase.favoriteEventDAO().insertEvent(favoriteEvent);
        }

    }

    private void  loadLineUpArtist(eventActivity context){
        ArrayList<Object> artistas = new ArrayList<>();
        for (Event.Performance performance:event.getPerformances())
            artistas.add(performance.getArtist());

        RecyclerView recyclerView = (RecyclerView) context.findViewById(R.id.recyclerViewEventsNear);
        EventoViewThinAdapter2 adapter = new EventoViewThinAdapter2(context,artistas);
        recyclerView.setAdapter(adapter);

    }

    public void goBack(View view) {
        finish();
    }
    public void followUnfollow(View view) {
        this.favoriteEvent.setFollowing(!this.favoriteEvent.isFollowing());

        Chip chip = ((Chip) this.findViewById(R.id.chipFavorite));
        chip.setChecked(this.favoriteEvent.isFollowing());
        chip.setText((this.favoriteEvent.isFollowing()) ? getString(R.string.following) : getString(R.string.follow));
        myRoomDatabase.favoriteEventDAO().updateFavoriteEvent(this.favoriteEvent);
    }
}