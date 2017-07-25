package mx.com.tormex.petagram.petagramcoursera.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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



        /*mascotaViewHolder.btnHuesoLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Like", Toast.LENGTH_SHORT).show();
                ConstructorMascotas constructorContactos = new ConstructorMascotas(activity);
                constructorContactos.darLikeMascota(mascota);
                mascotaViewHolder.tvRating.setText(String.valueOf(constructorContactos.obtenerLikesContacto(mascota) + " Likes"));
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFoto;
        //private TextView tvNombre;
        private TextView tvLikes;
        //private ImageButton btnHuesoLike;
        private LinearLayout fondo;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgFoto = (ImageView)itemView.findViewById(R.id.imgFoto);
            /*tvNombre = (TextView)itemView.findViewById(R.id.tvNombre);
            tvRating = (TextView)itemView.findViewById(R.id.tvRating);
            btnHuesoLike = (ImageButton) itemView.findViewById(R.id.btnHuesoLike);*/
            fondo = (LinearLayout) itemView.findViewById(R.id.fondo);
            tvLikes = (TextView) itemView.findViewById(R.id.tvLikes);

        }
    }
}
