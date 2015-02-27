package com.wpappdeveloper.scheduler;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;

import java.sql.Time;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class CreateProfileActivity extends ActionBarActivity {

    Profile profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        profile = new Profile();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_profile, menu);
        return true;
    }

    public void showTimePicker(View v) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(CreateProfileActivity.this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                Time tme = new Time(selectedHour, selectedMinute,0);//seconds by default set to zero
                SimpleDateFormat formatter = new SimpleDateFormat("h:mm a");
                try {
                    profile.setTime(formatter.parse(formatter.format(tme)));
                    System.out.println(formatter.format(profile.getTime()));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, hour, minute, false);
        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public void showRingerTypeOptions(View v) {
        final CharSequence[] items = {" Ring "," Vibrate "," Silent "};

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Notification Type");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                switch(item)
                {
                    case 0:
                        // Your code when first option seletced
                        profile.setNotificationType(Profile.NotificationTypes.RINGER.getCode());
                        break;
                    case 1:
                        profile.setNotificationType(Profile.NotificationTypes.VIBRATE.getCode());

                        break;
                    case 2:
                        profile.setNotificationType(Profile.NotificationTypes.SILENT.getCode());
                        break;
                }
                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void showDaysPicker(View v) {
        final CharSequence[] items={"Sunday","Monday","Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        final boolean[] itemsChecked = new boolean[items.length];

        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Pick a day");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                List<Integer> selectedDays = new ArrayList<Integer>();
                for(int i=0; i<items.length; i++) {
                    if(itemsChecked[i]) {
                        selectedDays.add(1+i);
                        itemsChecked[i] = false;
                    }
                }
                profile.setDays(selectedDays);
            }
        });
        builder.setMultiChoiceItems(items, new boolean[]{false, false, false, false, false, false, false}, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                itemsChecked[which] = isChecked;
            }
        });
        builder.show();
    }

    public void bluetoothToggleClicked(View v) {
        boolean checked = ((Switch) v).isChecked();
        profile.setBluetooth(checked);
    }

    public void wifiToggleClicked(View v) {
        boolean checked = ((Switch) v).isChecked();
        profile.setWifi(checked);
    }

    public void dataToggleClicked(View v) {
        boolean checked = ((Switch) v).isChecked();
        profile.setData(checked);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
