package mx.com.tormex.petagram.petagramcoursera;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Sistemas on 27/07/2017.
 */

public class NotificacionIDTokenService extends FirebaseInstanceIdService {

    public static final String TAG = "FIREBASE TOKEN";
    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        enviarTokenRegistro(FirebaseInstanceId.getInstance().getToken());
    }

    private void enviarTokenRegistro(String token){
        Log.d(TAG, token);
    }
}
