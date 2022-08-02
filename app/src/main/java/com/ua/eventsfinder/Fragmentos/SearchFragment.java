package com.ua.eventsfinder.Fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.ua.eventsfinder.Adapters.LargeAdapter;
import com.ua.eventsfinder.Atividades.SearchResultsActivity;
import com.ua.eventsfinder.DataBase.MyRoomDatabase;
import com.ua.eventsfinder.DataBase.SearchHistory.SearchHistory;
import com.ua.eventsfinder.Objetos.SongKickAPIKey;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

import api.blizzed.opensongkick.ApiCaller;
import api.blizzed.opensongkick.OpenSongKickContext;
import api.blizzed.opensongkick.SongKickApi;
import api.blizzed.opensongkick.models.Artist;
import api.blizzed.opensongkick.models.Event;
import api.blizzed.opensongkick.models.ResultsPage;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MyRoomDatabase myRoomDatabase;
    public SearchFragment() {
        OpenSongKickContext.initialize(SongKickAPIKey.key);
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        Button btn = (Button) view.findViewById(R.id.btnOpenSearchActivity);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), SearchResultsActivity.class);
                view.getContext().startActivity(intent);}
        });
        myRoomDatabase =  MyRoomDatabase.getDbInstance(view.getContext());

        loadSimiliarArtistIntoView(view);
        return  view;
    }


    public void loadSimiliarArtistIntoView(View view){
            long id = loadRecentSearchesIntoView(view);
            if(id == 0){
                id = 2596951;
            }
        SongKickApi.similarArtists(String.valueOf(id))
                .execute(new ApiCaller.Listener<ResultsPage<Artist>>() {
                    @Override
                    public void onComplete(ResultsPage<Artist> result, ApiCaller<ResultsPage<Artist>> apiCaller) {
                        ArrayList<Object> eventos = new ArrayList(result.getResults());
                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragmentSearchRecyclerViewMain);

                        LargeAdapter adapter = new LargeAdapter(eventos,view.getContext());
                        adapter.maxSize = 300;
                        adapter.gradientFundoBaixo = false;
                        recyclerView.setAdapter(adapter);
                    }
                });
    }
    private long loadRecentSearchesIntoView(View view){
        long id =0;
        ArrayList<SearchHistory> objetos = new ArrayList(myRoomDatabase.searchHistoryDAO().getAll());
        ArrayList<Object> historicos = new ArrayList<>();
        for (SearchHistory searchHistory: objetos) {
            if(searchHistory.getObjetoType().equals("artist")) {
                historicos.add((new Gson()).fromJson(searchHistory.getObjeto(), Artist.class));
                if(id ==0)
                    id= searchHistory.getId();
            }
            else if(searchHistory.getObjetoType().equals("event"))
                historicos.add((new Gson()).fromJson(searchHistory.getObjeto(), Event.class));
        }
        LargeAdapter eventoViewLargeGridAdapter = new LargeAdapter(historicos,view.getContext());
        eventoViewLargeGridAdapter.maxSize = 250;
        eventoViewLargeGridAdapter.hideDateTextView = true;

        ((RecyclerView)view.findViewById(R.id.recyclerViewSearchHistory)).setAdapter(eventoViewLargeGridAdapter);
        return id;
    }
}