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
import com.ua.eventsfinder.R;

import java.util.ArrayList;

import ru.blizzed.opensongkick.models.Artist;
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
        else if(lista.get(position) instanceof  Artist)
            handleArtist(holder,(Artist) lista.get(position));
        holder.setObjetoAEnviar(lista.get(position),context);

    }

    private void  handleEvent(EventoViewThinAdapter2.MyViewHolder holder, Event evento){
        String full = evento.getDisplayName();
        String titulo = (full.lastIndexOf(" at ")>0) ?
                full.substring(0,full.lastIndexOf(" at ")):evento.getDisplayName();
        String localizacao =(full.lastIndexOf(" at ")>0&&full.lastIndexOf("(") >0)
                ? full.substring(
                full.lastIndexOf(" at ")+4,full.lastIndexOf("(")-1
        )
                :evento.getLocation().getDisplayName();
        String data =(full.lastIndexOf("(")>0)? full.substring(
                full.lastIndexOf("(")+1,full.length()-2)
                :evento.getStart().getDate();
        holder.titulo.setText(titulo);
        holder.data.setText( data);

        holder.localizacao.setText( localizacao);

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
        holder.data.setText((artist.getOnTourUntil() !=null) ? "Touring until: " + artist.getOnTourUntil():"Not touring");
        holder.localizacao.setText( "");
        String url = "https://images.sk-static.com/images/media/profile_images/artists/"+artist.getId()+"/huge_avatar";
        Picasso.get()
                .load(url).placeholder(R.drawable.default_event).error(R.drawable.default_event)
                .into(holder.imageView);

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
            context.startActivity(intent);
        }
    }
}