package com.ua.eventsfinder.Atividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.SearchView;

import com.google.gson.Gson;
import com.ua.eventsfinder.Adapters.EventoViewThinAdapter2;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.SongKickApi;
import ru.blizzed.opensongkick.models.Artist;
import ru.blizzed.opensongkick.models.Location;
import ru.blizzed.opensongkick.models.ResultsPage;

public class SearchResultsActivity extends AppCompatActivity {
    private final SearchResultsActivity mContext;
    private  EventoViewThinAdapter2 SearchResultsAdapter;
    private ArrayList<Object> pesquisaLista;
    private int selectionMode;

    public SearchResultsActivity() {
        this.mContext = this;
        this.pesquisaLista =  new ArrayList<>();
        this.SearchResultsAdapter = new EventoViewThinAdapter2(this,pesquisaLista);
        this.selectionMode = 0;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_search_results);
        setSupportActionBar(findViewById(R.id.topBar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSearchHistory);

        recyclerView.setAdapter(SearchResultsAdapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        super.getMenuInflater().inflate(R.menu.searchmenuappbar, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.setQueryHint("Search Artists, Locations");
        searchView.requestFocusFromTouch();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextChange(String newText) {
                System.out.println(newText + "\n");
                    switch (mContext.selectionMode){
                        case  1:
                            SongKickApi.locationSearch().byQuery(newText)
                                    .execute(new ApiCaller.Listener<ResultsPage<Location>>() {
                                        @Override
                                        public void onComplete(ResultsPage<Location> result, ApiCaller<ResultsPage<Location>> apiCaller) {
                                            System.out.println((new Gson()).toJson(result.getResults()));
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
                                            System.out.println((new Gson()).toJson(result.getResults()));

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

                if(query.equals("voltar"))
                    finish();
                return false;
            }
        });
        return true;
    }

    public void searchByLocation(View view) {
        System.out.println("By Location");
        this.selectionMode = 1;

    }

    public void searchByArtists(View view) {
        System.out.println("By Artist");
        this.selectionMode = 0;
    }
}