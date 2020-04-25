package com.example.sorterapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Box3 extends AppCompatActivity {

    private Spinner shapeSpinner;
    private Spinner colorSpinner;
    private EditText minWeight;
    private EditText maxWeight;
    private Button save;

    private String bluetoothData="";
    private  String flag="";

    private String box1,box2,box3;
    private String shape1,shape2,shape3,color1,color2,color3,weight1,weight2,weight3;
    private List<String> box1List= new ArrayList<>();
    private List<String> box2List= new ArrayList<>();
    private List<String> box3List= new ArrayList<>();
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_box3);
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide app name bar

        Intent intent=getIntent();
        bluetoothData=intent.getStringExtra("bluetoothData");
        flag=intent.getStringExtra("flag");

        box1List=intent.getStringArrayListExtra("box1List");
        box2List=intent.getStringArrayListExtra("box2List");

        if(bluetoothData==null)
            bluetoothData="";

        shapeSpinner=findViewById(R.id.shapeSpinner3);
        colorSpinner=findViewById(R.id.colorSpinner3);
        minWeight=findViewById(R.id.idMinWeight3);
        maxWeight=findViewById(R.id.idMaxWeight3);
        save=findViewById(R.id.save3);
        minWeight.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "500")});
        maxWeight.setFilters(new InputFilter[]{ new InputFilterMinMax("0", "500")});

    }

    public void saveBox3(View view) {
        String dataBox3="";
        boolean shape=!shapeSpinner.getSelectedItem().toString().equals("Odaberi oblik");
        boolean color=!colorSpinner.getSelectedItem().toString().equals("Odaberi boju");
        boolean maxWeightBool=maxWeight.getText().toString().trim().length() > 0;
        boolean minWeightBool=minWeight.getText().toString().trim().length() > 0;

        String min=minWeight.getText().toString();
        String max=maxWeight.getText().toString();

        while(min.startsWith("0")){
            min=min.substring(1);
        }
        while(max.startsWith("0")){
            max=max.substring(1);
        }
        maxWeight.setText(max);
        minWeight.setText(min);

        if (!minWeightBool && maxWeightBool)
            Toast.makeText(getApplicationContext(), "Unesi minimalnu masu!", Toast.LENGTH_SHORT).show();
        else if (minWeightBool && !maxWeightBool)
            Toast.makeText(getApplicationContext(), "Unesi maksimalnu masu!", Toast.LENGTH_SHORT).show();
        else if (minWeightBool && maxWeightBool && Integer.parseInt(minWeight.getText().toString()) > Integer.parseInt(maxWeight.getText().toString()) )
            Toast.makeText(getApplicationContext(), "Greška! Minimum je veći od maksimuma!", Toast.LENGTH_SHORT).show();
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

            updateBox(dataBox3);
            separeteBoxesAndItems();
            fillList();


            Intent intent = new Intent(Box3.this, MenuActivity.class);
            intent.putExtra("bluetoothData", bluetoothData);
            intent.putExtra("flag", flag);
            intent.putStringArrayListExtra("box3List", (ArrayList<String>) box3List);
            intent.putStringArrayListExtra("box2List", (ArrayList<String>) box2List);
            intent.putStringArrayListExtra("box1List", (ArrayList<String>) box1List);

            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    }

    private void fillList() {
        box3List.clear();
        box3List.add(shape3);
        box3List.add(color3);
        box3List.add(weight3);
    }

    private void separeteBoxesAndItems() {
        box1=bluetoothData.split("&")[0];
        box2=bluetoothData.split("&")[1];
        box3=bluetoothData.split("&")[2];

        shape1=box1.split("/")[1];
        color1=box1.split("/")[2];
        weight1=box1.split("/")[3]+"-"+box1.split("/")[4];

        shape2=box2.split("/")[1];
        color2=box2.split("/")[2];
        weight2=box2.split("/")[3]+"-"+box2.split("/")[4];

        shape3=box3.split("/")[1];
        color3=box3.split("/")[2];
        weight3=box3.split("/")[3]+"-"+box3.split("/")[4];
    }

    public void updateBox(String dataBox3){
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
    }
}
