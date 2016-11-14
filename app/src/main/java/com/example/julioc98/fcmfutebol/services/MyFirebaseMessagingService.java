package com.example.julioc98.fcmfutebol.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.example.julioc98.fcmfutebol.MainActivity;
import com.example.julioc98.fcmfutebol.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by julioc98 on 11/11/16.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    public static  final  int ID_NOTIFICACAO = 1000;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Intent i = new Intent(MyFirebaseMessagingService.this, MainActivity.class);

        TaskStackBuilder stackBuilder =
                TaskStackBuilder.create(MyFirebaseMessagingService.this);

        stackBuilder.addParentStack(MyFirebaseMessagingService.class);
        stackBuilder.addNextIntent(i);

        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
                PendingIntent.FLAG_CANCEL_CURRENT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getApplicationContext())
                .setContentTitle(remoteMessage.getNotification().getTitle())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentText(remoteMessage.getNotification().getBody()
                ).setContentIntent(pendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) getApplicationContext()
                .getSystemService(Context.NOTIFICATION_SERVICE);

        mNotificationManager.notify(ID_NOTIFICACAO, mBuilder.build());

    }
}
