package mx.com.tormex.petagram.petagramcoursera.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mx.com.tormex.petagram.petagramcoursera.R;
import mx.com.tormex.petagram.petagramcoursera.adapter.GaleriaAdaptador;
import mx.com.tormex.petagram.petagramcoursera.adapter.MascotaAdaptador;
import mx.com.tormex.petagram.petagramcoursera.pojo.Mascota;

/**
 * A simple {@link Fragment} subclass.
 */
public class GaleriaFragment extends Fragment {

    ArrayList<Mascota> mascotas;
    RecyclerView rvGaleria;

    public GaleriaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil, container, false);

        rvGaleria = (RecyclerView) v.findViewById(R.id.rvGaleria);
        /*LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);*/

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);

        rvGaleria.setLayoutManager(manager);
        inicializarListaMascotas();
        inicializarAdaptador();

        return v;
    }

    public void inicializarListaMascotas(){
        mascotas = new ArrayList<>();
        mascotas.add(new Mascota(R.drawable.buho, "Buho", 6));
        mascotas.add(new Mascota(R.drawable.buho, "Buho", 5));
        mascotas.add(new Mascota(R.drawable.buho, "Buho", 4));
        mascotas.add(new Mascota(R.drawable.buho, "Buho", 3));
        mascotas.add(new Mascota(R.drawable.buho, "Buho", 2));
        mascotas.add(new Mascota(R.drawable.buho, "Buho", 4));
        mascotas.add(new Mascota(R.drawable.buho, "Buho", 3));
        mascotas.add(new Mascota(R.drawable.buho, "Buho", 2));
        mascotas.add(new Mascota(R.drawable.buho, "Buho", 2));
    }

    public void inicializarAdaptador(){
        GaleriaAdaptador adaptador = new GaleriaAdaptador(mascotas, getActivity());
        rvGaleria.setAdapter(adaptador);
    }

}
