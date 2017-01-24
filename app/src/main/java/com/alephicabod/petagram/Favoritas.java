package com.alephicabod.petagram;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Slide;
import android.transition.Visibility;
import android.view.Gravity;

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

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Slide fade=new Slide(Gravity.RIGHT);
            fade.setDuration(1500);
            Explode explode=new Explode();
            explode.setMode(Visibility.MODE_IN);
            explode.setDuration(1500);
            getWindow().setEnterTransition(explode);
            getWindow().setReturnTransition(explode);
            getWindow().setExitTransition(fade);
        }
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
