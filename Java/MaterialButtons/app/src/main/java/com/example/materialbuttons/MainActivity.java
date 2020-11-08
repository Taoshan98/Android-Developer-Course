package com.example.materialbuttons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private AppCompatButton flatButton, risedButton;
    private FloatingActionButton floatActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        flatButton = findViewById(R.id.flatButton);
        risedButton = findViewById(R.id.risedButton);
        floatActionButton = findViewById(R.id.floatButton);

        flatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Flat Button", Toast.LENGTH_SHORT).show();
            }
        });

        risedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Rised Button", Toast.LENGTH_SHORT).show();
            }
        });

        floatActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Float Button", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
