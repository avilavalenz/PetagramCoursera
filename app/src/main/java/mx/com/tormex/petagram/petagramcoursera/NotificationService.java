package mx.com.tormex.petagram.petagramcoursera;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by Sistemas on 27/07/2017.
 */

public class NotificationService extends FirebaseMessagingService {
    public static final String TAG = "FIREBASE";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        Intent i = new Intent(this, ListaMascotasActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder builder = (android.support.v7.app.NotificationCompat.Builder) new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.zebra)
                .setContentTitle("Notificación")
                .setContentText(remoteMessage.getNotification().getBody())
                .setAutoCancel(true)
                .setSound(sonido)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());

    }
}
