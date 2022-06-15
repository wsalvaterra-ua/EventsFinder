package com.ua.eventsfinder.Atividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.ua.eventsfinder.Adapters.EventoViewLargeGridAdapter2;
import com.ua.eventsfinder.Adapters.EventoViewThinAdapter;
import com.ua.eventsfinder.Fragmentos.FollowingFragment;
import com.ua.eventsfinder.Fragmentos.HomeFragment;
import com.ua.eventsfinder.Fragmentos.SearchFragment;
import com.ua.eventsfinder.Objetos.Evento;
import com.ua.eventsfinder.Objetos.EventoArtista;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.SongKickApi;
import ru.blizzed.opensongkick.models.Artist;
import ru.blizzed.opensongkick.models.ResultsPage;
import ru.blizzed.opensongkick.params.SongKickParams;

public class SearchResultsActivity extends AppCompatActivity {
private final SearchResultsActivity mContext;

    public SearchResultsActivity() {
        this.mContext = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_search_results);
        setSupportActionBar(findViewById(R.id.topBar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.searchBtn);

        bottomNavigationView.setOnItemSelectedListener(item ->{
            switch(item.getItemId()) {
                case R.id.homeBtn:
                    replaceFramework(new HomeFragment());
                    break;
                case R.id.searchBtn:
                    replaceFramework(new SearchFragment());
                    break;
                case R.id.followingBtn:
                    replaceFramework(new FollowingFragment());
                    break;
            }
            return  true;
        });

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSearchHistory);
        ArrayList<EventoArtista> eventos= new ArrayList<>();
        setEventos(eventos);
        EventoViewThinAdapter adapter = new EventoViewThinAdapter(this,eventos);
        recyclerView.setAdapter(adapter);
    }

    private void  setEventos(ArrayList<EventoArtista> eventos){
        String[] eventosTitulo = getResources().getStringArray(R.array.titulos);
        String[] eventosData = getResources().getStringArray(R.array.datas);
        String[] eventosLocalizacao = getResources().getStringArray(R.array.localizacoes);
        for (int i =0 ;i<eventosData.length;i++)
            eventos.add(new Evento(eventosTitulo[i] ,eventosData[i],eventosLocalizacao[i] ));

    }

    private void replaceFramework(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);

        transaction.commit();
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
                System.out.println(newText);


                    SongKickApi.artistSearch().byName(newText)
                            .execute(new ApiCaller.Listener<ResultsPage<Artist>>() {
                                @Override
                                public void onComplete(ResultsPage<Artist> result, ApiCaller<ResultsPage<Artist>> apiCaller) {
                                    ArrayList<Object> eventos = new ArrayList(result.getResults().subList(0,35));
                                    RecyclerView recyclerView = (RecyclerView) mContext.findViewById(R.id.fragmentSearchRecyclerViewMain);
                                    System.out.println(new Gson().toJson(eventos));
//                                    EventoViewLargeGridAdapter2 adapter = new EventoViewLargeGridAdapter2(eventos,mContext);
//                                    recyclerView.setAdapter(adapter);
                                }
                            });



                return false;
            }

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

        });
        return true;
    }

}