package com.ua.eventsfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.Menu;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ua.eventsfinder.Fragmentos.FollowingFragment;
import com.ua.eventsfinder.Fragmentos.HomeFragment;
import com.ua.eventsfinder.Fragmentos.SearchFragment;
import com.ua.eventsfinder.Objetos.Evento;
import com.ua.eventsfinder.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar mainToolbar;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_main);
         BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        replaceFramework(new HomeFragment());
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

//        setSupportActionBar((Toolbar) findViewById(R.id.mainToolbar));
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMain);
//        ArrayList<Evento> eventos= new ArrayList<>();
//        setEventos(eventos);
//        eventosRecyclerViewAdapter adapter = new eventosRecyclerViewAdapter(this,eventos);
//        recyclerView.setAdapter(adapter);
    }

    private void replaceFramework(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);

        transaction.commit();
    }
    private void  setEventos(ArrayList<Evento> eventos){
        String[] eventosTitulo = getResources().getStringArray(R.array.titulos);
        String[] eventosData = getResources().getStringArray(R.array.datas);
        String[] eventosLocalizacao = getResources().getStringArray(R.array.localizacoes);
        for (int i =0 ;i<eventosData.length;i++)
            eventos.add(new Evento(eventosTitulo[i] ,eventosData[i],eventosLocalizacao[i] ));

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
         super.getMenuInflater().inflate(R.menu.main_appbar, menu);
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayShowHomeEnabled(false);
//        menu.
        return true;
    }
}