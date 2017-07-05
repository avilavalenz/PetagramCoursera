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

import mx.com.tormex.petagram.petagramcoursera.R;
import mx.com.tormex.petagram.petagramcoursera.pojo.Mascota;

/**
 * Created by Sistemas on 04/07/2017.
 */

public class GaleriaAdaptador extends RecyclerView.Adapter<GaleriaAdaptador.GaleriaViewHolder> {

    ArrayList<Mascota> galeria;
    Activity activity;

    public GaleriaAdaptador(ArrayList<Mascota> galeria, Activity activity) {
        this.galeria = galeria;
        this.activity = activity;
    }

    @Override
    public GaleriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_galeria, parent, false);
        return new GaleriaAdaptador.GaleriaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(GaleriaViewHolder galeriaViewHolder, int position) {
        final Mascota fGaleria = galeria.get(position);
        galeriaViewHolder.imgImagenGaleria.setImageResource(fGaleria.getFotografia());
        galeriaViewHolder.tvRatingGaleria.setText(String.valueOf(fGaleria.getRating()));
        galeriaViewHolder.galeriaFondo.setBackgroundResource(R.color.colorFondoAzul);

        galeriaViewHolder.btnHuesoLikeGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Like", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return galeria.size();
    }

    public static class GaleriaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgImagenGaleria;
        private TextView tvRatingGaleria;
        private ImageButton btnHuesoLikeGaleria;
        private LinearLayout galeriaFondo;

        public GaleriaViewHolder(View itemView) {
            super(itemView);
            imgImagenGaleria = (ImageView)itemView.findViewById(R.id.imgImagenGaleria);
            tvRatingGaleria = (TextView)itemView.findViewById(R.id.tvRatingGaleria);
            btnHuesoLikeGaleria = (ImageButton) itemView.findViewById(R.id.btnHuesoLikeGaleria);
            galeriaFondo = (LinearLayout) itemView.findViewById(R.id.galeriaFondo);
        }
    }
}