package com.company.notificationproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button showSimpleNotification,showExpandableNotification,showPictureNotification
            ,showActionNotification,showNotificationGroup;
    String CHANNEL_ID="1";
    String GROUP_KEY_WORK_EMAIL="notifactiongroup";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showSimpleNotification=findViewById(R.id.show_simple_notification);
        showExpandableNotification=findViewById(R.id.show_expandable_notification);
        showPictureNotification=findViewById(R.id.show_picture_notification);
        showActionNotification=findViewById(R.id.show_action_notification);
        showNotificationGroup=findViewById(R.id.show_notification_group);


        showSimpleNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                   String name = "channel";

                   String description ="describe your channel";

                   int importance = NotificationManager.IMPORTANCE_HIGH;

                   NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
                   channel.setDescription(description);

                   NotificationManager notificationManager = getSystemService(NotificationManager.class);

                   notificationManager.createNotificationChannel(channel);

               }

                NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID);

                builder.setSmallIcon(R.drawable.flag)
                       .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.egypt))
                       .setContentTitle("Title")
                       .setContentText("This is my first notification");

                NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0,builder.build());

            }
        });


        showExpandableNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder=
                        new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                builder.setSmallIcon(R.drawable.flag)
                        .setContentTitle("Title 1")
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.egypt))
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("blablablablablablablablablablablabla" +
                                "blablablablablablablablablablablablablablablablablablablablablablablabla" +
                                "blablablablablablablablablablablablablablablablablablablablablablablabla"));

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,builder.build());
            }
        });

        showPictureNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder=
                        new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                builder.setSmallIcon(R.drawable.flag)
                        .setContentTitle("Title 1")
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.egypt))
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(BitmapFactory.decodeResource(getResources(),R.drawable.egypt)).bigLargeIcon(null));

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0,builder.build());
            }
        });

        showActionNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NotificationCompat.Builder builder=new NotificationCompat.Builder(MainActivity.this,CHANNEL_ID);

                builder.setSmallIcon(R.drawable.flag)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(),R.drawable.egypt))
                        .setContentTitle("Title")
                        .setContentText("This is my first notification");

                Intent intent=new Intent(MainActivity.this,SecondActivity.class);

                PendingIntent pendingIntent=PendingIntent.getActivity(MainActivity.this,0,intent,0);

                builder.setContentIntent(pendingIntent);


                NotificationManager manager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                manager.notify(0,builder.build());

            }
        });

        showNotificationGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder notification1 =
                        new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                notification1.setSmallIcon(R.drawable.flag)
                        .setContentTitle("Title 1")
                        .setContentText("You will not believe...")
                        .setGroup(GROUP_KEY_WORK_EMAIL);

                NotificationCompat.Builder notification2 =
                        new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                notification2.setSmallIcon(R.drawable.egypt)
                        .setContentTitle("Title 2")
                        .setContentText("Please join us to celebrate the...") 
                        .setGroup(GROUP_KEY_WORK_EMAIL);

                NotificationCompat.Builder summaryNotification =
                        new NotificationCompat.Builder(MainActivity.this, CHANNEL_ID);
                summaryNotification.setContentTitle("Summary")
                        .setContentText("Two new messages")
                        .setSmallIcon(R.drawable.ic_launcher_foreground)
                        .setStyle(new NotificationCompat.InboxStyle()
                                .addLine("Alex Faarborg  Check this out")
                                .addLine("Jeff Chang Launch Party")
                                .setBigContentTitle("2 new messages")
                                .setSummaryText("janedoe@example.com"))
                        .setGroup(GROUP_KEY_WORK_EMAIL)
                        .setGroupSummary(true);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, notification1.build());
                notificationManager.notify(1, notification2.build());
                notificationManager.notify(2, summaryNotification.build());
            }
        });

    }
}