package com.example.pc.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.backendless.messaging.PublishOptions;
import com.backendless.push.BackendlessPushService;

/**
 * Created by PC on 8/10/2018.
 */

public class PushService extends BackendlessPushService
{

    Intent intent;


    @Override
    public boolean onMessage(Context context, Intent intent)
    {

        String tickerText = intent.getStringExtra(PublishOptions.ANDROID_TICKER_TEXT_TAG);
        String contentTitle = intent.getStringExtra(PublishOptions.ANDROID_CONTENT_TITLE_TAG);
        String contentText = intent.getStringExtra(PublishOptions.ANDROID_CONTENT_TEXT_TAG);

        String messageText = intent.getStringExtra("message");

        String [] tokenizer = messageText.split("#");

        String message = tokenizer[0];

        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(context);
        nBuilder.setSmallIcon(GetNotification(nBuilder))
                .setTicker(tickerText)
                .setContentText(contentText)
                .setContentTitle(contentTitle)
                .setAutoCancel(true);

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        inboxStyle.setBigContentTitle(contentTitle);

        if(tokenizer.length > 1)
        {
            for(int i = 1; 1 < tokenizer.length;i++)
            {
                inboxStyle.addLine(tokenizer[i]);
            }
        }

        nBuilder.setStyle(inboxStyle);
        Intent resultIntent = new Intent(context,Roles.class);
        resultIntent.putExtra("title",contentTitle);

        TaskStackBuilder builder = TaskStackBuilder.create(context);

        builder.addNextIntent(resultIntent);

        PendingIntent pendingIntent = builder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        nBuilder.setContentIntent(pendingIntent);

         nBuilder.setFullScreenIntent(pendingIntent,false);

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
        notificationManager.notify(1,nBuilder.build());

        return false;
    }

    private int GetNotification(NotificationCompat.Builder notificationBuilder)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            notificationBuilder.setColor(Color.BLUE);
            return R.mipmap.taxi_driver;
        }

        else
        {
            return R.mipmap.taxi_driver;
        }



    }


}
