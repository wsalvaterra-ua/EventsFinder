package com.ua.eventsfinder.Fragmentos;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.ua.eventsfinder.Adapters.EventoViewThinAdapter;
import com.ua.eventsfinder.DataBase.Location.FavoriteLocation;
import com.ua.eventsfinder.DataBase.MyRoomDatabase;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

import ru.blizzed.opensongkick.models.Location;
import ru.blizzed.opensongkick.models.MetroArea;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FollowingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowingFragment extends Fragment {
    private final Context mContext;
    private MyRoomDatabase myRoomDatabase;
    private EventoViewThinAdapter eventoViewThinAdapter;
    private ArrayList<Object> objectArrayList;
    private int selectionMode;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FollowingFragment() {
        this.mContext = this.getContext();
        this.objectArrayList =  new ArrayList<>();

        this.selectionMode = 0;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FollowingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FollowingFragment newInstance(String param1, String param2) {
        FollowingFragment fragment = new FollowingFragment();
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
        View view =  inflater.inflate(R.layout.fragment_following, container, false);

        myRoomDatabase = MyRoomDatabase.getDbInstance(view.getContext());
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewMain);
        this.eventoViewThinAdapter = new EventoViewThinAdapter(view.getContext(), objectArrayList);
        recyclerView.setAdapter(eventoViewThinAdapter);
        loadFavoriteLocation(view);
        return  view;
    }

    private void loadFavoriteLocation(View view){
        ArrayList<Object> loadedLocations = new ArrayList<>();
        Gson gson  = new Gson();
        ArrayList<FavoriteLocation> favoriteLocationArrayList = new ArrayList<>(myRoomDatabase.favoriteLocationDAO().getAll());


        for (FavoriteLocation favoriteLocation:favoriteLocationArrayList)
            loadedLocations.add(gson.fromJson(favoriteLocation.getLocation(),Location.class));

//        this.objectArrayList.clear();
        this.objectArrayList.addAll(loadedLocations);
        System.out.println(gson.toJson(loadedLocations));
        this.eventoViewThinAdapter.notifyDataSetChanged();
    }
}