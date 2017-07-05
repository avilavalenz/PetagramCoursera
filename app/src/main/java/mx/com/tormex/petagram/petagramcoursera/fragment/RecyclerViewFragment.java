package mx.com.tormex.petagram.petagramcoursera.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import mx.com.tormex.petagram.petagramcoursera.R;
import mx.com.tormex.petagram.petagramcoursera.adapter.MascotaAdaptador;
import mx.com.tormex.petagram.petagramcoursera.pojo.Mascota;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment {


    ArrayList<Mascota> mascotas;
    RecyclerView rvMascotas;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);

        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        rvMascotas.setLayoutManager(manager);
        inicializarListaMascotas();
        inicializarAdaptador();

        return v;
    }

    public void inicializarListaMascotas(){
        mascotas = new ArrayList<Mascota>();
        mascotas.add(new Mascota(R.drawable.buho, "Buho", 1));
        mascotas.add(new Mascota(R.drawable.cabra, "Cabra", 2));
        mascotas.add(new Mascota(R.drawable.cerdo, "Cerdo", 3));
        mascotas.add(new Mascota(R.drawable.elefante, "Elefante", 4));
        mascotas.add(new Mascota(R.drawable.leopardo, "Leopardo", 5));
        mascotas.add(new Mascota(R.drawable.zebra, "Zebra", 6));
    }

    public void inicializarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        rvMascotas.setAdapter(adaptador);
    }

}
