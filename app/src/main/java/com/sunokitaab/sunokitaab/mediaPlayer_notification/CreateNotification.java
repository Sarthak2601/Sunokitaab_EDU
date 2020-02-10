package com.sunokitaab.sunokitaab.mediaPlayer_notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.sunokitaab.sunokitaab.R;
import com.sunokitaab.sunokitaab.Services.NotificationActionService;

public class CreateNotification {

    public static final String CHANNEL_ID = "channel1";
    public static final String ACTION_PREVIOUS = "actionprevious";
    public static final String ACTION_PLAY = "actionplay";
    public static final String ACTION_NEXT = "actionnext";

    public static Notification notification;


    public static void createNotification(Context context, Track track, int playButton, int pos, int size) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);
            MediaSessionCompat mediaSessionCompat = new MediaSessionCompat(context, "tag");

            Bitmap icon = BitmapFactory.decodeResource(context.getResources(),R.drawable.logo);

            PendingIntent pendingIntentPrevious ;
            int drw_previous;
            if(pos == 0){
                pendingIntentPrevious = null;
                drw_previous = 0;

            }else {
                Intent intentprevious = new Intent(context, NotificationActionService.class)
                        .setAction(ACTION_PREVIOUS);
                pendingIntentPrevious = PendingIntent.getBroadcast(context,0, intentprevious, PendingIntent.FLAG_UPDATE_CURRENT);
                drw_previous= R.drawable.ic_skip_previous_black_24dp;
            }

          //  Intent intent = new Intent(context, MainUi.class);
          //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK| Intent.FLAG_ACTIVITY_SINGLE_TOP);
          //  intent.putExtra("data","notification");
            //PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);

            Intent intentplay = new Intent(context, NotificationActionService.class)
                    .setAction(ACTION_PLAY)
                   // .setFlags(FLA)
                    ;
            PendingIntent pendingIntentPlay = PendingIntent.getBroadcast(context,0, intentplay, PendingIntent.FLAG_UPDATE_CURRENT);

            PendingIntent pendingIntentNext ;
            int drw_next;
            if(pos == size){
                pendingIntentNext = null;
                drw_next = 0;

            }else {
                Intent intentnext = new Intent(context, NotificationActionService.class)
                        .setAction(ACTION_NEXT);
                pendingIntentNext = PendingIntent.getBroadcast(context,0, intentnext, PendingIntent.FLAG_UPDATE_CURRENT);
                drw_next= R.drawable.ic_skip_next_black_24dp;
            }

            //create Notification

            notification = new NotificationCompat
                    .Builder(context, CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_audiotrack)
                    .setLargeIcon(icon)
                    .setContentTitle(track.getTitle())
                    .setPriority(NotificationCompat.PRIORITY_LOW)
                   // .addAction(drw_previous, "Previous",pendingIntentPrevious)
                    .addAction(playButton, "Play",pendingIntentPlay)
                    //.setContentIntent(pendingIntent)
                    .setStyle(new androidx.media.app.NotificationCompat.MediaStyle()
                    .setShowActionsInCompactView(0)
                    .setMediaSession(mediaSessionCompat.getSessionToken()))
                 //   .addAction(drw_next, "Next",pendingIntentNext)
                    .build();

            notificationManagerCompat.notify(1, notification);
        }
    }
}
