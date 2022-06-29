package com.ua.eventsfinder.Fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.ua.eventsfinder.Adapters.LargeAdapter;
import com.ua.eventsfinder.DataBase.Artist.FavoriteArtist;
import com.ua.eventsfinder.DataBase.Location.FavoriteLocation;
import com.ua.eventsfinder.DataBase.MyRoomDatabase;
import com.ua.eventsfinder.DataBase.SearchHistory.SearchHistory;
import com.ua.eventsfinder.Objetos.GpsTracker;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

import api.blizzed.opensongkick.ApiCaller;
import api.blizzed.opensongkick.OpenSongKickContext;
import api.blizzed.opensongkick.SongKickApi;
import api.blizzed.opensongkick.models.Artist;
import api.blizzed.opensongkick.models.Event;
import api.blizzed.opensongkick.models.Location;
import api.blizzed.opensongkick.models.ResultsPage;
import api.blizzed.opensongkick.params.SongKickParams;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private MyRoomDatabase myRoomDatabase;
    private Gson gson = new Gson();
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private  double latitude,longitude;
    public HomeFragment() {
        this.latitude = 0;
        this.longitude = 0;
        OpenSongKickContext.initialize("lKLDro9R9AqqXm1b");
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {

        HomeFragment fragment = new HomeFragment();
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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        myRoomDatabase= myRoomDatabase.getDbInstance(view.getContext());

        loadArtistsSimiliar(view);
        getLocation(view);
        loadEventsNearMe(view);
        loadEventsNearCityYouFollow(view);
        loadSimiliarArtistFromRecentSearches(view);
        return  view;
    }
    private void loadArtistsSimiliar(View view){
        ArrayList<FavoriteArtist> favoriteArtistArrayList =new ArrayList<>(myRoomDatabase.favoriteArtistDAO().getAll());
        if(favoriteArtistArrayList.size()<1){
            ((TextView) view.findViewById(R.id.textViewSimiliarArtists)).setMaxHeight(0);
            return;
        }
        int randomIndex = (int) ((Math.random() * (favoriteArtistArrayList.size())) + 1)-1;
        Artist artist = gson.fromJson(favoriteArtistArrayList.get(randomIndex).getArtist(),Artist.class);

        ((TextView) view.findViewById(R.id.textViewSimiliarArtists))
                .setText(getString(R.string.becauseYouFollow_withData ,artist.getDisplayName() ) );

        SongKickApi.similarArtists(String.valueOf(artist.getId()))
                .execute(new ApiCaller.Listener<ResultsPage<Artist>>() {
                    @Override
                    public void onComplete(ResultsPage<Artist> result, ApiCaller<ResultsPage<Artist>> apiCaller) {
                        int maxResults = 35;
                        ArrayList<Object> eventos = new ArrayList(result.getResults());
                        if(eventos.size()> maxResults)
                            eventos = new ArrayList<>(eventos.subList(0,maxResults));
                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewSimiliar);

                        LargeAdapter adapter = new LargeAdapter(eventos,view.getContext());
                        recyclerView.setAdapter(adapter);
                    }
                });
    }
    private void loadEventsNearCityYouFollow(View view){
        ArrayList<FavoriteLocation> favoriteLocationArrayList =new ArrayList<>(myRoomDatabase.favoriteLocationDAO().getAll());
        if(favoriteLocationArrayList.size()<1){
            ((TextView) view.findViewById(R.id.textViewEventsHappeningIn)).setMaxHeight(0);
            return;
        }
        int randomIndex = (int) ((Math.random() * (favoriteLocationArrayList.size())) + 1)-1;
        Location location = gson.fromJson(favoriteLocationArrayList.get(randomIndex).getLocation(), Location.class);

        ((TextView) view.findViewById(R.id.textViewEventsHappeningIn))
                .setText(getString(R.string.eventsHappeningIn_withData , location.getCity().getDisplayName()));
        SongKickApi.metroAreaCalendar().byId(String.valueOf(location.getMetroArea().getId()))
                .execute(new ApiCaller.Listener<ResultsPage<Event>>() {
                    @Override
                    public void onComplete(ResultsPage<Event> result, ApiCaller<ResultsPage<Event>> apiCaller) {
                        int maxResults = 35;
                        ArrayList<Object> eventos = new ArrayList(result.getResults());
                        if(eventos.size()> maxResults)
                            eventos = new ArrayList<>(eventos.subList(0,maxResults));
                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewEventsHappeningIn);

                        LargeAdapter adapter = new LargeAdapter(eventos,view.getContext());
                        recyclerView.setAdapter(adapter);
                    }
                });
    }

    public void getLocation(View view){
        GpsTracker gpsTracker = new GpsTracker(view.getContext());
        if(gpsTracker.canGetLocation()){
            this.latitude = gpsTracker.getLatitude();
            this.longitude = gpsTracker.getLongitude();

        }else{
            gpsTracker.showSettingsAlert();
        }
    }

    private void loadEventsNearMe(View view){

             SongKickApi.eventSearch()
                    .byLocation(SongKickParams.LOCATION_GEO.of(latitude, longitude))
                    .execute(new ApiCaller.Listener<ResultsPage<Event>>() {
                        @Override
                        public void onComplete(ResultsPage<Event> result, ApiCaller<ResultsPage<Event>> apiCaller) {
                            ArrayList<Object> eventos = new ArrayList(result.getResults());

                            ((TextView) view.findViewById(R.id.textViewEventsNearMe)).setText(R.string.events_happeningInYourCity);
                            RecyclerView recyclerView =  view.findViewById(R.id.recyclerViewEventsNearMe);

                            LargeAdapter adapter = new LargeAdapter(eventos,view.getContext());
                            recyclerView.setAdapter(adapter);
                      }
                    });
    }

    private void loadSimiliarArtistFromRecentSearches(View view){
        ArrayList<SearchHistory> objetos = new ArrayList(myRoomDatabase.searchHistoryDAO().getAll());
        Artist artist = null;
        for (SearchHistory searchHistory: objetos)
            if(searchHistory.getObjetoType().equals("artist"))
                if (artist == null)
                    artist = gson.fromJson(searchHistory.getObjeto(), Artist.class);
                else{
                    artist = gson.fromJson(searchHistory.getObjeto(), Artist.class);
                    break;
                }


        ((TextView) view.findViewById(R.id.textViewSimiliarArtistsToRecentSearch)).setText("");
        if(artist == null )
            return;
        String artistName = artist.getDisplayName();
        SongKickApi.similarArtists(String.valueOf(artist.getId()))
                .execute(new ApiCaller.Listener<ResultsPage<Artist>>() {
                    @Override
                    public void onComplete(ResultsPage<Artist> result, ApiCaller<ResultsPage<Artist>> apiCaller) {
                        ArrayList<Object> eventos = new ArrayList(result.getResults());
                        RecyclerView recyclerView = (RecyclerView) view
                                .findViewById(R.id.recyclerViewSimiliarArtistsToRecentSearch);

                        ((TextView) view.findViewById(R.id.textViewSimiliarArtistsToRecentSearch))
                                .setText(getString(R.string.sinceYouRecentlySearchedFor_withData,artistName));
                        LargeAdapter adapter = new LargeAdapter(eventos,view.getContext());
                        recyclerView.setAdapter(adapter);
                    }
                });

    }


}