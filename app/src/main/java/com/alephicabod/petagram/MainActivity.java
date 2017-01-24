package com.alephicabod.petagram;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Visibility;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.alephicabod.petagram.adapters.PageAdapter;
import com.alephicabod.petagram.fragments.ListaMascotasFragment;
import com.alephicabod.petagram.fragments.MiMascotaFragment;
import com.alephicabod.petagram.models.Mascota;
import com.alephicabod.petagram.rest.Endpoints;
import com.alephicabod.petagram.rest.adapter.RestAdapter;
import com.alephicabod.petagram.rest.model.FollowResponse;
import com.alephicabod.petagram.rest.model.UsuarioResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar appbar=(Toolbar)findViewById(R.id.appBarDetalle);
        setSupportActionBar(appbar);
        activity=this;
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        setUpPageView();
        Utilidades.establecerTransitions(this);


    }





    private ArrayList<Fragment> loadFragments(){
        ArrayList<Fragment> f=new ArrayList<Fragment>();
        f.add(new ListaMascotasFragment());
        f.add(new MiMascotaFragment());
        return f;
    }

    private void setUpPageView(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),loadFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_action_name);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        switch (item.getItemId()){
            case R.id.mContacto:
                i=new Intent(MainActivity.this,ContactoActivity.class);
                Utilidades.iniciarActividad(i,activity);
               break;
            case R.id.mAbout:
                i=new Intent(MainActivity.this,AcercaDeActivity.class);
                Utilidades.iniciarActividad(i,activity);
                break;
            case R.id.topFavoritos:
                i=new Intent(MainActivity.this,Favoritas.class);
                Utilidades.iniciarActividad(i,activity);
                break;
            case R.id.mregistrarUsuario:
                i=new Intent(MainActivity.this,ConfigurarCuentaActivity.class);
                Utilidades.iniciarActividad(i,activity);
                break;
            case R.id.mNotificaciones:
                SharedPreferences sp=getSharedPreferences("TOKEN_FIREBASE",MODE_PRIVATE);
                String token=sp.getString("TOKEN","no hay token");
                sp=getSharedPreferences("Cuenta",MODE_PRIVATE);
                String usuarioActual=sp.getString("Usuario","no hay usuario");
                if(token!="no hay token"&&usuarioActual!="no hay usuario"){
                    RestAdapter adapter=new RestAdapter();
                    Endpoints endpoints=adapter.establecerConexionFirebase();
                    Call<UsuarioResponse> respuesta=endpoints.registrarUsuario(token,usuarioActual);
                    respuesta.enqueue(new Callback<UsuarioResponse>() {
                        @Override
                        public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                         UsuarioResponse usuarioResponse=response.body();
                            Toast.makeText(MainActivity.this, "Se han activado las notificaciones para el dispositivo", Toast.LENGTH_LONG).show();
                            SharedPreferences sp=getSharedPreferences("CUENTA_ACTUAL", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor=sp.edit();
                            editor.putString("ID",usuarioResponse.getId());
                            editor.putString("ID_DISPOSITIVO",usuarioResponse.getId_dispositivo());
                            editor.putString("ID_USUARIO_INSTAGRAM",usuarioResponse.getId_usuario_instagram());
                            editor.commit();
                        }

                        @Override
                        public void onFailure(Call<UsuarioResponse> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Hubo un error, reintenta despues", Toast.LENGTH_LONG).show();
                        }
                    });
                }else{
                    Toast.makeText(this, "No se ha registrado un usuario", Toast.LENGTH_LONG).show();
                }
                break;
        }
        return true;
    }



}

