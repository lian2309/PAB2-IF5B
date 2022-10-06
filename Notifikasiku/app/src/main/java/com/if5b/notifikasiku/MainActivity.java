package com.if5b.notifikasiku;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;

import com.if5b.notifikasiku.databinding.ActivityMainBinding;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private int notifId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        binding.btnNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showNotification();
            }
        });
    }

    private void showNotification() {
        PendingIntent pendingIntent;
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        int pendingIntentFlags = PendingIntent.FLAG_UPDATE_CURRENT;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            pendingIntentFlags = PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_MUTABLE;
        }

        pendingIntent = PendingIntent.getActivity(this, 0, intent, pendingIntentFlags);


        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "notifikasiku_default")
                .setContentTitle("Reminder Tugas PAB II")
                .setContentText("Kumpul jam 23.59 malam ini!")
//                .setStyle(new NotificationCompat.BigTextStyle()
//                        .bigText("Kumpul jam 23.59 malam ini, semoga sehat selalu. Jangan lupa submit sesuai instruksi!")
//                        .setBigContentTitle("Panggilan Terakhir Reminder Tugas PAB II")
//                        .setSummaryText("Kumpul jam 23.59 malam ini!"))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentInfo("Notifikasiku")
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setColor(ContextCompat.getColor(this, android.R.color.transparent))
                .setLights(Color.RED, 1000, 300)
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setSmallIcon(R.drawable.ic_notifications_24);

        String picture = "https://avatars.githubusercontent.com/u/100788158?v=4";
        try {
            if (picture != null && !picture.equals("")) {
                URL url = new URL(picture);
                Bitmap bigPicture = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                notificationBuilder.setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(bigPicture)
                        .setBigContentTitle("Panggilan Terakhir Reminder Tugas PAB II")
                        .setSummaryText("Kumpul jam 23.59 malam ini!"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("notifikasiku_default", "Notifikasiku", NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Notifikasiku description");
            channel.setShowBadge(true);
            channel.canShowBadge();
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);
            channel.setVibrationPattern(new long[]{100, 200, 300, 400, 500});

            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        if (notificationManager != null) {
            notificationManager.notify(notifId++, notificationBuilder.build());
        }
    }
}