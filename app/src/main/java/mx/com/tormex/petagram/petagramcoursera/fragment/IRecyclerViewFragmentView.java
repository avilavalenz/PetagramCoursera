package mx.com.tormex.petagram.petagramcoursera.fragment;

import java.util.ArrayList;

import mx.com.tormex.petagram.petagramcoursera.adapter.MascotaAdaptador;
import mx.com.tormex.petagram.petagramcoursera.pojo.Mascota;

/**
 * Created by Sistemas on 08/07/2017.
 */

public interface IRecyclerViewFragmentView {
    public void generarLinearLayoutVertical();
    public MascotaAdaptador crearAdaptador(ArrayList<Mascota> mascotas);
    public void inicializarAdaptadorRV(MascotaAdaptador adaptador);
}
