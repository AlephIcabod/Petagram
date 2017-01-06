package com.alephicabod.petagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.alephicabod.petagram.adapters.MascotaAdapter;
import com.alephicabod.petagram.fragments.IListaMascotasFragment;
import com.alephicabod.petagram.models.Foto;
import com.alephicabod.petagram.models.Mascota;
import com.alephicabod.petagram.presenters.IListaMascotasPresenter;
import com.alephicabod.petagram.presenters.ListaMascotasPresenter;

import java.util.ArrayList;

public class Favoritas extends AppCompatActivity implements IListaMascotasFragment{

     private RecyclerView listaFavoritos;
    private IListaMascotasPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritas);
        Toolbar appbar=(Toolbar)findViewById(R.id.appBarDetalle);
        setSupportActionBar(appbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listaFavoritos=(RecyclerView)findViewById(R.id.listaFavoritas);
        presenter =new ListaMascotasPresenter(this,this);
        presenter.getFavoritos();
    }

    @Override
    public void generateVerticalLayout() {
        LinearLayoutManager llm=new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaFavoritos.setLayoutManager(llm);
    }

    @Override
    public MascotaAdapter createAdapter(ArrayList<Mascota> mascotas) {
        MascotaAdapter adaptador=new MascotaAdapter(mascotas,this);
        return adaptador;
    }

    @Override
    public void initAdapter(MascotaAdapter adapter) {
            listaFavoritos.setAdapter(adapter);
    }
}
