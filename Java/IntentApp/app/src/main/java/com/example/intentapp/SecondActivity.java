package com.example.intentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = SecondActivity.class.getName();
    private static final String KEY_VALUE = TAG + ".extras.value";

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = findViewById(R.id.textView);

        String valueIntent = getIntent().getStringExtra(KEY_VALUE);

        textView.setText(valueIntent);

    }

    public static Intent getInstnceIntent(Context context, String value) {
        Intent intent = new Intent(context, SecondActivity.class);
        intent.putExtra(KEY_VALUE, value);
        return intent;
    }
}
