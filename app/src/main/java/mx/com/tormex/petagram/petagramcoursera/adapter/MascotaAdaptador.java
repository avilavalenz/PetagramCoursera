package mx.com.tormex.petagram.petagramcoursera.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

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
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
        return new MascotaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MascotaViewHolder mascotaViewHolder, int position) {
        final Mascota mascota = mascotas.get(position);
        mascotaViewHolder.imgImagen.setImageResource(mascota.getFotografia());
        mascotaViewHolder.tvNombre.setText(mascota.getNombre());
        mascotaViewHolder.tvRating.setText(String.valueOf(mascota.getRating()));

        if (position % 2 == 0)
            mascotaViewHolder.fondo.setBackgroundResource(R.color.colorFondoRosa);
        else
            mascotaViewHolder.fondo.setBackgroundResource(R.color.colorFondoAzul);

        mascotaViewHolder.btnHuesoLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Like", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgImagen;
        private TextView tvNombre;
        private TextView tvRating;
        private ImageButton btnHuesoLike;
        private LinearLayout fondo;

        public MascotaViewHolder(View itemView) {
            super(itemView);
            imgImagen = (ImageView)itemView.findViewById(R.id.imgImagen);
            tvNombre = (TextView)itemView.findViewById(R.id.tvNombre);
            tvRating = (TextView)itemView.findViewById(R.id.tvRating);
            btnHuesoLike = (ImageButton) itemView.findViewById(R.id.btnHuesoLike);
            fondo = (LinearLayout) itemView.findViewById(R.id.fondo);
        }
    }
}
