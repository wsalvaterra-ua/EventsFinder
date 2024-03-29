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
import com.ua.eventsfinder.Objetos.DateType;
import com.ua.eventsfinder.R;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import api.blizzed.opensongkick.models.Artist;
import api.blizzed.opensongkick.models.Event;


public class LargeAdapter extends RecyclerView.Adapter<LargeAdapter.MyViewHolder> {
    private final ArrayList<Object> lista;
    Context context;
    public int maxSize;
    public Boolean gradientFundoBaixo;
    public Boolean gradientFundoCima;
    public  boolean hideDateTextView;

    public LargeAdapter(ArrayList<Object> lista, Context context) {
        this.lista = lista;
        this.context = context;
        this.maxSize = 400;
        this.hideDateTextView = false;
        this.gradientFundoBaixo = true;
        this.gradientFundoCima = true;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view =  LayoutInflater.from(context).inflate(R.layout.large_view_for_recycler_view,parent,false);
        return new LargeAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if(lista.get(position) instanceof  Event)
            handleEvent(holder,(Event) lista.get(position));
        else if(lista.get(position) instanceof  Artist)
            handleArtist(holder,(Artist) lista.get(position));
        holder.setObjetoAEnviar(lista.get(position),context);
        if(hideDateTextView)
            holder.data.setVisibility(View.GONE);
        if(!gradientFundoBaixo)
            holder.gradientFundoBaixo.setVisibility(View.GONE);
        if(!gradientFundoCima)
            holder.gradientFundoCima.setVisibility(View.GONE);
        holder.titulo.setMaxWidth(maxSize);
        holder.data.setMaxWidth(maxSize);

    }

    private void  handleEvent(LargeAdapter.MyViewHolder holder, Event evento){

        String full = evento.getDisplayName();

        String titulo = (full.lastIndexOf(" at ")>0) ?
                full.substring(0,full.lastIndexOf(" at ")):evento.getDisplayName();

        holder.titulo.setText(titulo);
        holder.data.setText( dateToHuman(evento.getStart().getDate(),DateType.FULL_DATE));

        int idParaFoto =(int) ((evento.getType() ==Event.Type.CONCERT )?
                evento.getPerformances().get(0).getArtist().getId():evento.getId());
        String tipodeEventoLink = ((evento.getType() ==Event.Type.CONCERT )? "artists":"events");

        String url = "https://images.sk-static.com/images/media/profile_images/"+tipodeEventoLink+"/"+idParaFoto+"/huge_avatar";

        Picasso.get()
                .load(R.drawable.default_event)
                .resize(maxSize,0).into(holder.imageViewHolder);

        Picasso.get()
                .load(url).resize(maxSize,0).into(holder.imageView);

    }
    private void  handleArtist(LargeAdapter.MyViewHolder holder, Artist artist){
        holder.titulo.setText(artist.getDisplayName());
        holder.data.setText((artist.getOnTourUntil() !=null) ? "Touring until: " +dateToHuman(artist.getOnTourUntil(),DateType.FULL_DATE):"Not touring");

        String url = "https://images.sk-static.com/images/media/profile_images/artists/"+artist.getId()+"/huge_avatar";

        Picasso.get()
                .load(R.drawable.default_event)
                .resize(maxSize,0).into(holder.imageViewHolder);

        Picasso.get()
                .load(url).resize(maxSize,0).into(holder.imageView);

    }
    private static String dateToHuman(String sdate , DateType dateType){
        LocalDate localDate =  LocalDate.parse(sdate);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
        switch (dateType){

            case DAY:
                formatter = DateTimeFormatter.ofPattern("d");
                break;
            case MONTH:
                formatter = DateTimeFormatter.ofPattern("MMM");
                break;
            case YEAR:
                formatter = DateTimeFormatter.ofPattern("yyyy");
                break;
            case FULL_DATE:
                DateTimeFormatter.ofPattern("MMM d, yyyy");
                break;
        }



      return  localDate.format(formatter);
    }


    @Override
    public int getItemCount() {
        return lista.size();
    }


    public  static class MyViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{
        private Object objetoAEnviar;
        private  Context context;
        public TextView titulo;
        public View gradientFundoBaixo;
        public View gradientFundoCima;
        public TextView data;

        public ImageView imageView;
        public ImageView imageViewHolder;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            objetoAEnviar = null;
            gradientFundoBaixo =  itemView.findViewById(R.id.gradientMediumPhotoLayoutBottom);
            gradientFundoCima =  itemView.findViewById(R.id.gradientMediumPhotoLayout);
            titulo = (TextView) itemView.findViewById(R.id.titleTextView);
            data = (TextView) itemView.findViewById(R.id.dateTextView);
            imageViewHolder = (ImageView) itemView.findViewById(R.id.imageViewHolder);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

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
            context.startActivity(intent);
        }
    }
}
