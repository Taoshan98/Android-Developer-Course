package com.example.snackbarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    private Snackbar snackbar;
    private CoordinatorLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        floatingActionButton = findViewById(R.id.floatingActionButton);
        view = findViewById(R.id.view);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar = Snackbar.make(v, "Message Sent", Snackbar.LENGTH_INDEFINITE);
                snackbar.setDuration(1000);
                snackbar.setAction("DISMISS", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(MainActivity.this, "Dismiss", Toast.LENGTH_SHORT).show();
                        snackbar.dismiss();
                    }
                });
                snackbar.show();
            }
        });
    }
}
