package com.example.pravar.sidepanel;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.NotificationCompat;

public class notify extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService
                (context.NOTIFICATION_SERVICE);
        Intent repeatintent = new Intent(context,MainActivity.class);
        repeatintent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,repeatintent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(context);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.drawable.logo);
        builder.setContentTitle("hello everyone");
        builder.setContentText("ho gya re ho gya");
        builder.setDefaults(Notification.DEFAULT_SOUND);
        builder.setVibrate(new long[]{100,2000,500,2000,500,2000,1000,1000,1000,1000});
        builder.setLights(Color.WHITE, 400, 200);
        builder.setAutoCancel(true);

        notificationManager.notify(100,builder.build());

    }
}
