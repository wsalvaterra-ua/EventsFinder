package com.ua.eventsfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.ua.eventsfinder.Atividades.artistActivity;
import com.ua.eventsfinder.Atividades.eventActivity;
import com.ua.eventsfinder.Fragmentos.FollowingFragment;
import com.ua.eventsfinder.Fragmentos.HomeFragment;
import com.ua.eventsfinder.Fragmentos.SearchFragment;
import com.ua.eventsfinder.Objetos.Evento;
import com.ua.eventsfinder.Objetos.GpsTracker;


import java.net.InetAddress;
import java.util.ArrayList;

import ru.blizzed.opensongkick.ApiCallException;
import ru.blizzed.opensongkick.ApiErrorException;
import ru.blizzed.opensongkick.OpenSongKickContext;
import ru.blizzed.opensongkick.SongKickApi;
import ru.blizzed.opensongkick.models.Event;
import ru.blizzed.opensongkick.params.SongKickParams;

public class MainActivity extends AppCompatActivity {
    private Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        replaceFramework(new HomeFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
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
            return true;
        });

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 101);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//        Intent intent = new Intent(this, artistActivity.class);
//        this.startActivity(intent);

    }

    private void replaceFramework(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frameLayout, fragment);

        transaction.commit();
    }

    private void setEventos(ArrayList<Evento> eventos) {
        String[] eventosTitulo = getResources().getStringArray(R.array.titulos);
        String[] eventosData = getResources().getStringArray(R.array.datas);
        String[] eventosLocalizacao = getResources().getStringArray(R.array.localizacoes);
        for (int i = 0; i < eventosData.length; i++)
            eventos.add(new Evento(eventosTitulo[i], eventosData[i], eventosLocalizacao[i]));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        super.getMenuInflater().inflate(R.menu.main_appbar, menu);

        return true;
    }
}