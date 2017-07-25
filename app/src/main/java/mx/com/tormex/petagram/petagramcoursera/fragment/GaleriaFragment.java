package mx.com.tormex.petagram.petagramcoursera.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.com.tormex.petagram.petagramcoursera.R;
import mx.com.tormex.petagram.petagramcoursera.adapter.GaleriaAdaptador;
import mx.com.tormex.petagram.petagramcoursera.adapter.MascotaAdaptador;
import mx.com.tormex.petagram.petagramcoursera.pojo.Mascota;
import mx.com.tormex.petagram.petagramcoursera.restApi.EndpointsApi;
import mx.com.tormex.petagram.petagramcoursera.restApi.adapter.RestApiAdapter;
import mx.com.tormex.petagram.petagramcoursera.restApi.model.MascotaResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GaleriaFragment extends Fragment {

    ArrayList<Mascota> mascotas;
    RecyclerView rvGaleria;
    CircularImageView civImagenPerfil;
    TextView tvNombreMascotaPerfil;

    public GaleriaFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);
        civImagenPerfil = (CircularImageView) v.findViewById(R.id.civImagenPerfil);
        tvNombreMascotaPerfil = (TextView) v.findViewById(R.id.tvNombreMascotaPerfil);
        rvGaleria = (RecyclerView) v.findViewById(R.id.rvGaleria);
        /*LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);*/

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);

        rvGaleria.setLayoutManager(manager);
        inicializarListaMascotas();

        return v;
    }

    public void inicializarListaMascotas(){
        mascotas = new ArrayList<>();
        mascotas = new ArrayList<>();
        RestApiAdapter restApiAdapter = new RestApiAdapter();
        Gson gsonMediaRecent = restApiAdapter.construyeGsonDeserializadorMediaRecent();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiInstagram(gsonMediaRecent);
        //mascotas.add(new Mascota("", "Manuel Avila", 3));
        SharedPreferences miPreferenciaCompartida = this.getContext().getSharedPreferences("preferenciaCuenta", Context.MODE_PRIVATE);
        String idCuentaInstagram = miPreferenciaCompartida.getString("id", "");
        String urlCuentaInstagram = miPreferenciaCompartida.getString("url", "http://epaper2.mid-day.com/images/no_image_thumb.gif");
        String fullName = miPreferenciaCompartida.getString("fullName", " ");
        tvNombreMascotaPerfil.setText(fullName);
        Call<MascotaResponse> mascotaResponseCall;
        if (idCuentaInstagram == "")
            mascotaResponseCall = endpointsApi.getRecentMedia();
        else{
            mascotaResponseCall = endpointsApi.getRecentMediaById(idCuentaInstagram);
            Picasso.with(this.getActivity())
                    .load(urlCuentaInstagram)
                    .placeholder(R.drawable.buho)
                    .into(civImagenPerfil);

        }

        mascotaResponseCall.enqueue(new Callback<MascotaResponse>() {

            @Override
            public void onResponse(Call<MascotaResponse> call, Response<MascotaResponse> response) {
                MascotaResponse mascotaResponse = response.body();
                mascotas = mascotaResponse.getMascotas();
                inicializarAdaptador();
            }

            @Override
            public void onFailure(Call<MascotaResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Falló la conexión", Toast.LENGTH_SHORT).show();
                Log.e("Fallo la conexión", t.toString());
            }
        });
    }

    public void inicializarAdaptador(){
        GaleriaAdaptador adaptador = new GaleriaAdaptador(mascotas, getActivity());
        rvGaleria.setAdapter(adaptador);
    }

}