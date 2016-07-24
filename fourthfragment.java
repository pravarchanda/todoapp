package com.example.pravar.sidepanel;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class fourthfragment extends Fragment {

    public fourthfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view;
        final TimePicker[] timePicker = new TimePicker[1];
        view = inflater.inflate(R.layout.fragment_fourthfragment, container, false);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                timePicker[0] = (TimePicker) view.findViewById(R.id.timePicker);
                final int hour = timePicker[0].getCurrentHour();
                int minute = timePicker[0].getCurrentMinute();
                String hours = String.valueOf(hour);
                String minutes = String.valueOf(minute);
                if(minute<10)
                {
                    minutes = "0"+minutes;
                }
                Toast.makeText(getContext(),"Alarm set for "+hours+" : "+minutes,Toast.LENGTH_LONG).show();
                final int finalMinute = minute;
                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.HOUR_OF_DAY,hour);
                calendar.set(Calendar.MINUTE, finalMinute);
                calendar.set(Calendar.SECOND,0);
                Intent intent = new Intent(getContext(),notify.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(),
                        100,intent,PendingIntent.FLAG_UPDATE_CURRENT);

                AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),
                        AlarmManager.INTERVAL_DAY,pendingIntent);
            }
        });
        view.findViewById(R.id.cancelalarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),notify.class);
                PendingIntent.getBroadcast(getContext(),
                        100,intent,PendingIntent.FLAG_UPDATE_CURRENT).cancel();
                Toast.makeText(getContext(),"alarm cancelled",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
