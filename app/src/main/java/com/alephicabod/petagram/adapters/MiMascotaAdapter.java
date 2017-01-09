package com.alephicabod.petagram.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alephicabod.petagram.R;
import com.alephicabod.petagram.models.Foto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by angel on 03/01/2017.
 */

public class MiMascotaAdapter extends RecyclerView.Adapter<MiMascotaAdapter.MiMascotaHolderView> {

    private ArrayList<Foto> fotos;
    private Activity context;
    public MiMascotaAdapter(ArrayList<Foto> fotos,Activity a) {
        this.fotos = fotos;this.context=a;
    }

    @Override
    public MiMascotaAdapter.MiMascotaHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mi_mascota,parent,false);
        return new MiMascotaHolderView(v);
    }

    @Override
    public void onBindViewHolder(MiMascotaAdapter.MiMascotaHolderView holder, int position) {
        Foto x=fotos.get(position);
        Picasso.with(context)
                .load(x.getUrl())
                .placeholder(R.drawable.cat_footprint_48)
                .into(holder.foto);
        holder.votos.setText(x.getVotos()+"");
    }

    @Override
    public int getItemCount() {
//        return fotos.size();
        return fotos.size();
    }

    public class MiMascotaHolderView extends  RecyclerView.ViewHolder{
        public  ImageView foto;
        public TextView votos;
        public MiMascotaHolderView(View itemView) {
            super(itemView);
            foto=(ImageView)itemView.findViewById(R.id.fotoMiMascota);
            votos=(TextView)itemView.findViewById(R.id.puntosFoto);
        }
    }
}
