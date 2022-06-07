package com.ua.eventsfinder.Fragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ua.eventsfinder.Adapters.EventoViewLargeGridAdapter;
import com.ua.eventsfinder.Adapters.EventoViewThinAdapter;
import com.ua.eventsfinder.Objetos.Evento;
import com.ua.eventsfinder.Objetos.EventoArtista;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.fragmentSearchRecyclerViewMain);
        ArrayList<EventoArtista> eventos= new ArrayList<>();
        setEventos(eventos);
        EventoViewLargeGridAdapter eventoViewLargeGridAdapter = new EventoViewLargeGridAdapter(eventos,view.getContext());
        recyclerView.setAdapter(eventoViewLargeGridAdapter);
        EventoViewLargeGridAdapter eventoViewLargeGridAdapterHistory = new EventoViewLargeGridAdapter(eventos,view.getContext());
        ((RecyclerView)view.findViewById(R.id.recyclerViewSearchHistory)).setAdapter(eventoViewLargeGridAdapter);
        return  view;
    }

    private void openSearchBar(View view){
//        Intent intent = new Intent()

    }
    private void  setEventos(ArrayList<EventoArtista> eventos){
        String[] eventosTitulo = getResources().getStringArray(R.array.titulos);
        String[] eventosData = getResources().getStringArray(R.array.datas);
        String[] eventosLocalizacao = getResources().getStringArray(R.array.localizacoes);
        for (int i =0 ;i<eventosData.length;i++)
            eventos.add(new Evento(eventosTitulo[i] ,eventosData[i],eventosLocalizacao[i] ));

    }
}