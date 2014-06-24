package com.example.livecards;

import com.google.android.glass.timeline.LiveCard;
import com.google.android.glass.timeline.LiveCard.PublishMode;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class GlService extends Service
{
    private static final String LIVE_CARD_TAG = "opengl";

    private LiveCard mLiveCard;

    @Override
    public IBinder onBind(Intent intent) 
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        if (mLiveCard == null) 
        {
            mLiveCard = new LiveCard(this, LIVE_CARD_TAG);
            mLiveCard.setRenderer(new CubeRenderer());
            mLiveCard.setAction(
                    PendingIntent.getActivity(this, 0, new Intent(this, MainActivity.class), 0));
            mLiveCard.attach(this);
            mLiveCard.publish(PublishMode.REVEAL);
            
        } 
        else
        {
            mLiveCard.navigate();
        }

        return START_STICKY;
    }

    @Override
    public void onDestroy() 
    {
        if (mLiveCard != null && mLiveCard.isPublished())
        {
            mLiveCard.unpublish();
            mLiveCard = null;
        }
        super.onDestroy();
    }

}
