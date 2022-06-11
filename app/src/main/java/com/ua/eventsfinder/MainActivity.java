package com.ua.eventsfinder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;
import com.ua.eventsfinder.Fragmentos.FollowingFragment;
import com.ua.eventsfinder.Fragmentos.HomeFragment;
import com.ua.eventsfinder.Fragmentos.SearchFragment;
import com.ua.eventsfinder.Objetos.Evento;


import java.net.InetAddress;
import java.util.ArrayList;

import ru.blizzed.opensongkick.ApiCallException;
import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.ApiErrorException;
import ru.blizzed.opensongkick.OpenSongKickContext;
import ru.blizzed.opensongkick.SongKickApi;
import ru.blizzed.opensongkick.models.Location;
import ru.blizzed.opensongkick.models.ResultsPage;
import ru.blizzed.opensongkick.params.SongKickParams;

public class MainActivity extends AppCompatActivity {
    private Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_artist);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        double lat = 48.1550547;
        double lon = 11.4017526;

        System.out.print("ddd" + Boolean.toString(isInternetAvailable()) + " SD\n\n\n\n\n\nkkkkkkkkkkk\n\n\n\n\n" +
                "\n\n\n\n\n\n\n\n\n\n" +
                "\n\n\n\n\n" +
                "\n\n\n\n\n\n\n\n\n\n" +
                "\n\n\n\n\n" +
                "\n\n\n\n\n" +
                "\n\n\n\n\n" +
                "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" +
                "kLLLL\ndddddddddd");
        OpenSongKickContext.initialize("lKLDro9R9AqqXm1b");

        SongKickApi.locationSearch().byQuery("Portugal").execute(new ApiCaller.Listener<ResultsPage<Location>>() {
            @Override
            public void onComplete(ResultsPage<Location> result, ApiCaller<ResultsPage<Location>> apiCaller) {
                System.out.println("Resulta:" + (new Gson()).toJson( result.getResults()));

            }

            public void onError(Error error, ApiCaller<ResultsPage<Location>> apiCaller) {
                /* This method triggers you when API has been called but response contains an error */
                // Handle Api Error
                System.out.println("Ressadasasdulta:");
            }
            @Override
            public void onFailure(ApiCallException e, ApiCaller<ResultsPage<Location>> apiCaller) {
                /* This method triggers you when call to API cannot be established. E.g. no internet connection */
                // Handle Failure
                System.out.println("REEesultdadsaa:");
            }
        });

//         BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
//        replaceFramework(new SearchFragment());
//         bottomNavigationView.setOnItemSelectedListener(item ->{
//             switch(item.getItemId()) {
//                 case R.id.homeBtn:
//                    replaceFramework(new HomeFragment());
//                 break;
//                 case R.id.searchBtn:
//                    replaceFramework(new SearchFragment());
//                 break;
//                 case R.id.followingBtn:
//                    replaceFramework(new FollowingFragment());
//                 break;
//             }
//             return  true;
//         });

//        setSupportActionBar((Toolbar) findViewById(R.id.mainToolbar));
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMain);
//        ArrayList<Evento> eventos= new ArrayList<>();
//        setEventos(eventos);
//        eventosRecyclerViewAdapter adapter = new eventosRecyclerViewAdapter(this,eventos);
//        recyclerView.setAdapter(adapter);
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
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
         super.getMenuInflater().inflate(R.menu.main_appbar, menu);
//        ActionBar actionBar = getActionBar();
//        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.setDisplayShowHomeEnabled(false);
//        men
        return true;
    }
}