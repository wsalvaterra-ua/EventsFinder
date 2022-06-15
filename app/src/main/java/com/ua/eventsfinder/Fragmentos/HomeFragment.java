package com.ua.eventsfinder.Fragmentos;

import static java.util.stream.Collectors.toCollection;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.ua.eventsfinder.Adapters.EventoViewThinAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ua.eventsfinder.Adapters.EventoViewThinAdapter2;
import com.ua.eventsfinder.Objetos.Evento;
import com.ua.eventsfinder.Objetos.EventoArtista;
import com.ua.eventsfinder.Objetos.GpsTracker;
import com.ua.eventsfinder.R;

import java.util.ArrayList;
import java.util.List;

import ru.blizzed.opensongkick.ApiCallException;
import ru.blizzed.opensongkick.ApiCaller;
import ru.blizzed.opensongkick.ApiErrorException;
import ru.blizzed.opensongkick.OpenSongKickContext;
import ru.blizzed.opensongkick.SongKickApi;
import ru.blizzed.opensongkick.models.Artist;
import ru.blizzed.opensongkick.models.Event;
import ru.blizzed.opensongkick.models.ResultsPage;
import ru.blizzed.opensongkick.params.SongKickParams;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private  double latitude,longitude;
    public HomeFragment() {
        this.latitude = 0;
        this.longitude = 0;
        OpenSongKickContext.initialize("lKLDro9R9AqqXm1b");
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        OpenSongKickContext.initialize("lKLDro9R9AqqXm1b");
        try {
            SongKickApi.artistSearch().byName("Justin").execute().getResults().forEach(System.out::println);
        } catch (ApiCallException | ApiErrorException e) {
            // Handle error
            System.out.println(new Gson().toJson(e));
        }
        SongKickApi.artistSearch().byName("Justin")
                .execute(new ApiCaller.Listener<ResultsPage<Artist>>() {
                    @Override
                    public void onComplete(ResultsPage<Artist> result, ApiCaller<ResultsPage<Artist>> apiCaller) {
//                        ArrayList<Object> eventos = new ArrayList(result.getResults().subList(0,35));
                        System.out.println(new Gson().toJson(result.getResults()));
                        System.out.println("Olaa");
                        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragmentSearchRecyclerViewMain);
//                                    EventoViewLargeGridAdapter2 adapter = new EventoViewLargeGridAdapter2(eventos,mContext);
//                                    recyclerView.setAdapter(adapter);
                    }
                    public void onError(Error error, ApiCaller<ResultsPage<Artist>> apiCaller) {
                        System.out.println(error.getCause());
                        /* This method triggers you when API has been called but response contains an error */
                        // Handle Api Error
                    }
                    @Override
                    public void onFailure(ApiCallException e, ApiCaller<ResultsPage<Artist>> apiCaller) {
                        /* This method triggers you when call to API cannot be established. E.g. no internet connection */
                        // Handle Failure
                        System.out.println(e.getCause());
                    }
                });


        getLocation(view);
        return  view;
    }


    public void getLocation(View view){
        GpsTracker gpsTracker = new GpsTracker(view.getContext());
        if(gpsTracker.canGetLocation()){
            this.latitude = gpsTracker.getLatitude();
            this.longitude = gpsTracker.getLongitude();
//
//            System.out.println("Lat: " + String.valueOf(latitude));
//            System.out.println("Long: " + String.valueOf(longitude));
            getByloc(view);
        }else{
//            System.out.println("Erro");
            gpsTracker.showSettingsAlert();

        }
    }

    public void getByloc(View view){


    Evento evento;
             SongKickApi.eventSearch()
                    .byLocation(SongKickParams.LOCATION_GEO.of(latitude, longitude))
                    .execute(new ApiCaller.Listener<ResultsPage<Event>>() {
                        @Override
                        public void onComplete(ResultsPage<Event> result, ApiCaller<ResultsPage<Event>> apiCaller) {

                            ArrayList<Object> eventos = new ArrayList(result.getResults());
                            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewMain);

                            EventoViewThinAdapter2 adapter = new EventoViewThinAdapter2(view.getContext(),eventos);
                            recyclerView.setAdapter(adapter);
                        }
                    });
    }
}