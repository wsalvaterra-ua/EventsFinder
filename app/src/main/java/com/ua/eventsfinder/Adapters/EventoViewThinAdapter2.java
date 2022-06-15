package com.ua.eventsfinder.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.ua.eventsfinder.Objetos.EventoArtista;
import com.ua.eventsfinder.R;

import java.util.ArrayList;

import ru.blizzed.opensongkick.models.Event;

public class EventoViewThinAdapter2 extends RecyclerView.Adapter<EventoViewThinAdapter2.MyViewHolder> {
    private Context context;
    private final ArrayList<Object> lista;

    public EventoViewThinAdapter2(Context context, ArrayList<Object> lista_) {
        this.context = context;
        this.lista = lista_;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate((R.layout.frame_evento_thin),parent,false);
        return new EventoViewThinAdapter2.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(lista.get(position) instanceof  Event)
            handleEvent(holder,(Event) lista.get(position));

    }

    private void  handleEvent(MyViewHolder holder, Event evento){
        holder.titulo.setText(evento.getDisplayName());
        holder.data.setText( evento.getStart().getDate());
        holder.localizacao.setText( evento.getLocation().getCity());

        int idParaFoto =(int) ((evento.getType() ==Event.Type.CONCERT )?
                evento.getPerformances().get(0).getArtist().getId():evento.getId());
        String tipodeEventoLink = ((evento.getType() ==Event.Type.CONCERT )? "artists":"events");

        String url = "https://images.sk-static.com/images/media/profile_images/"+tipodeEventoLink+"/"+idParaFoto+"/huge_avatar";
//        System.out.println(eventos.get(position).getDisplayName());
//        System.out.println(url);
//        System.out.println((new Gson()).toJson(eventos.get(position)));
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
        public TextView localizacao;
        public ImageView imageView;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = (TextView) itemView.findViewById(R.id.eventTitleTextView);
            data = (TextView) itemView.findViewById(R.id.eventDateTextView);
            localizacao = (TextView) itemView.findViewById(R.id.eventLocationTextView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            view = itemView;
        }
    }
}
