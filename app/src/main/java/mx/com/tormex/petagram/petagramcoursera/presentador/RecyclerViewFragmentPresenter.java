package mx.com.tormex.petagram.petagramcoursera.presentador;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;

import mx.com.tormex.petagram.petagramcoursera.db.ConstructorMascotas;
import mx.com.tormex.petagram.petagramcoursera.fragment.IRecyclerViewFragmentView;
import mx.com.tormex.petagram.petagramcoursera.pojo.Mascota;
import mx.com.tormex.petagram.petagramcoursera.restApi.EndpointsApi;
import mx.com.tormex.petagram.petagramcoursera.restApi.adapter.RestApiAdapter;
import mx.com.tormex.petagram.petagramcoursera.restApi.model.MascotaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sistemas on 08/07/2017.
 */

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragmentView iRecyclerViewFragmentView;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragmentView iRecyclerViewFragmentView, Context context){
        this.iRecyclerViewFragmentView = iRecyclerViewFragmentView;
        this.context = context;

        //obtenerMascotasBaseDatos();
        obtenerMediosRecientes();
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarMascotasRV();
    }

    @Override
    public void obtenerMediosRecientes() {
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);

        SharedPreferences miPreferenciaCompartida = context.getSharedPreferences("preferenciaCuenta", Context.MODE_PRIVATE);
        String idCuentaInstagram = miPreferenciaCompartida.getString("id", "");
        String idCunetaUsuarioAmigo = miPreferenciaCompartida.getString("usuarioid", "");

        Call<MascotaResponse> mascotaResponseCall;
        if (idCuentaInstagram == "")
            mascotaResponseCall = endpointsApi.getRecentMedia();
        else
            mascotaResponseCall = endpointsApi.getRecentMediaById(idCunetaUsuarioAmigo);

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {
            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                mostrarMascotasRV();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(context, "Falló la conexión", Toast.LENGTH_SHORT).show();
                Log.e("Fallo la conexión", t.toString());
            }
        });
    }

    @Override
    public void mostrarMascotasRV() {
        iRecyclerViewFragmentView.inicializarAdaptadorRV(iRecyclerViewFragmentView.crearAdaptador(mascotas));
        iRecyclerViewFragmentView.generarLinearLayoutVertical();
        iRecyclerViewFragmentView.generarGridLayout();
    }
}
