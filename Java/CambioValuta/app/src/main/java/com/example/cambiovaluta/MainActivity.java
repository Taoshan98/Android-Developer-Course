package com.example.cambiovaluta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    private EditText insertedValue;

    private TextView viewFirstConvertedValues;
    private TextView viewSecondConvertedValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        insertedValue = findViewById(R.id.insertValue);

        viewFirstConvertedValues = findViewById(R.id.textViewFirstValue);
        viewSecondConvertedValues = findViewById(R.id.textViewSecondValue);

        insertedValue.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start,int before, int count) {
                populateCurrency();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                populateCurrency();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
    }

    private void populateCurrency() {
        String selectedCurrency = String.valueOf(spinner.getSelectedItem());

        String insertedValueString = insertedValue.getText().toString();
        insertedValueString = insertedValueString.replaceAll(",",".");

        boolean isNumeric = isValidDouble(insertedValueString);

        if (!insertedValueString.equals("") && isNumeric) {

            double insertedValueVal = Double.parseDouble(insertedValueString);

            List<String> finalValues = convertCurrency(selectedCurrency, insertedValueVal);
            viewFirstConvertedValues.setText(finalValues.get(0));
            viewSecondConvertedValues.setText(finalValues.get(1));
        } else if (!isNumeric){
            Toast.makeText(getApplicationContext(), "Insert just numbers!", Toast.LENGTH_SHORT).show();
            insertedValue.getText().clear();
        }else{
            Toast.makeText(getApplicationContext(), "Insert Value!", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isValidDouble(String s) {
        try {
            Double.valueOf(s);
            return true;
        } catch (NumberFormatException numberFormatException) {
            return false;
        }
    }

    private List<String> convertCurrency(String selectedCurrency, Double insertedValueVal) {
        String currencyFirst;
        String currencySecond;

        switch (selectedCurrency) {
            case "USD":
                currencyFirst = "EUR: " + insertedValueVal * 1.08;
                currencySecond = "GBP: " + insertedValueVal * 0.93;
                break;
            case "EUR":
                currencyFirst = "USD: " + insertedValueVal * 0.93;
                currencySecond = "GBP: " + insertedValueVal * 0.86;
                break;
            case "GBP":
                currencyFirst = "USD: " + insertedValueVal * 1.16;
                currencySecond = "EUR: " + insertedValueVal * 1.08;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + selectedCurrency);
        }
        return Arrays.asList(currencyFirst, currencySecond);
    }
}
