package mx.com.tormex.petagram.petagramcoursera;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import mx.com.tormex.petagram.petagramcoursera.adapter.PageAdapter;
import mx.com.tormex.petagram.petagramcoursera.fragment.GaleriaFragment;
import mx.com.tormex.petagram.petagramcoursera.fragment.RecyclerViewFragment;
import mx.com.tormex.petagram.petagramcoursera.restApi.EndpointsApi;
import mx.com.tormex.petagram.petagramcoursera.restApi.adapter.RestApiAdapter;
import mx.com.tormex.petagram.petagramcoursera.restApi.model.UsuarioHerokuResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListaMascotasActivity extends AppCompatActivity {

    private Toolbar toolBar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_mascotas);
//        Toolbar miActionBar = (Toolbar)findViewById(R.id.miActionBar);
//        setSupportActionBar(miActionBar);

        toolBar = (Toolbar) findViewById(R.id.toolbar);
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager();

        /*

        */

        if (toolBar != null) {
            setSupportActionBar(toolBar);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mAbout:
                startActivity(new Intent(this, AcercaDeActivity.class));
                finish();
                break;
            case R.id.mContact:
                startActivity(new Intent(this, ContactoActivity.class));
                finish();
                break;
            case R.id.mAccount:
                startActivity(new Intent(this, CuentaActivity.class));
                finish();
                break;
            case R.id.mNotificacion:
                enviarTokenRegistro(FirebaseInstanceId.getInstance().getToken());
                break;
            case R.id.mStar:
                Intent intent = new Intent(ListaMascotasActivity.this, MascotasFavoritasActivity.class);
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private ArrayList<Fragment> agregarFragment(){
        ArrayList<Fragment> fragments = new ArrayList<Fragment>();
        fragments.add(new RecyclerViewFragment());
        fragments.add(new GaleriaFragment());
        return fragments;
    }

    public void setupViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), agregarFragment()));
        viewPager.setCurrentItem(1);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_action_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.iconcatprofile);
    }

    public void enviarTokenRegistro(String token) {
        Log.d("Token", token);
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpoints = restApiAdapter.establecerConexionRestApiHeroku();
        SharedPreferences miPreferenciaCompartida = getSharedPreferences("preferenciaCuenta", Context.MODE_PRIVATE);
        Call<UsuarioHerokuResponse> usuarioResponseCall = endpoints.registrarTokenID(token, miPreferenciaCompartida.getString("id", ""), miPreferenciaCompartida.getString("cuentaInstagram", ""));

        usuarioResponseCall.enqueue(new Callback<UsuarioHerokuResponse>() {
            @Override
            public void onResponse(Call<UsuarioHerokuResponse> call, Response<UsuarioHerokuResponse> response) {
                UsuarioHerokuResponse usuarioHerokuResponse = response.body();
                Log.d("ID_FIREBASE", usuarioHerokuResponse.getId());
                Log.d("USUARIO_FIREBASE", usuarioHerokuResponse.getToken());
                Log.d("ID_USUARIO_INSTAGRAM", usuarioHerokuResponse.getId_usuario_instagram());
                Log.d("USUARIO_INSTAGRAM", usuarioHerokuResponse.getUsername());
            }

            @Override
            public void onFailure(Call<UsuarioHerokuResponse> call, Throwable t) {

            }
        });
    }
}