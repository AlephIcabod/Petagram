package com.alephicabod.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import java.util.ArrayList;

public class Favoritas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritas);
        Toolbar appbar=(Toolbar)findViewById(R.id.appBarDetalle);
        setSupportActionBar(appbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ArrayList<Mascota> favos=new ArrayList<Mascota>();
        String[] claves=getResources().getStringArray(R.array.claves);
        Bundle extras=getIntent().getExtras();
        for(int i=0;i<claves.length;i++){
            String [] aux=(String [])extras.get(claves[i]);
            favos.add(new Mascota(Integer.parseInt(aux[2]),Integer.parseInt(aux[1]),aux[0]));
        }
        RecyclerView listaFavoritos=(RecyclerView)findViewById(R.id.listaFavoritas);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaFavoritos.setLayoutManager(llm);
        listaFavoritos.setAdapter(new MascotaAdapter(favos,this));
    }
}
