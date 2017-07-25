package mx.com.tormex.petagram.petagramcoursera;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import mx.com.tormex.petagram.petagramcoursera.pojo.UsuarioInstagram;
import mx.com.tormex.petagram.petagramcoursera.restApi.EndpointsApi;
import mx.com.tormex.petagram.petagramcoursera.restApi.adapter.RestApiAdapter;
import mx.com.tormex.petagram.petagramcoursera.restApi.model.MascotaResponse;
import mx.com.tormex.petagram.petagramcoursera.restApi.model.UsuarioInstagramResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CuentaActivity extends AppCompatActivity {

    private ArrayList<UsuarioInstagram> usuarios;
    private EditText etCuentaInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuenta);

        SharedPreferences miPreferenciaCompartida = getSharedPreferences("preferenciaCuenta", Context.MODE_PRIVATE);
        String cuentaInstagram = miPreferenciaCompartida.getString("cuentaInstagram", "");
        etCuentaInstagram = (EditText) findViewById(R.id.etCuentaInstagram);
        etCuentaInstagram.setText(cuentaInstagram);
    }

    public void guardarPreferenciaCuenta(View v){
        /*-----------------------------------------------------------------*/
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonUserSearch = restApiAdapter.construyeGsonDeserializadorUserSearch();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonUserSearch);
        Call<UsuarioInstagramResponse> usuarioInstagramResponseCall = endpointsApi.getSearchUser(etCuentaInstagram.getText().toString());
        usuarioInstagramResponseCall.enqueue(new Callback<UsuarioInstagramResponse>() {
            @Override
            public void onResponse(Call<UsuarioInstagramResponse> call, Response<UsuarioInstagramResponse> response) {
                UsuarioInstagramResponse usuarioInstagramResponse = response.body();
                usuarios = usuarioInstagramResponse.getUsuariosInstagram();
                if (usuarios.size() == 0) {
                    Toast.makeText(CuentaActivity.this, "No se encontrarón usuarios con ese nombre.", Toast.LENGTH_SHORT).show();
                    return;
                }

                /*Fuera del metodo*/
                SharedPreferences miPreferenciaCompartida = getSharedPreferences("preferenciaCuenta", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = miPreferenciaCompartida.edit();
                etCuentaInstagram = (EditText) findViewById(R.id.etCuentaInstagram);
                String cuentaInstagram = etCuentaInstagram.getText().toString();
                editor.putString("cuentaInstagram", cuentaInstagram);
                editor.putString("id", usuarios.get(0).getId());
                editor.putString("url", usuarios.get(0).getProfile_picture());
                editor.putString("fullName", usuarios.get(0).getFull_name());

                editor.commit();
                Toast.makeText(CuentaActivity.this, "Datos guardados con éxito.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(CuentaActivity.this, ListaMascotasActivity.class);
                startActivity(intent);
                finish();
                /*Fuera del metodo*/
            }
            @Override
            public void onFailure(Call<UsuarioInstagramResponse> call, Throwable t) {
                Toast.makeText(CuentaActivity.this, "La conexión falló.", Toast.LENGTH_SHORT).show();
                Log.e("Fallo la conexión", t.toString());
            }
        });
        /*-----------------------------------------------------------------*/
    }

    public void mostrarPreferenciaCuenta(View v){
        SharedPreferences miPreferenciaCompartida = getSharedPreferences("preferenciaCuenta", Context.MODE_PRIVATE);
        String cuentaInstagram = miPreferenciaCompartida.getString("cuentaInstagram", "");
        etCuentaInstagram = (EditText) findViewById(R.id.etCuentaInstagram);
        etCuentaInstagram.setText(cuentaInstagram);
    }
}