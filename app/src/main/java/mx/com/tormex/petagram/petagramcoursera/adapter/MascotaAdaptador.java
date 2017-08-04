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
        //new UsuarioHerokuResponse (idFireBaseDueñoToken, tokenDueñoToken, idUsuarioInstagramDueñoToken, usernameDestinatario)
        //UsuarioHerokuResponse usuarioResponse = new UsuarioHerokuResponse("-Kqe7Tk1fqkbEcKXhTDq", "fdCzvxzrFqw:APA91bGsXX8KEQ-IRt51Diqg4p6ci-BiByoTKGORrKowbGQE97mb0i_QJZEI14lJVT0GRFltht6nvrmpYGuahGqb1WZrq5B1DuEWD4KeOfcERxYIDkdSANWpbRwIQnlj_W8DDnp3WvZH", "5810080353", "jorge.avilavalenz"); //mismascotas2017
        UsuarioHerokuResponse usuarioResponse = new UsuarioHerokuResponse("-Kqe71o40YgJvaasxows", "ecXiRe1kURE:APA91bHXMZEDYktBC4iDtzYJeDCL7ntoMlry94sF8rrRHXc6Fqt0z2nkD6iBk0kGvm92R73qX_gbWf-fNnwHg16mi9lQeYSFXAYardcByeQiAwAxZTtbqXJPG4AdxtU3Lcby8opwcqB1", "5753223183", "mismascotas2017"); //jorge.avilavalenz


        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endpointsApi = restApiAdapter.establecerConexionRestApiHeroku();
        //Enviar notificacion de like en instagram
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
