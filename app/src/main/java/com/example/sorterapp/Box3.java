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

public class Box3 extends AppCompatActivity {

    private Spinner shapeSpinner;
    private Spinner colorSpinner;
    private EditText minWeight;
    private EditText maxWeight;
    private Button save;

    private String bluetoothData="";
    private  String flag="";


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box3);
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide app name bar

        Intent intent=getIntent();
        bluetoothData=intent.getStringExtra("bluetoothData");
        flag=intent.getStringExtra("flag");

        if(bluetoothData==null)
            bluetoothData="";

        shapeSpinner=findViewById(R.id.shapeSpinner3);
        colorSpinner=findViewById(R.id.colorSpinner3);
        minWeight=findViewById(R.id.idMinWeight3);
        maxWeight=findViewById(R.id.idMaxWeight3);
        save=findViewById(R.id.save3);

    }

    public void saveBox3(View view) {
        String dataBox3="";
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
                dataBox3 = "B3/"+shapeSpinner.getSelectedItem().toString() + "/" + colorSpinner.getSelectedItem().toString() + "/" + minWeight.getText() + "/" + maxWeight.getText();
            else if (!shape && color && maxWeightBool)
                dataBox3 = "B3/"+"X" + "/" + colorSpinner.getSelectedItem().toString() + "/" + minWeight.getText() + "/" + maxWeight.getText();
            else if (shape && !color && maxWeightBool)
                dataBox3 = "B3/"+shapeSpinner.getSelectedItem().toString() + "/" + "X" + "/" + minWeight.getText() + "/" + maxWeight.getText();
            else if (shape && color && !maxWeightBool)
                dataBox3 = "B3/"+shapeSpinner.getSelectedItem().toString() + "/" + colorSpinner.getSelectedItem().toString() + "/" + "X" + "/" + "X";
            else if (!shape && !color && maxWeightBool)
                dataBox3 = "B3/"+"X" + "/" + "X" + "/" + minWeight.getText() + "/" + maxWeight.getText();
            else if (!shape && color && !maxWeightBool)
                dataBox3 = "B3/"+"X" + "/" + colorSpinner.getSelectedItem().toString() + "/" + "X" + "/" + "X";
            else if (shape && !color && !maxWeightBool)
                dataBox3 = "B3/"+shapeSpinner.getSelectedItem().toString() + "/" + "X" + "/" + "X" + "/" + "X";
            else
                dataBox3 ="B3/"+"X"+ "/" + "X" + "/" + "X" + "/" + "X";

            if(bluetoothData.equals(""))
                bluetoothData="B1/X/X/X/X&"+"B2/X/X/X/X&"+dataBox3;
            else if (bluetoothData.contains("B1") && !bluetoothData.contains("B2") && !bluetoothData.contains("B3")) //samo B1
                bluetoothData=bluetoothData.split("&")[0]+"&"+"B2/X/X/X/X&"+dataBox3;
            else if (bluetoothData.contains("B1") && bluetoothData.contains("B2") && !bluetoothData.contains("B3"))  // B1 i B2
                bluetoothData=bluetoothData+"&"+dataBox3;
            else if (bluetoothData.contains("B1") && bluetoothData.contains("B2") && bluetoothData.contains("B3")) //B1,B2,B3
                bluetoothData=bluetoothData.split("&")[0]+"&"+bluetoothData.split("&")[1]+"&"+dataBox3;
            else if (!bluetoothData.contains("B1") && bluetoothData.contains("B2") && !bluetoothData.contains("B3")) //samo B2
                bluetoothData="B1/X/X/X/X&"+bluetoothData+"&"+dataBox3;
            else if (!bluetoothData.contains("B1") && bluetoothData.contains("B2") && bluetoothData.contains("B3"))  // B2 i B3
                bluetoothData="B1/X/X/X/X&"+bluetoothData.split("&")[1]+"&"+dataBox3;
            else if (!bluetoothData.contains("B1") && !bluetoothData.contains("B2") && bluetoothData.contains("B3")) //samo B3
                bluetoothData="B1/X/X/X/X&"+"B2/X/X/X/X&"+dataBox3;
            else if (bluetoothData.contains("B1") && !bluetoothData.contains("B2") && bluetoothData.contains("B3")) //B1 i B3
                bluetoothData=bluetoothData.split("&")[0]+"&"+"B2/X/X/X/X&"+dataBox3;



            Intent intent = new Intent(Box3.this, MenuActivity.class);
            intent.putExtra("bluetoothData", bluetoothData);
            intent.putExtra("flag", flag);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }
}
