package com.ua.eventsfinder.Fragmentos;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.chip.Chip;
import com.google.gson.Gson;
import com.ua.eventsfinder.Adapters.EventoViewThinAdapter;
import com.ua.eventsfinder.DataBase.Artist.FavoriteArtist;
import com.ua.eventsfinder.DataBase.Event.FavoriteEvent;
import com.ua.eventsfinder.DataBase.Location.FavoriteLocation;
import com.ua.eventsfinder.DataBase.MyRoomDatabase;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

import ru.blizzed.opensongkick.models.Artist;
import ru.blizzed.opensongkick.models.Event;
import ru.blizzed.opensongkick.models.Location;
import ru.blizzed.opensongkick.models.MetroArea;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FollowingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowingFragment extends Fragment {
    private final Context mContext;
    private MyRoomDatabase myRoomDatabase;
    private EventoViewThinAdapter eventoViewThinAdapter;
    private ArrayList<Object> objectArrayList;
    private int selectionMode;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FollowingFragment() {
        this.mContext = this.getContext();
        this.objectArrayList =  new ArrayList<>();

        this.selectionMode = 0;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FollowingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FollowingFragment newInstance(String param1, String param2) {
        FollowingFragment fragment = new FollowingFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_following, container, false);

        myRoomDatabase = MyRoomDatabase.getDbInstance(view.getContext());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewMain);
        this.eventoViewThinAdapter = new EventoViewThinAdapter(view.getContext(), objectArrayList);
        recyclerView.setAdapter(eventoViewThinAdapter);
        loadFavoriteEvent(view);
        FollowingFragment me = this;
        view.findViewById(R.id.chipArtist).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            me.loadFavoriteArtist(view);
            }
        });
        view.findViewById(R.id.chipEvent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            me.loadFavoriteEvent(view);
            }
        });
        view.findViewById(R.id.chipLocation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            me.loadFavoriteLocation(view);
            }
        });
        return  view;
    }


    public void loadFavoriteLocation(View view){
        ArrayList<Object> loadedLocations = new ArrayList<>();
        Gson gson  = new Gson();
        ArrayList<FavoriteLocation> favoriteLocationArrayList = new ArrayList<>(myRoomDatabase.favoriteLocationDAO().getAll());

        for (FavoriteLocation favoriteLocation:favoriteLocationArrayList)
            loadedLocations.add(gson.fromJson(favoriteLocation.getLocation(),Location.class));

        this.objectArrayList.clear();
        this.objectArrayList.addAll(loadedLocations);
        this.eventoViewThinAdapter.notifyDataSetChanged();
    }
    public void loadFavoriteEvent(View view){
        ArrayList<Object> loadedEvents = new ArrayList<>();
        Gson gson  = new Gson();
        ArrayList<FavoriteEvent> favoriteEventArrayList = new ArrayList<>(myRoomDatabase.favoriteEventDAO().getAll());
        for (FavoriteEvent favoriteEvent:favoriteEventArrayList)
            loadedEvents.add(gson.fromJson(favoriteEvent.getEvent(), Event.class));

        this.objectArrayList.clear();
        this.objectArrayList.addAll(loadedEvents);
        this.eventoViewThinAdapter.notifyDataSetChanged();
    }

    public void loadFavoriteArtist(View view){

        ArrayList<Object> loadedArtists = new ArrayList<>();
        Gson gson  = new Gson();
        ArrayList<FavoriteArtist> favoriteArtistArrayList = new ArrayList<>(myRoomDatabase.favoriteArtistDAO().getAll());

        for (FavoriteArtist favoriteArtist:favoriteArtistArrayList)
            loadedArtists.add(gson.fromJson(favoriteArtist.getArtist(), Artist.class));

        this.objectArrayList.clear();
        this.objectArrayList.addAll(loadedArtists);
        this.eventoViewThinAdapter.notifyDataSetChanged();
    }

}