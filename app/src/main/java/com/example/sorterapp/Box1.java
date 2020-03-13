package com.example.sorterapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Objects;

public class Box1 extends AppCompatActivity {

    private Spinner shapeSpinner;
    private Spinner colorSpinner;
    private EditText minWeight;
    private EditText maxWeight;
    private Button save;
    private String bluetoothData="";


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box1);
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide app name bar

        Intent intent=getIntent();
        bluetoothData=bluetoothData+intent.getStringExtra("bluetoothData");

        shapeSpinner=findViewById(R.id.shapeSpinner1);
        colorSpinner=findViewById(R.id.colorSpinner1);
        minWeight=findViewById(R.id.idMinWeight1);
        maxWeight=findViewById(R.id.idMaxWeight1);
        save=findViewById(R.id.save1);


    }

    public void saveBox1(View view) {
        String dataBox1="";
        boolean shape=!shapeSpinner.getSelectedItem().toString().equals("Odaberi oblik");
        boolean color=!colorSpinner.getSelectedItem().toString().equals("Odaberi boju");
        boolean maxWeightBool=maxWeight.getText().toString().trim().length() > 0;
        boolean minWeightBool=minWeight.getText().toString().trim().length() > 0;

        if (!minWeightBool && maxWeightBool)
            Toast.makeText(getApplicationContext(), "Unesi minimalnu masu!", Toast.LENGTH_SHORT).show();
        else if (minWeightBool && !maxWeightBool)
            Toast.makeText(getApplicationContext(), "Unesi maksimalnu masu!", Toast.LENGTH_SHORT).show();
        else {
            if (shape && color && maxWeightBool)
                dataBox1 = "&B1/"+shapeSpinner.getSelectedItem().toString() + "/" + colorSpinner.getSelectedItem().toString() + "/" + minWeight.getText() + "/" + maxWeight.getText();
            else if (!shape && color && maxWeightBool)
                dataBox1 = "&B1/"+"X" + "/" + colorSpinner.getSelectedItem().toString() + "/" + minWeight.getText() + "/" + maxWeight.getText();
            else if (shape && !color && maxWeightBool)
                dataBox1 = "&B1/"+shapeSpinner.getSelectedItem().toString() + "/" + "X" + "/" + minWeight.getText() + "/" + maxWeight.getText();
            else if (shape && color && !maxWeightBool)
                dataBox1 = "&B1/"+shapeSpinner.getSelectedItem().toString() + "/" + colorSpinner.getSelectedItem().toString() + "/" + "X" + "/" + "X";
            else if (!shape && !color && maxWeightBool)
                dataBox1 = "&B1/"+"X" + "/" + "X" + "/" + minWeight.getText() + "/" + maxWeight.getText();
            else if (!shape && color && !maxWeightBool)
                dataBox1 = "&B1/"+"X" + "/" + colorSpinner.getSelectedItem().toString() + "/" + "X" + "/" + "X";
            else if (shape && !color && !maxWeightBool)
                dataBox1 = "&B1/"+shapeSpinner.getSelectedItem().toString() + "/" + "X" + "/" + "X" + "/" + "X";
            else
                dataBox1 ="&B1/"+"X"+ "/" + "X" + "/" + "X" + "/" + "X";

            bluetoothData=bluetoothData+dataBox1;

            Intent intent = new Intent(Box1.this, MenuActivity.class);
            intent.putExtra("box1Data", bluetoothData);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}
