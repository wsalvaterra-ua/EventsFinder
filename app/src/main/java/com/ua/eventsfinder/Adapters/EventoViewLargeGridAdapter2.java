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

public class EventoViewLargeGridAdapter2 extends RecyclerView.Adapter<EventoViewLargeGridAdapter2.MyViewHolder> {
    private final ArrayList<EventoArtista> eventoArtistaArrayList;
    private LayoutInflater inflater;
    Context context;


    public EventoViewLargeGridAdapter2(ArrayList<EventoArtista> eventoArtistaArrayList, Context context) {
        this.eventoArtistaArrayList = eventoArtistaArrayList;
        this.context = context;
    }

    public View getView(int position, View view, ViewGroup viewGroup) {
        if(inflater == null)
            inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(view == null) 
            view = inflater.inflate(R.layout.frame_recyclerview_eventoslarge,null);

        TextView title = view.findViewById(R.id.titleTextView);
        TextView date =  view.findViewById(R.id.dateTextView);
        title.setText(this.eventoArtistaArrayList.get(position).titulo);
        title.setText(this.eventoArtistaArrayList.get(position).data);
        return  view;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view =  LayoutInflater.from(context).inflate(R.layout.frame_recyclerview_eventoslarge,parent,false);
        return new EventoViewLargeGridAdapter2.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.titulo.setText(eventoArtistaArrayList.get(position).titulo);
        holder.data.setText(eventoArtistaArrayList.get(position).data);

    }

    @Override
    public int getItemCount() {
        return eventoArtistaArrayList.size();
    }


    public  static class MyViewHolder extends  RecyclerView.ViewHolder{

        private  View view;
        public TextView titulo;
        public TextView data;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.titleTextView);
            data = (TextView) itemView.findViewById(R.id.dateTextView);
            view = itemView;
        }
    }
}
