package com.example.coloredbuttonsconstraintapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button mClickedSquare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onItemClick(View view) {

        switch (view.getId()){
            case R.id.firstBox:
                view.setBackgroundColor(Color.DKGRAY);
                break;
            case R.id.secondBox:
                view.setBackgroundColor(Color.MAGENTA);
                break;
            case R.id.thirdBox:
                view.setBackgroundColor(Color.RED);
                break;
            case R.id.fourthBox:
                view.setBackgroundColor(Color.GREEN);
                break;
            case R.id.fifthBox:
                view.setBackgroundColor(Color.BLUE);
                break;
        }
    }
}
