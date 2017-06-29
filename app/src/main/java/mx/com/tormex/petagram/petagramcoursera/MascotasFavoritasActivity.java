package mx.com.tormex.petagram.petagramcoursera;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class MascotasFavoritasActivity extends AppCompatActivity {

    ArrayList<Mascota> mascotas;
    RecyclerView listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas_favoritas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        listaMascotas = (RecyclerView) findViewById(R.id.rvMascotas);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(manager);
        inicializarListaMascotas();
        inicializarAdaptador();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //menu.add(Menu.NONE, 1, Menu.NONE, "Item name");
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        MenuItem itemStar = menu.findItem(R.id.mStar);
        itemStar.setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mAbout:
                Toast.makeText(this, "Creado por avilavalenz", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mSettings:
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.zebra, "Zebra", 6));
        mascotas.add(new Mascota(R.drawable.leopardo, "Leopardo", 5));
        mascotas.add(new Mascota(R.drawable.elefante, "Elefante", 4));
        mascotas.add(new Mascota(R.drawable.cerdo, "Cerdo", 3));
        mascotas.add(new Mascota(R.drawable.cabra, "Cabra", 2));
    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, this);
        listaMascotas.setAdapter(adaptador);
    }
}