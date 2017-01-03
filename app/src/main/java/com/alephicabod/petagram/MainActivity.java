package com.alephicabod.petagram;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Mascota> mascotas;
    private RecyclerView listaMascotas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar appbar=(Toolbar)findViewById(R.id.appBar);
        setSupportActionBar(appbar);
        cargarDatos();
        listaMascotas=(RecyclerView)findViewById(R.id.listaMascotas);
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
        listaMascotas.setAdapter(new MascotaAdapter(mascotas,this));
        ImageView favoritos=(ImageView)findViewById(R.id.favoritos);
        favoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity.this,Favoritas.class);
                obtenerTop();
                String [] claves=getResources().getStringArray(R.array.claves);
                for(int j=0;j<5;j++){
                    String [] x={mascotas.get(j).getNombre(),mascotas.get(j).getVotos()+"",mascotas.get(j).getPicture()+""};
                    i.putExtra(claves[j],x);
                }
                startActivity(i);
            }
        });
    }


    private void obtenerTop(){
        Collections.sort(mascotas);
    }
    private void cargarDatos(){
        mascotas=new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.gato1,5,"Gatito"));
        mascotas.add(new Mascota(R.drawable.pajaro1,6,"Periquillo"));
        mascotas.add(new Mascota(R.drawable.pajaro2,8,"Pajarito"));
        mascotas.add(new Mascota(R.drawable.perro1,3,"Doggy"));
        mascotas.add(new Mascota(R.drawable.perro2,2,"Dobby"));
        mascotas.add(new Mascota(R.drawable.tortuga1,4,"Torty"));
    }
}
