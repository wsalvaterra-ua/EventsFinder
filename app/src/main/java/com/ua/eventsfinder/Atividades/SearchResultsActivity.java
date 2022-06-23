package com.ua.eventsfinder.Atividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import com.ua.eventsfinder.Adapters.EventoViewThinAdapter;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

import api.blizzed.opensongkick.ApiCaller;
import api.blizzed.opensongkick.SongKickApi;
import api.blizzed.opensongkick.models.Artist;
import api.blizzed.opensongkick.models.Location;
import api.blizzed.opensongkick.models.ResultsPage;

public class SearchResultsActivity extends AppCompatActivity {
    private final SearchResultsActivity mContext;
    private EventoViewThinAdapter SearchResultsAdapter;
    private ArrayList<Object> pesquisaLista;
    private int selectionMode;

    public SearchResultsActivity() {
        this.mContext = this;
        this.pesquisaLista =  new ArrayList<>();
        this.SearchResultsAdapter = new EventoViewThinAdapter(this,pesquisaLista);
        this.selectionMode = 0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_search_results);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSearchHistory);

        recyclerView.setAdapter(SearchResultsAdapter);

        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        configSearchView(searchView);

    }


    private void configSearchView(SearchView searchView){
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.setQueryHint("Search Artists, Locations");
        searchView.requestFocusFromTouch();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextChange(String newText) {
                switch (mContext.selectionMode){
                    case  1:
                        SongKickApi.locationSearch().byQuery(newText)
                                .execute(new ApiCaller.Listener<ResultsPage<Location>>() {
                                    @Override
                                    public void onComplete(ResultsPage<Location> result, ApiCaller<ResultsPage<Location>> apiCaller) {
                                        mContext.pesquisaLista.clear();
                                        mContext.pesquisaLista.addAll(new ArrayList<>(result.getResults()));
                                        mContext.SearchResultsAdapter.notifyDataSetChanged();
                                    }
                                });
                        break;
                    default:
                        SongKickApi.artistSearch().byName(newText)
                                .execute(new ApiCaller.Listener<ResultsPage<Artist>>() {
                                    @Override
                                    public void onComplete(ResultsPage<Artist> result, ApiCaller<ResultsPage<Artist>> apiCaller) {
                                        mContext.pesquisaLista.clear();
                                        mContext.pesquisaLista.addAll(new ArrayList<>(result.getResults()));
                                        mContext.SearchResultsAdapter.notifyDataSetChanged();
                                    }
                                });
                        break;
                }
                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
        });

    }

    public void searchByLocation(View view) {
        SongKickApi.locationSearch().byQuery(((SearchView) findViewById(R.id.searchView)).getQuery().toString())
                .execute(new ApiCaller.Listener<ResultsPage<Location>>() {
                    @Override
                    public void onComplete(ResultsPage<Location> result, ApiCaller<ResultsPage<Location>> apiCaller) {
                        mContext.pesquisaLista.clear();
                        mContext.pesquisaLista.addAll(new ArrayList<>(result.getResults()));
                        mContext.SearchResultsAdapter.notifyDataSetChanged();
                    }
                });
        this.selectionMode = 1;
    }


    public void searchByArtists(View view) {
        SongKickApi.artistSearch().byName(((SearchView) findViewById(R.id.searchView)).getQuery().toString())
                .execute(new ApiCaller.Listener<ResultsPage<Artist>>() {
                    @Override
                    public void onComplete(ResultsPage<Artist> result, ApiCaller<ResultsPage<Artist>> apiCaller) {
                        mContext.pesquisaLista.clear();
                        mContext.pesquisaLista.addAll(new ArrayList<>(result.getResults()));
                        mContext.SearchResultsAdapter.notifyDataSetChanged();
                    }
                });
        this.selectionMode = 0;
    }
    public void goBack(View view) {
        finish();
    }
}