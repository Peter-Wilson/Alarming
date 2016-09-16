package brockbadgers.alarming;

import android.app.AlarmManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class Clock extends AppCompatActivity {

    //make alarm manage
    AlarmManager alarmManager;
    TimePicker alarm_timepicker;
    TextView status_text;
    Button on;
    Button off;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //init variables
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarm_timepicker = (TimePicker) this.findViewById(R.id.timePickerAlarm);
        status_text = (TextView) this.findViewById(R.id.status);
        on = (Button) this.findViewById(R.id.btnSetAlarm);
        off = (Button) this.findViewById(R.id.btnStopAlarm);
        final Calendar calendar = Calendar.getInstance();

        //setOnClick for start button
        on.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set calendar with hour and minute
                calendar.set(Calendar.HOUR_OF_DAY, alarm_timepicker.getHour());
                calendar.set(Calendar.MINUTE, alarm_timepicker.getMinute());

                //set the status text
                set_status_text(1, calendar.getTime().toString());

            }
        });

        //setOnClick for off button
        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set status textbox
                set_status_text(0, null);

            }
        });


    }

    private void set_status_text(int status, String time) {
        switch(status)
        {
            case 2:
                status_text.setText(getString(R.string.status_alarm_going));
                break;
            case 1:
                status_text.setText(getString(R.string.status_alarm_set) + time);
                break;
            default:
                status_text.setText(getString(R.string.status_no_alarm_set));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clock, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
