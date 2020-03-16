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

public class Box2 extends AppCompatActivity {

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
        setContentView(R.layout.activity_box2);
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide app name bar

        Intent intent=getIntent();
        bluetoothData=intent.getStringExtra("bluetoothData");

        if(bluetoothData==null)
            bluetoothData="";

        shapeSpinner=findViewById(R.id.shapeSpinner2);
        colorSpinner=findViewById(R.id.colorSpinner2);
        minWeight=findViewById(R.id.idMinWeight2);
        maxWeight=findViewById(R.id.idMaxWeight2);
        save=findViewById(R.id.save2);

    }

    public void saveBox2(View view) {
        String dataBox2="";
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
                dataBox2 = "B2/"+shapeSpinner.getSelectedItem().toString() + "/" + colorSpinner.getSelectedItem().toString() + "/" + minWeight.getText() + "/" + maxWeight.getText();
            else if (!shape && color && maxWeightBool)
                dataBox2 = "B2/"+"X" + "/" + colorSpinner.getSelectedItem().toString() + "/" + minWeight.getText() + "/" + maxWeight.getText();
            else if (shape && !color && maxWeightBool)
                dataBox2 = "B2/"+shapeSpinner.getSelectedItem().toString() + "/" + "X" + "/" + minWeight.getText() + "/" + maxWeight.getText();
            else if (shape && color && !maxWeightBool)
                dataBox2 = "B2/"+shapeSpinner.getSelectedItem().toString() + "/" + colorSpinner.getSelectedItem().toString() + "/" + "X" + "/" + "X";
            else if (!shape && !color && maxWeightBool)
                dataBox2 = "B2/"+"X" + "/" + "X" + "/" + minWeight.getText() + "/" + maxWeight.getText();
            else if (!shape && color && !maxWeightBool)
                dataBox2 = "B2/"+"X" + "/" + colorSpinner.getSelectedItem().toString() + "/" + "X" + "/" + "X";
            else if (shape && !color && !maxWeightBool)
                dataBox2 = "B2/"+shapeSpinner.getSelectedItem().toString() + "/" + "X" + "/" + "X" + "/" + "X";
            else
                dataBox2 ="B2/"+"X"+ "/" + "X" + "/" + "X" + "/" + "X";

            if(bluetoothData.contains("B1") && !bluetoothData.contains("B3"))
                bluetoothData=bluetoothData+"&"+dataBox2;
            else if(!bluetoothData.contains("B1") && bluetoothData.contains("B3"))
                bluetoothData=dataBox2+"&"+bluetoothData;
            else if(bluetoothData.contains("B1") && bluetoothData.contains("B3")){
                String box1=bluetoothData.split("&")[0];
                String box3=bluetoothData.split("&")[1];
                bluetoothData=box1+"&"+dataBox2+"&"+box3;
            }
            else
                bluetoothData=dataBox2;


            Intent intent = new Intent(Box2.this, MenuActivity.class);
            intent.putExtra("bluetoothData", bluetoothData);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}
