package com.example.lucchetto;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();

    EditText insertedPin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insertedPin = findViewById(R.id.insertedPin);

        insertedPin.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start,int before, int count) {
                if (insertedPin.getText().toString().equals("1707")){
                    toViewElements();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public void enterPinClick(View view) {
        selectActivityAlert();
    }

    private void selectActivityAlert() {
        if (insertedPin.getText().toString().equals("1707")){
            toViewElements();
            insertedPin.getText().clear();
        }else{
            new AlertDialog.Builder(MainActivity.this).setTitle("Pin Errato!").setMessage("Inserisci il Pin corretto!").setNegativeButton(android.R.string.ok, null).setIcon(android.R.drawable.ic_dialog_alert).show();
        }
    }

    private void toViewElements(){
        Intent intent = new Intent(this, viewElements.class);
        startActivity(intent);
    }
}
