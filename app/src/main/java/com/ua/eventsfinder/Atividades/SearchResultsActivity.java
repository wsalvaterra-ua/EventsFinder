package com.ua.eventsfinder.Atividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ua.eventsfinder.Fragmentos.FollowingFragment;
import com.ua.eventsfinder.Fragmentos.HomeFragment;
import com.ua.eventsfinder.Fragmentos.SearchFragment;
import com.ua.eventsfinder.R;

public class SearchResultsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
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
        searchView.setQueryHint("Search Events, Artists, Locations");
        searchView.requestFocusFromTouch();


        return true;
    }

}