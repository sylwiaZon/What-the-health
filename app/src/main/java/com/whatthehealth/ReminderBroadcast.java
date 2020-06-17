package com.whatthehealth;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationManager;

public class ReminderBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        addNotification(context);
    }
    private void addNotification(Context context) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, "wthChannel")
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("What The Health!")
                        .setContentText("Let's find together some new recipes!");


        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(200,builder.build());
    }
}
