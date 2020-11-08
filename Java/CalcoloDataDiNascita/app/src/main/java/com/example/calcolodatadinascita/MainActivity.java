package com.example.calcolodatadinascita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    private TextView viewAge;
    private EditText insertsData;
    private int year = Calendar.getInstance().get(Calendar.YEAR);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewAge = findViewById(R.id.textView);
        Button calcolaEtaButton = findViewById(R.id.button);
        insertsData = findViewById(R.id.editText);

        calcolaEtaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String inserisciTesto = insertsData.getText().toString();
                int dataDiNascita = Integer.parseInt(inserisciTesto);
                int eta = calcoloEta(dataDiNascita);
                viewAge.setText(String.valueOf(eta));
            }
        });
    }

    private int calcoloEta(int dataDiNascita){
        return (this.year - dataDiNascita);
    }

    public void calcolaEta(View view) {
    }
}
