package com.ua.eventsfinder.Fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ua.eventsfinder.Adapters.EventoViewLargeGridAdapter2;
import com.ua.eventsfinder.Atividades.SearchResultsActivity;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.OpenSongKickContext;
import ru.blizzed.opensongkick.SongKickApi;
import ru.blizzed.opensongkick.models.Artist;
import ru.blizzed.opensongkick.models.ResultsPage;

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

    public SearchFragment() {
        OpenSongKickContext.initialize("lKLDro9R9AqqXm1b");
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
        loadRecentSearchesIntoView(view);
        loadSimiliarArtistIntoView(view);
        return  view;
    }


    public void loadSimiliarArtistIntoView(View view){

        SongKickApi.similarArtists("2596951")
                .execute(new ApiCaller.Listener<ResultsPage<Artist>>() {
                    @Override
                    public void onComplete(ResultsPage<Artist> result, ApiCaller<ResultsPage<Artist>> apiCaller) {
                        ArrayList<Object> eventos = new ArrayList(result.getResults().subList(0,35));
                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragmentSearchRecyclerViewMain);

                        EventoViewLargeGridAdapter2 adapter = new EventoViewLargeGridAdapter2(eventos,view.getContext());
                        recyclerView.setAdapter(adapter);
                    }
                });
    }
    private  void  loadRecentSearchesIntoView(View view){



        SongKickApi.similarArtists("99074")
                .execute(new ApiCaller.Listener<ResultsPage<Artist>>() {
                    @Override
                    public void onComplete(ResultsPage<Artist> result, ApiCaller<ResultsPage<Artist>> apiCaller) {
                        ArrayList<Object> eventos = new ArrayList(result.getResults().subList(0,35));
                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragmentSearchRecyclerViewMain);

                        EventoViewLargeGridAdapter2 eventoViewLargeGridAdapter = new EventoViewLargeGridAdapter2(eventos,view.getContext());

                        ((RecyclerView)view.findViewById(R.id.recyclerViewSearchHistory)).setAdapter(eventoViewLargeGridAdapter);
                    }
                });


    }



}