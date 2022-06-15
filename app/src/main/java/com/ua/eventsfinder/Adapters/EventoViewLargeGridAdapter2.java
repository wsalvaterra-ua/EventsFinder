package com.ua.eventsfinder.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

import ru.blizzed.opensongkick.models.Artist;
import ru.blizzed.opensongkick.models.Event;

public class EventoViewLargeGridAdapter2 extends RecyclerView.Adapter<EventoViewLargeGridAdapter2.MyViewHolder> {
    private final ArrayList<Object> lista;
    private LayoutInflater inflater;
    Context context;

    public EventoViewLargeGridAdapter2(ArrayList<Object> lista, Context context) {
        this.lista = lista;
        this.context = context;
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

        if(lista.get(position) instanceof  Event)
            handleEvent(holder,(Event) lista.get(position));
        else if(lista.get(position) instanceof  Artist)
            handleArtist(holder,(Artist) lista.get(position));
    }

    private void  handleEvent(EventoViewLargeGridAdapter2.MyViewHolder holder, Event evento){

        String full = evento.getDisplayName();

        String titulo = (full.lastIndexOf(" at ")>0) ?
                full.substring(0,full.lastIndexOf(" at ")):evento.getDisplayName();

        String data =(full.lastIndexOf("(")>0)? full.substring(
                full.lastIndexOf("(")+1,full.length()-1)
                :evento.getStart().getDate();
        holder.titulo.setText(titulo);
        holder.data.setText( data);

        int idParaFoto =(int) ((evento.getType() ==Event.Type.CONCERT )?
                evento.getPerformances().get(0).getArtist().getId():evento.getId());
        String tipodeEventoLink = ((evento.getType() ==Event.Type.CONCERT )? "artists":"events");

        String url = "https://images.sk-static.com/images/media/profile_images/"+tipodeEventoLink+"/"+idParaFoto+"/huge_avatar";

        Picasso.get()
                .load(url).placeholder(R.drawable.default_event).error(R.drawable.default_event)
                .into(holder.imageView);
    }
    private void  handleArtist(EventoViewLargeGridAdapter2.MyViewHolder holder, Artist artist){
        holder.titulo.setText(artist.getDisplayName());
        holder.data.setText((artist.getOnTourUntil() !=null) ? "Touring until: " + artist.getOnTourUntil():"Not touring");

        String url = "https://images.sk-static.com/images/media/profile_images/artists/"+artist.getId()+"/huge_avatar";
        Picasso.get()
                .load(url).placeholder(R.drawable.default_event).error(R.drawable.default_event)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


    public  static class MyViewHolder extends  RecyclerView.ViewHolder{

        private  View view;
        public TextView titulo;
        public TextView data;
        public ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.titleTextView);
            data = (TextView) itemView.findViewById(R.id.dateTextView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            view = itemView;
        }
    }
}
