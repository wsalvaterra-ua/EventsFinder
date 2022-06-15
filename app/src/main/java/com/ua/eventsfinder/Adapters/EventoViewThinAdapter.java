package com.ua.eventsfinder.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ua.eventsfinder.Objetos.EventoArtista;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

public class EventoViewThinAdapter extends RecyclerView.Adapter<EventoViewThinAdapter.MyViewHolder> {
    private Context context;
    private final ArrayList<EventoArtista> eventos;

    public EventoViewThinAdapter(Context context, ArrayList<EventoArtista> eventos_) {
        this.context = context;
        this.eventos = eventos_;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate((R.layout.frame_evento_thin),parent,false);
        return new EventoViewThinAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.titulo.setText(eventos.get(position).titulo);
        holder.data.setText(eventos.get(position).data);
        holder.localizacao.setText(eventos.get(position).localizacao);
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public  static class MyViewHolder extends  RecyclerView.ViewHolder{

        private  View view;
        public TextView titulo;
        public TextView data;
        public TextView localizacao;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.titleTextView);
            data = (TextView) itemView.findViewById(R.id.dateTextView);
            localizacao = (TextView) itemView.findViewById(R.id.locationTextView);

            view = itemView;
        }
    }
}
