package com.example.okhttpapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView textView;
    private OkHttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        httpClient = new OkHttpClient();

        String url = "http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topsongs/limit=10/xml";

        getWebReponse(url);
    }

    private void getWebReponse(String url) {

        final Request request = new Request.Builder().url(url).build();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                if (!response.isSuccessful()){
                    throw  new IOException("Somethingd Went Wrong!");
                }

                final String responseData = Objects.requireNonNull(response.body()).string();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(responseData);
                    }
                });
            }
        });

    }
}
