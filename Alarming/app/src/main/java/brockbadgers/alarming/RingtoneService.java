package brockbadgers.alarming;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Peter on 9/16/2016.
 */
public class RingtoneService extends Service {

    boolean isRunning;
    MediaPlayer alarm_song;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startId = intent.getIntExtra("start", 1);
        Log.i("LocalService", "New start id " + startId + ": " + intent);

        //setup a notification when alarm goes off
        NotificationManager notify = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent intent_main_activity = new Intent(this.getApplicationContext(), Clock.class);
        PendingIntent pending_intent = PendingIntent.getActivity(this, 0, intent_main_activity, 0);

        Notification alarm_notification = new Notification.Builder(this)
                .setContentTitle("WAKE UP!!!")
                .setContentText("click here")
                .setSmallIcon(R.drawable.alarm_icon)
                .setContentIntent(pending_intent)
                .setAutoCancel(true)
                .build();


        //turn on or off alarm
        switch(startId) {
            case 1:
                //create song
                if(!isRunning) {
                    alarm_song = MediaPlayer.create(this, R.raw.clock);
                    alarm_song.start();
                    isRunning = true;
                    //notify
                    notify.notify(0, alarm_notification);
                    break;
                }
            case 0:
                //stop ringtone
                if(isRunning)
                {
                    alarm_song.stop();
                    alarm_song.reset();
                    isRunning = false;
                }
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        // Tell the user we stopped.
        Log.w("RingtoneService","OnDestroy called");
        super.onDestroy();
        this.isRunning = false;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
