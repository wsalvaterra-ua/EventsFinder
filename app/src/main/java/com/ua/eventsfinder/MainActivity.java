package com.ua.eventsfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ua.eventsfinder.Atividades.eventActivity;
import com.ua.eventsfinder.Fragmentos.FollowingFragment;
import com.ua.eventsfinder.Fragmentos.HomeFragment;
import com.ua.eventsfinder.Fragmentos.SearchFragment;
import com.ua.eventsfinder.Objetos.Evento;


import java.net.InetAddress;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//
        setContentView(R.layout.activity_main);

         BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        replaceFramework(new SearchFragment());
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

        Intent intent = new Intent(this, eventActivity.class);
        this.startActivity(intent);
    }
    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");
            //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }
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
        super.onCreateOptionsMenu(menu);
         super.getMenuInflater().inflate(R.menu.main_appbar, menu);

        return true;
    }
}