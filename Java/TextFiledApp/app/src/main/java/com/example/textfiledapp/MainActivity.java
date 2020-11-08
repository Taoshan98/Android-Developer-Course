package com.example.textfiledapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextView usernameText;
    private TextInputLayout usernameTextInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameText = findViewById(R.id.usernameText);
        usernameTextInput = findViewById(R.id.usernameTextInput);

        usernameText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (usernameText.getText().toString().trim().isEmpty()){
                    usernameTextInput.setErrorEnabled(true);
                    usernameTextInput.setError("Insert Username");
                }else {
                    usernameTextInput.setErrorEnabled(false);
                }
            }
        });
    }
}
