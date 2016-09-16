package brockbadgers.alarming;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Peter on 9/16/2016.
 */
public class AlarmReciever extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Log.w("AlarmReciever","Successfully in the AlarmReciever");

        //create an intent to the ringtone service
        Intent service_intent = new Intent(context, RingtoneService.class);
        service_intent.putExtra("start", intent.getIntExtra("start", 1));

        //start the service
        context.startService(service_intent);
    }
}
