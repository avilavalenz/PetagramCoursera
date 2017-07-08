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
import mx.com.tormex.petagram.petagramcoursera.presentador.IRecyclerViewFragmentPresenter;
import mx.com.tormex.petagram.petagramcoursera.presentador.RecyclerViewFragmentPresenter;


/**
 * A simple {@link Fragment} subclass.
 */
public class RecyclerViewFragment extends Fragment implements IRecyclerViewFragmentView {


    ArrayList<Mascota> mascotas;
    RecyclerView rvMascotas;
    private IRecyclerViewFragmentPresenter presenter;

    public RecyclerViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View v = inflater.inflate(R.layout.fragment_recycler_view, container, false);
        rvMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        presenter = new RecyclerViewFragmentPresenter(this, getContext());

//        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//
//        rvMascotas.setLayoutManager(manager);
//        inicializarAdaptador();

        return v;
    }

    @Override
    public void generarLinearLayoutVertical() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMascotas.setLayoutManager(manager);
    }

    @Override
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas) {
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, getActivity());
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador) {
        rvMascotas.setAdapter(adaptador);
    }
}
