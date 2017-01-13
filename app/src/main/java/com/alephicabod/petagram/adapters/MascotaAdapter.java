package com.alephicabod.petagram.adapters;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alephicabod.petagram.R;
import com.alephicabod.petagram.db.ConstructorMascotas;
import com.alephicabod.petagram.models.Mascota;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by angel on 02/01/2017.
 */

public class MascotaAdapter extends RecyclerView.Adapter<MascotaAdapter.MascotaViewHolder> {
    private ArrayList<Mascota> mascotas;
    private Activity activity;

    public MascotaAdapter(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder holder, int position) {
        final Mascota m=mascotas.get(position);
        //holder.foto.setImageResource(m.getPicture());
        Picasso.with(activity)
                .load(m.getPicture())
                .placeholder(R.drawable.cat_footprint_48)
                .into(holder.foto);

        if(position%2==0)
            holder.foto.setBackgroundResource(R.color.fondo1);
        else
            holder.foto.setBackgroundResource(R.color.fondo2);
            Calendar c=Calendar.getInstance();
        c.setTimeInMillis(Long.parseLong(m.getFecha())*1000);
        SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yy hh:mm:ss");
        holder.fecha.setText(sdf.format(c.getTime()));
        holder.nombre.setText(m.getId());
        holder.votos.setText(m.getVotos()+"");

        holder.iconoFavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity,m.getId_foto(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto,iconoFavs;
        private TextView nombre,votos,fecha;


        public MascotaViewHolder(View itemView) {
            super(itemView);
            foto=(ImageView) itemView.findViewById(R.id.foto);
            nombre=(TextView)itemView.findViewById(R.id.nombre);
            votos=(TextView)itemView.findViewById(R.id.votos);
            fecha=(TextView)itemView.findViewById(R.id.fecha);
            iconoFavs=(ImageView)itemView.findViewById(R.id.iconoFavs);
        }
    }
}
