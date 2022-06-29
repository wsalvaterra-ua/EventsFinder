package com.ua.eventsfinder.Atividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.ua.eventsfinder.Adapters.ThinAdapter;
import com.ua.eventsfinder.DataBase.Event.FavoriteEvent;
import com.ua.eventsfinder.DataBase.MyRoomDatabase;
import com.ua.eventsfinder.DataBase.SearchHistory.SearchHistory;
import com.ua.eventsfinder.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import api.blizzed.opensongkick.OpenSongKickContext;
import api.blizzed.opensongkick.models.Event;

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
        addToHistory();
    }
    private void addToHistory(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

        LocalDateTime now = LocalDateTime.now();
        SearchHistory searchHistory = myRoomDatabase.searchHistoryDAO().findSearchHistoricByID(event.getId());
        SearchHistory searchHistory1 = new SearchHistory(event.getId(),
                (new Gson()).toJson(event),dtf.format(now),"event");
        if(searchHistory == null)
            myRoomDatabase.searchHistoryDAO().insertSearchHistoric(searchHistory1);
        else
            myRoomDatabase.searchHistoryDAO().updateSearchHistoric(searchHistory1);
    }
    private  void fillElements(){
        String titulo = (event.getDisplayName().lastIndexOf(" at ")>0) ?
                event.getDisplayName().substring(0,event.getDisplayName()
                        .lastIndexOf(" at ")):event.getDisplayName();
        ((TextView) findViewById(R.id.textViewName)).setText(titulo);

        String localizacao =event.getVenue().getDisplayName() + ", " +
                event.getVenue().getMetroArea().getDisplayName() + ", " +
                event.getVenue().getMetroArea().getCountry().getDisplayName();
        ((TextView) findViewById(R.id.textViewLocation)).setText(localizacao);

        ((TextView) findViewById(R.id.textViewDate)).
                setText(getString(R.string.data_with_data, dateToHuman(event.getStart().getDate()) +
                        ((event.getEnd() != null) ?" - " + dateToHuman(event.getEnd().getDate()): "")));

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
        ThinAdapter adapter = new ThinAdapter(context,artistas);
        recyclerView.setAdapter(adapter);

    }
    private String dateToHuman(String sdate){
        Date date_;
        try {
            date_ = new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
        } catch (ParseException e) {
            return  sdate;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, yyyy", Locale.getDefault());
        return  simpleDateFormat.format(date_);

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

    public void openCalendar(View view) {
        LocalDate date1;
        LocalDate date2;
            date1 = LocalDate.parse(event.getStart().getDate());
            if(event.getEnd() != null)
                date2 = LocalDate.parse(event.getEnd().getDate());
            else
                date2 = date1;

        String localizacao =event.getVenue().getDisplayName() + ", " +
                event.getVenue().getMetroArea().getDisplayName() + ", " +
                event.getVenue().getMetroArea().getCountry().getDisplayName();
        ((TextView) findViewById(R.id.textViewLocation)).setText(localizacao);

        Calendar beginTime = Calendar.getInstance();
        beginTime.set(date1.getYear(), date1.getMonth().getValue(), date1.getDayOfMonth(), 0, 0);
        Calendar endTime = Calendar.getInstance();
        endTime.set(date2.getYear(), date2.getMonth().getValue(), date2.getDayOfMonth(), 0, 0);

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                        beginTime.getTimeInMillis())
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME,
                        endTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, event.getDisplayName())
                .putExtra(CalendarContract.Events.DESCRIPTION, event.getDisplayName() + "\n" + event.getUri())
                .putExtra(CalendarContract.Events.EVENT_LOCATION, localizacao)
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);

        startActivity(intent);

    }

}