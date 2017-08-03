package mx.com.tormex.petagram.petagramcoursera.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import mx.com.tormex.petagram.petagramcoursera.DetalleMascota;
import mx.com.tormex.petagram.petagramcoursera.DetallleMascota;
import mx.com.tormex.petagram.petagramcoursera.pojo.Mascota;
import mx.com.tormex.petagram.petagramcoursera.R;
import mx.com.tormex.petagram.petagramcoursera.restApi.EndpointsApi;
import mx.com.tormex.petagram.petagramcoursera.restApi.adapter.RestApiAdapter;
import mx.com.tormex.petagram.petagramcoursera.restApi.model.UsuarioHerokuResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Sistemas on 27/06/2017.
 */

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {

    ArrayList<Mascota> mascotas;
    Activity activity;

    public MascotaAdaptador(ArrayList<Mascota> mascotas, Activity activity) {
        this.mascotas = mascotas;
        this.activity = activity;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_grid_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        //mascotaViewHolder.imgFoto.setImageResource(mascota.getUrlFoto());
        Picasso.with(activity)
                .load(mascota.getUrlFoto())
                .placeholder(R.drawable.buho)
                .into(mascotaViewHolder.imgFoto);
        //mascotaViewHolder.tvNombre.setText(mascota.getNombre());
        mascotaViewHolder.tvLikes.setText(String.valueOf(mascota.getLikes()));

        mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(activity, mascota.getNombre(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(activity, DetalleMascota.class);
                intent.putExtra("url", mascota.getUrlFoto());
                intent.putExtra("like", mascota.getLikes());
                activity.startActivity(intent);
            }
        });



        mascotaViewHolder.imgBone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Te gusta", Toast.LENGTH_SHORT).show();
                likeInstagram(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        //private TextView tvNombre;
        private TextView tvLikes;
        private ImageView imgBone;
        private LinearLayout fondo;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView)itemView.findViewById(R.id.imgFoto);
            /*tvNombre = (TextView)itemView.findViewById(R.id.tvNombre);
            tvRating = (TextView)itemView.findViewById(R.id.tvRating);
            btnHuesoLike = (ImageButton) itemView.findViewById(R.id.btnHuesoLike);*/
            imgBone = (ImageView) itemView.findViewById(R.id.imgBone);
            fondo = (LinearLayout) itemView.findViewById(R.id.fondo);
            tvLikes = (TextView) itemView.findViewById(R.id.tvLikes);

        }
    }

    public void likeInstagram(View v){
        UsuarioHerokuResponse usuarioResponse = new UsuarioHerokuResponse("-KqcnCSU8bZ1kZClf4iP", "chLwHWRKhIs:APA91bFPAFQIgCaqintGC-_j1pK4lcIZTlnUH7MPRxAQB1y2yDhsMRYNyvbz28vkjR__b_CJxMVtdznXFpVzhf4qbgEp_GJgJxfJ3-a1OzfeaduK832wZ3syyWyElCZD7yzuOLcNucmq", "5753223183"); //mismascotas2017
        //UsuarioHerokuResponse usuarioResponse = new UsuarioHerokuResponse("-KqcnCSU8bZ1kZClf4iP", "eVFjG0BKFYo:APA91bHK6Z2IVQ8R6rqkLdrK0k1DG6m2j-rOyh0kY1F9bH1jsuq3sz_CsF0I6-p1GiMzJFagzrDkzlrj6rhrv8bkhrujuUNoS7R-YkhBuyLV_JTxZcs-SWQ8bVsB5Gn1HU3M_CYO-WA-", "5810080353"); //mismascotas2017

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiHeroku();
        Call<UsuarioHerokuResponse> usuarioHerokuResponseCall = endpointsApi.like(usuarioResponse.getId(), usuarioResponse.getId_usuario_instagram());

        usuarioHerokuResponseCall.enqueue(new Callback<UsuarioHerokuResponse>() {
            @Override
            public void onResponse(Call<UsuarioHerokuResponse> call, Response<UsuarioHerokuResponse> response) {
                UsuarioHerokuResponse usuarioHerokuResponse1 = response.body();
                Log.d("ID_FIREBASE", usuarioHerokuResponse1.getId());
                Log.d("TOKEN_FIREBASE", usuarioHerokuResponse1.getToken());
                Log.d("USUARIO_INSTAGRAM", usuarioHerokuResponse1.getId_usuario_instagram());
            }

            @Override
            public void onFailure(Call<UsuarioHerokuResponse> call, Throwable t) {

            }
        });


    }
}
