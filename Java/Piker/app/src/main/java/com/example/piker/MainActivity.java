package com.example.piker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton pickerButton;
    private TextView textView;
    private Calendar calendar;
    private TimePickerDialog timePickerDialog;
    private Boolean time24Hours = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pickerButton = findViewById(R.id.pickerButton);
        textView = findViewById(R.id.textView);

        pickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                final int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
                int currentMinute = calendar.get(Calendar.MINUTE);

                new TimePickerDialog(MainActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        String minuteStr = minute < 10 ? "0" + minute : String.valueOf(minute);
                        String hourStr = hourOfDay < 10 ? "0" + hourOfDay : String.valueOf(hourOfDay);

                        textView.setText("Ore :" + hourStr + " minuti " + minuteStr);

                    }
                }, currentHour, currentMinute, time24Hours).show();
            }
        });
    }
}
