package com.alephicabod.petagram;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;

import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.Gravity;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by angel on 10/01/2017.
 */

public class NotificationService extends FirebaseMessagingService {
    private static final String TAG="NOTIFICACION_FIREBASE";
    private static final int REQUEST_CODE=0;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG,remoteMessage.getData().get("id_usuario_origen"));
        notificacion(remoteMessage.getNotification().getBody(),
                remoteMessage.getNotification().getTitle(),
                remoteMessage.getData().get("id_usuario_origen"));
    }

    private void notificacion(String mensaje,String titulo,String usuarioDiferente){
       //PendingIntent pendingIntent=PendingIntent.getActivity(this,REQUEST_CODE,i,PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSound= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        PendingIntent pendingIntent =   PendingIntent.getBroadcast(this,REQUEST_CODE,
                                        new Intent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setAction("VER_PERFIL"),
                                        PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action    =   new NotificationCompat.Action.Builder
                                                (R.drawable.perfil,"Ver mi perfil",pendingIntent).build();

        pendingIntent =   PendingIntent.getBroadcast(this,REQUEST_CODE,
                            new Intent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setAction("FOLLOW").putExtra("ID_USUARIO_INICIO",usuarioDiferente),
                            PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action1=new NotificationCompat.Action.Builder
                                            (R.drawable.follow_user,"Seguir",pendingIntent).build();


        pendingIntent =   PendingIntent.getBroadcast(this,REQUEST_CODE,
                new Intent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP).setAction("USUARIO").putExtra("ID_USUARIO_INICIO",usuarioDiferente),
                PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Action action2=new NotificationCompat.Action.Builder(R.drawable.follow,"Ver usuario",pendingIntent).build();

        NotificationCompat.WearableExtender wear=new NotificationCompat.WearableExtender().setHintHideIcon(true)
                                                    .setBackground(BitmapFactory.decodeResource(getResources(),R.drawable.gato1))
                                                    .setGravity(Gravity.CENTER_VERTICAL);

        NotificationCompat.Builder notBuilder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.cat_footprint_48)
                .setContentTitle(getResources().getString(R.string.app_name))
                .setContentText(mensaje)
                .setSound(defaultSound)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .extend(wear.addAction(action))
                .extend(wear.addAction(action1))
                .extend(wear.addAction(action2));

        //NotificationManager notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        NotificationManagerCompat notificationManagerCompat=NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(0,notBuilder.build());

    }
}
