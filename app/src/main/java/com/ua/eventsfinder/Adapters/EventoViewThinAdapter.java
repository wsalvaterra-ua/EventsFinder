package com.ua.eventsfinder.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.ua.eventsfinder.Atividades.artistActivity;
import com.ua.eventsfinder.Atividades.eventActivity;
import com.ua.eventsfinder.Atividades.locationActivity;
import com.ua.eventsfinder.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import api.blizzed.opensongkick.models.Artist;
import api.blizzed.opensongkick.models.Event;
import api.blizzed.opensongkick.models.Location;
import api.blizzed.opensongkick.models.MetroArea;

public class EventoViewThinAdapter extends RecyclerView.Adapter<EventoViewThinAdapter.MyViewHolder> {
    private Context context;
    private final ArrayList<Object> lista;

    public EventoViewThinAdapter(Context context, ArrayList<Object> lista_) {
        this.context = context;
        this.lista = lista_;
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
        if(lista.get(position) instanceof  Event)
            handleEvent(holder,(Event) lista.get(position));
        else if(lista.get(position) instanceof  Artist)
            handleArtist(holder,(Artist) lista.get(position));
        else if(lista.get(position) instanceof  Location)
            handleLocation(holder,((Location) lista.get(position)).getMetroArea());
        holder.setObjetoAEnviar(lista.get(position),context);

    }
    private void handleLocation(EventoViewThinAdapter.MyViewHolder holder, MetroArea location){

        holder.titulo.setText(location.getDisplayName());
        holder.data.setText(location.getCountry().getDisplayName());
        holder.localizacao.setText("");
//        holder.imageView.setMaxHeight(130);
        Picasso.get()
                .load(R.drawable.location_small).resize(150,0).into(holder.imageView);
    }


    private void  handleEvent(EventoViewThinAdapter.MyViewHolder holder, Event evento){
        String full = evento.getDisplayName();
        String titulo = (full.lastIndexOf(" at ")>0) ?
                full.substring(0,full.lastIndexOf(" at ")):evento.getDisplayName();
        String localizacao =evento.getVenue().getDisplayName() + ", " + evento.getVenue().getMetroArea().getDisplayName();
        String data =dateToHuman(evento.getStart().getDate());
        holder.titulo.setText(titulo);
        holder.data.setText( data);

        holder.localizacao.setText( localizacao);
        holder.imageView.setMaxHeight(1000);
        int idParaFoto =(int) ((evento.getType() ==Event.Type.CONCERT )?
                evento.getPerformances().get(0).getArtist().getId():evento.getId());
        String tipodeEventoLink = ((evento.getType() ==Event.Type.CONCERT )? "artists":"events");

        String url = "https://images.sk-static.com/images/media/profile_images/"+tipodeEventoLink+"/"+idParaFoto+"/huge_avatar";

        Picasso.get()
                .load(url).placeholder(R.drawable.default_event).error(R.drawable.default_event)
                .into(holder.imageView);

    }

    private void  handleArtist(MyViewHolder holder, Artist artist){

        holder.titulo.setText(artist.getDisplayName());
        holder.data.setText((artist.getOnTourUntil() !=null) ?  "Touring until: " + dateToHuman(artist.getOnTourUntil()):"Not touring");
        holder.localizacao.setText( "");
        holder.imageView.setMaxHeight(1000);
        String url = "https://images.sk-static.com/images/media/profile_images/artists/"+artist.getId()+"/huge_avatar";
        Picasso.get()
                .load(url).placeholder(R.drawable.default_event).error(R.drawable.default_event)
                .into(holder.imageView);

    }

private String dateToHuman(String sdate){
    Date date_;
    try {
        date_ = new SimpleDateFormat("yyyy-MM-dd").parse(sdate);
    } catch (ParseException e) {
        return  sdate;
    }
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, yyyy");
    return  simpleDateFormat.format(date_);

}

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public  static class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        private Object objetoAEnviar;
        private  Context context;
        private  View view;
        public TextView titulo;
        public TextView data;
        public TextView localizacao;
        public ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            titulo = (TextView) itemView.findViewById(R.id.titleTextView);
            data = (TextView) itemView.findViewById(R.id.dateTextView);
            localizacao = (TextView) itemView.findViewById(R.id.locationTextView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            view = itemView;
        }

        public void setObjetoAEnviar(Object objetoAEnviar , Context context) {
            this.context = context;
            this.objetoAEnviar = objetoAEnviar;
        }
        @Override
        public void onClick(View view) {
            Intent intent = null;
            if(objetoAEnviar instanceof  Event) {
                intent = new Intent(context,  eventActivity.class);
                intent.putExtra("event", (new Gson()).toJson(objetoAEnviar));
            }
            else if(objetoAEnviar instanceof  Artist) {
                intent = new Intent(context,  artistActivity.class);
                intent.putExtra("artist", (new Gson()).toJson(objetoAEnviar));
            }
            else if(objetoAEnviar instanceof  Location) {
                intent = new Intent(context,  locationActivity.class);
                intent.putExtra("location", (new Gson()).toJson(((Location) objetoAEnviar)));
            }
            context.startActivity(intent);
        }
    }
}
