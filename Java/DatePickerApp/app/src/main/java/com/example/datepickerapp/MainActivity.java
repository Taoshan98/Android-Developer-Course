package com.example.datepickerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton datePickerButton;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        datePickerButton = findViewById(R.id.datePickerButton);

        datePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();

                int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
                int currentMonth = calendar.get(Calendar.MONTH);
                int currentYear = calendar.get(Calendar.YEAR);

                new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month++;
                        String monthStr = (month < 10 ? "0" + month : String.valueOf(month));
                        String dayStr = (dayOfMonth < 10 ? "0" + dayOfMonth : String.valueOf(dayOfMonth));

                        Toast.makeText(MainActivity.this, "Selected Day: " + dayStr + "/" + monthStr + "/" + year, Toast.LENGTH_SHORT).show();

                    }
                },currentYear, currentMonth, currentDay).show();

            }
        });
    }
}
