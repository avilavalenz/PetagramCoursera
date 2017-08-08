package mx.com.tormex.petagram.petagramcoursera.restApi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Sistemas on 08/08/2017.
 */

public class SeguirUsuario extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String ACCION_KEY = "SEGUIR_USUARIO";
        String ACCION_KEY_VER_PERFIL = "VER_PERFIL";
        String ACCION_KEY_VER_USUARIO = "VER_USUARIO";

        String accion = intent.getAction();

        if (ACCION_KEY.equals(accion)) {
            Toast.makeText(context, "Siguiendo", Toast.LENGTH_SHORT).show();
        }

        if (ACCION_KEY_VER_PERFIL.equals(accion)) {
            Toast.makeText(context, "Ver perfil", Toast.LENGTH_SHORT).show();
        }

        if (ACCION_KEY_VER_USUARIO.equals(accion)) {
            Toast.makeText(context, "Ver usuario", Toast.LENGTH_SHORT).show();
        }
    }
}
