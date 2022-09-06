package com.example.conversioncalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int positionFrom, positionTo;
    Spinner options, unitsFrom, unitsTo;
    TextView txtUnitFrom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }


        options = findViewById(R.id.spinnerOptions);
        unitsFrom = findViewById(R.id.spinnerUnitsFrom);
        unitsTo = findViewById(R.id.spinnerUnitsTo);

        String[] listOfOptions = {"Length", "Temperature"};
        String[] listUnitsTemperature = {"Celsius", "Fahrenheit", "Kelvin"};
        String[] listUnitsLength = {"Millimeter", "Centimeter", "Decimeter", "Meter", "Kilometer"};

        ArrayAdapter<String> adapterOptions = new ArrayAdapter<>(this, R.layout.my_selected_item, listOfOptions);
        adapterOptions.setDropDownViewResource(R.layout.my_dropdown_item);
        options.setAdapter(adapterOptions);

        //setting options for spinners: 'spinnerUnitsFrom' and 'spinnerUnitsTo' according to selected option in 'spinnerOptions'
        options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    ArrayAdapter<String> adapterLength = new ArrayAdapter<>(getApplicationContext(), R.layout.my_selected_item, listUnitsLength);
                    adapterLength.setDropDownViewResource(R.layout.my_dropdown_item);
                    unitsFrom.setAdapter(adapterLength);
                    unitsTo.setAdapter(adapterLength);
                    EditText input = findViewById(R.id.txtNumber);
                    input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);

                } else if (position == 1) {
                    ArrayAdapter<String> adapterTemperature = new ArrayAdapter<>(getApplicationContext(), R.layout.my_selected_item, listUnitsTemperature);
                    adapterTemperature.setDropDownViewResource(R.layout.my_dropdown_item);
                    unitsFrom.setAdapter(adapterTemperature);
                    unitsTo.setAdapter(adapterTemperature);

                    EditText input = findViewById(R.id.txtNumber);
                    input.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

    }
    public void onBtnChangeClick(View view){
        change();
        EditText inputTxt = findViewById(R.id.txtNumber);
        double input = Double.parseDouble(inputTxt.getText().toString());
        TextView resultView = findViewById(R.id.textResult);

        if(input != 0 ){
            if(input == -0 || input == '-' || input == '.'|| input == '-'+'.' ){
                resultView.setText("Wrong input!");
            }
            if (options.getSelectedItemPosition() == 0) {
                CalculateLength();
            } else if (options.getSelectedItemPosition() == 1) {
                CalculateTemperature();
            }
        }
    }

    // setting what will be done after button clicked
    public void onBtnConvertClick(View view) {
        if (options.getSelectedItemPosition() == 0) {
            CalculateLength();
        } else if (options.getSelectedItemPosition() == 1) {
            CalculateTemperature();
        }
    }
    //TODO repair result, number without E


    // method to convert length in each case
    void CalculateLength() {
        positionFrom = unitsFrom.getSelectedItemPosition();
        positionTo = unitsTo.getSelectedItemPosition();
        txtUnitFrom = findViewById(R.id.txtUnitFrom);
        EditText inputTxt = findViewById(R.id.txtNumber);
        double input = Double.parseDouble(inputTxt.getText().toString());
        TextView resultView = findViewById(R.id.textResult);
        double result;
        Log.e("from value in method", String.valueOf(positionFrom));

        switch (positionFrom){
            case 0:
                txtUnitFrom.setText("mm");
                break;
            case 1:
                txtUnitFrom.setText("cm");
                break;
            case 2:
                txtUnitFrom.setText("dm");
                break;
            case 3:
                txtUnitFrom.setText("m");
                break;
            case 4:
                txtUnitFrom.setText("km");
                break;
            default:
                txtUnitFrom.setText("");

        }

        if(input == -0 || input == '-' || input == '.'|| input == '-'+'.' ){
            resultView.setText("Wrong input!");
        }
        //checking if number is negative
        else if(input<0){
            resultView.setText("The length cannot be negative");
        }

        //from millimeter
        else if (positionFrom == 0) {

            if (positionTo == 0) {
                result = input;
                resultView.setText(input + " mm = " + roundTo3DecimalPlace(result) + " mm");
            }
            if (positionTo == 1) {
                result = input / 10;
                resultView.setText(input + " mm = " + roundTo3DecimalPlace(result) + " cm");
            }
            if (positionTo == 2) {
                result = input / 100;
                resultView.setText(input + " mm = " + roundTo3DecimalPlace(result) + " dm");
            }
            if (positionTo == 3) {
                result = input / 1000;
                resultView.setText(input + " mm = " + roundTo3DecimalPlace(result) + " m");
            }
            if (positionTo == 4) {
                result = input / 1000000;
                resultView.setText(input + " mm = " + roundTo3DecimalPlace(result) + " km");
            }
        }

        //from centimeter
        else if (positionFrom == 1) {

            if (positionTo == 0) {
                result = input * 10;
                resultView.setText(input + " cm = " + roundTo3DecimalPlace(result) + " mm");
            }
            if (positionTo == 1) {
                result = input;
                resultView.setText(input + " cm = " + roundTo3DecimalPlace(result) + " cm");
            }
            if (positionTo == 2) {
                result = input / 10;
                resultView.setText(input + " cm = " + roundTo3DecimalPlace(result) + " dm");
            }
            if (positionTo == 3) {
                result = input / 100;
                resultView.setText(input + " cm = " + roundTo3DecimalPlace(result) + " m");
            }
            if (positionTo == 4) {
                result = input / 100000;
                resultView.setText(input + " cm = " + roundTo3DecimalPlace(result) + " km");
            }
        }
        //from decimeter
        else if (positionFrom == 2) {

            if (positionTo == 0) {
                result = input * 100;
                resultView.setText(input + " dm = " + roundTo3DecimalPlace(result) + " mm");
            }
            if (positionTo == 1) {
                result = input * 10;
                resultView.setText(input + " dm = " + roundTo3DecimalPlace(result) + " cm");
            }
            if (positionTo == 2) {
                result = input;
                resultView.setText(input + " dm = " + roundTo3DecimalPlace(result) + " dm");
            }
            if (positionTo == 3) {
                result = input / 10;
                resultView.setText(input + " dm = " + roundTo3DecimalPlace(result) + " m");
            }
            if (positionTo == 4) {
                result = input / 10000;
                resultView.setText(input + " dm = " + roundTo3DecimalPlace(result) + " km");
            }
        }
        //from meter
        else if (positionFrom == 3) {

            if (positionTo == 0) {
                result = input * 1000;
                resultView.setText(input + " m = " + roundTo3DecimalPlace(result) + " mm");
            }
            if (positionTo == 1) {
                result = input * 100.0;
                resultView.setText(input + " m = " + roundTo3DecimalPlace(result) + " cm");
            }
            if (positionTo == 2) {
                result = input * 10;
                resultView.setText(input + " m = " + roundTo3DecimalPlace(result) + " dm");
            }
            if (positionTo == 3) {
                result = input;
                resultView.setText(input + " m = " + roundTo3DecimalPlace(result) + "m");
            }
            if (positionTo == 4) {
                result = input / 1000;
                resultView.setText(input + " m = " + roundTo3DecimalPlace(result) + " km");
            }
        }
        //from kilometer
        else if (positionFrom == 4) {

            if (positionTo == 0) {
                result = input * 1000000;
                resultView.setText(input + " km = " + roundTo3DecimalPlace(result) + " mm");
            }
            if (positionTo == 1) {
                result = input * 100000;
                resultView.setText(input + " km = " + roundTo3DecimalPlace(result) + " cm");
            }
            if (positionTo == 2) {
                result = input * 10000;
                resultView.setText(input + " km = " + roundTo3DecimalPlace(result) + " dm");
            }
            if (positionTo == 3) {
                result = input * 1000;
                resultView.setText(input + " km = " + roundTo3DecimalPlace(result) + " m");
            }
            if (positionTo == 4) {
                result = input;
                resultView.setText(input + " km = " + roundTo3DecimalPlace(result) + " km");
            }
        }
    }
    //method to convert temperature in each case
    void CalculateTemperature() {
        positionFrom = unitsFrom.getSelectedItemPosition();
        positionTo = unitsTo.getSelectedItemPosition();
        EditText inputTxt = findViewById(R.id.txtNumber);
        double input = Double.parseDouble(inputTxt.getText().toString());
        TextView resultView = findViewById(R.id.textResult);
        double result;
        Log.e("from value in method", String.valueOf(positionFrom));
        Log.e("to value in method", String.valueOf(positionTo));

        if(input == -0 || input == '-' || input == '.'|| input == '-'+'.' ){
            resultView.setText("Wrong input!");
        }
        //from celsius
        else if (positionFrom == 0) {
            if (positionTo == 0) {
                result = input;
                resultView.setText(input  +" \u2103 = " + roundTo3DecimalPlace(result) + " \u2103 ");
            }
            if (positionTo == 1) {
                result = (9.0/5 * input) + 32;
                resultView.setText(input + " \u2103 = " + roundTo3DecimalPlace(result) + " \u2109");
            }
            if (positionTo == 2) {
                result = input + 273.15;
                resultView.setText(input + " \u2103 = " + roundTo3DecimalPlace(result) + " K");
            }
        }
        //from fahrenheit
        else if  (positionFrom == 1) {
            if (positionTo == 0) {
                result = (5.0/9 * input) - 17.78;
                resultView.setText(input + " \u2109 = " + roundTo3DecimalPlace(result) + " \u2103");
            }
            if (positionTo == 1) {
                result = input;
                resultView.setText(input + " \u2109 = " + roundTo3DecimalPlace(result) + " \u2109");
            }
            if (positionTo == 2) {
                result = (5.0/9 * input) + 255.37;
                resultView.setText(input + " \u2109 = " + roundTo3DecimalPlace(result) + " K");
            }
        }
        //from kelvin
        else if (positionFrom == 2) {
            if (positionTo == 0) {
                result = input - 273.15;
                resultView.setText(input + " K = " + roundTo3DecimalPlace(result) + " \u2103");
            }
            if (positionTo == 1) {
                result = (9.0/5 * input) - 459.67;
                resultView.setText(input + " K = " + roundTo3DecimalPlace(result) + " \u2109");
            }
            if (positionTo == 2) {
                result = input;
                resultView.setText(input + " K = " + roundTo3DecimalPlace(result) + " K");
            }
        }

    }
    // method to round value to 3 decimal place
    public static double roundTo3DecimalPlace(double value) {
        return Math.round(value * 1000.0) / 1000.0;
    }

    void change(){
        String from = unitsFrom.getTransitionName();
        positionFrom = unitsFrom.getSelectedItemPosition();

        String to = unitsTo.getTransitionName();
        positionTo = unitsTo.getSelectedItemPosition();

        unitsTo.setSelection(positionFrom);
        Log.i("INFO", "The selected position from is: " + positionFrom);

        unitsFrom.setSelection(positionTo);
        Log.i("INFO", "The selected position to is: " + positionTo);

    }
}


