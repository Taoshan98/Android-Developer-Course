package com.example.downloadfromwebapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        String url = "http://massimocarli.eu/bus/bus_stop.json";

        new MyAsyncTask().execute(url);
    }

    @SuppressLint("StaticFieldLeak")
    private class MyAsyncTask extends AsyncTask<String, Void, String> {

        private String mFileContent;

        @Override
        protected String doInBackground(String... strings) {

            try {
                mFileContent = downloadJsonFile(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (mFileContent == null){
                Log.d("Download Err", "Error Downloading");
            }

            return mFileContent;
        }

        private String downloadJsonFile(String urlPath) throws IOException {

            StringBuilder stringBuilder = new StringBuilder();

            URL url = new URL(urlPath);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int resposnse = connection.getResponseCode();

            Log.d("HTTP Response", "Response " + resposnse);

            // Prendo il flusso di bit con InputStream e lo rendo comprensibile in UTF-8 con InputStreamReader
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);

            // Questo metodo ci permette di semplicaficare e velocizzare la conversione del flusso di bit
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line = bufferedReader.readLine();

            while (line != null){
                stringBuilder.append(line).append("\n");
                line = bufferedReader.readLine();
            }

            Log.d("Result", "Response " + stringBuilder.toString());
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            textView.setText(s);
        }
    }

}
