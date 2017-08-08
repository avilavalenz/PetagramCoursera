package mx.com.tormex.petagram.petagramcoursera;


import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import mx.com.tormex.petagram.petagramcoursera.fragment.GaleriaFragment;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.app.NotificationCompat.WearableExtender;
import android.view.Gravity;

/**
 * Created by Sistemas on 27/07/2017.
 */

public class NotificationService extends FirebaseMessagingService {
    public static final String TAG = "FIREBASE";
    public static final int NOTIFICATION_ID = 001;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        Intent i = new Intent();
        i.setAction("SEGUIR_USUARIO");
//        i.setAction("VER_PERFIL");
//        i.setAction("VER_USUARIO");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        //Notificacion para el smartwatch
        NotificationCompat.Action actionSeguir =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_seguir, getString(R.string.texto_accion_seguir), pendingIntent).build();

        NotificationCompat.Action actionPerfil =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_profile, getString(R.string.texto_accion_perfil), pendingIntent).build();

        NotificationCompat.Action actionVerContacto =
                new NotificationCompat.Action.Builder(R.drawable.ic_full_contact, getString(R.string.texto_accion_contacto), pendingIntent).build();



        NotificationCompat.WearableExtender wearableExtender =
                new NotificationCompat.WearableExtender()
                .setHintHideIcon(true)
                .setBackground(BitmapFactory.decodeResource(getResources(), R.drawable.instagramwall))
                .setGravity(Gravity.CENTER_VERTICAL);

        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.zebra)
                .setContentTitle("Notificación")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .extend(wearableExtender.addAction(actionSeguir))
                .extend(wearableExtender.addAction(actionPerfil))
                .extend(wearableExtender.addAction(actionVerContacto))
                //.addAction(R.drawable.ic_full_seguir, getString(R.string.texto_accion_seguir), pendingIntent)
                ;

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);
        notificationManager.notify(NOTIFICATION_ID, notificacion.build());

    }
}
