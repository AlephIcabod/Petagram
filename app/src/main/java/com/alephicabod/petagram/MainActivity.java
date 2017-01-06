package com.alephicabod.petagram;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.alephicabod.petagram.adapters.MascotaAdapter;
import com.alephicabod.petagram.adapters.PageAdapter;
import com.alephicabod.petagram.fragments.ListaMascotasFragment;
import com.alephicabod.petagram.fragments.MiMascotaFragment;
import com.alephicabod.petagram.pojo.Mascota;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar appbar=(Toolbar)findViewById(R.id.appBarDetalle);
        setSupportActionBar(appbar);

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);
        setUpPageView();


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
                startActivity(i);
                break;
            case R.id.mAbout:
                i=new Intent(MainActivity.this,AcercaDeActivity.class);
                startActivity(i);
                break;
            case R.id.topFavoritos:
                i=new Intent(MainActivity.this,Favoritas.class);
                ArrayList<Mascota> mascotas= ListaMascotasFragment.getMascotas();
                Collections.sort(mascotas);
                String [] claves=getResources().getStringArray(R.array.claves);
                for(int j=0;j<5;j++){
                    String [] x={mascotas.get(j).getNombre(),mascotas.get(j).getVotos()+"",mascotas.get(j).getPicture()+""};
                    i.putExtra(claves[j],x);
                }
                startActivity(i);
                break;
        }
        return true;
    }
}

