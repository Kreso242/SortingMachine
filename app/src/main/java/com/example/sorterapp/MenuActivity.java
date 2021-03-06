package com.example.sorterapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MenuActivity extends AppCompatActivity {

    private ImageView arrow1;
    private ImageView arrow2;
    private ImageView arrow3;
    private boolean flagBox1=false;
    private boolean flagBox2=false;
    private boolean flagBox3=false;
    private RelativeLayout content1;
    private RelativeLayout content2;
    private RelativeLayout content3;
    private RelativeLayout box1;
    private RelativeLayout box2;
    private RelativeLayout box3;
    private ImageView cross1;
    private ImageView cross2;
    private ImageView cross3;
    private ImageView check1;
    private ImageView check2;
    private ImageView check3;
    private ImageView containterImage1;
    private ImageView containterImage2;
    private ImageView containterImage3;
    private TextView containterName1;
    private TextView containterName2;
    private TextView containterName3;
    private TextView contentShape1;
    private TextView contentShape2;
    private TextView contentShape3;
    private TextView contentColor1;
    private TextView contentColor2;
    private TextView contentColor3;
    private TextView contentWeight1;
    private TextView contentWeight2;
    private TextView contentWeight3;
    private TextView contentShape1_1;
    private TextView contentShape2_2;
    private TextView contentShape3_3;
    private TextView contentColor1_1;
    private TextView contentColor2_2;
    private TextView contentColor3_3;
    private TextView contentWeight1_1;
    private TextView contentWeight2_2;
    private TextView contentWeight3_3;

    private String bluetoothData="";
    private String flag="1";

    private boolean jelMoze=true;
    private List<Boolean> booleanList= new ArrayList<Boolean>();
    private List<Boolean> weightBooleanList= new ArrayList<Boolean>();


    public static List<String> box1List= new ArrayList<>();
    public static List<String> box2List= new ArrayList<>();
    public static List<String> box3List= new ArrayList<>();


    private List<String> cantGo= new ArrayList<>();


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide app name bar


        BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
        if (!btAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, 1);
        }

        booleanList.add(false);
        booleanList.add(false);
        booleanList.add(false);
        weightBooleanList.add(false);
        weightBooleanList.add(false);
        weightBooleanList.add(false);

        Intent intent=getIntent();
        flag=intent.getStringExtra("flag");
        box1List=intent.getStringArrayListExtra("box1List");
        box2List=intent.getStringArrayListExtra("box2List");
        box3List=intent.getStringArrayListExtra("box3List");

        if(flag==null)
            flag="1";

        initialization();
        getBoxData();

    }



    private void getBoxData() {
        Intent intent=getIntent();
        bluetoothData=intent.getStringExtra("bluetoothData");
        int br=0;
        int countB1=0;
        int countB2=0;
        int countB3=0;
        if(bluetoothData!=null) {

                if (bluetoothData.contains("B1") && !bluetoothData.contains("B2") && !bluetoothData.contains("B3")) {
                    contentShape1_1.setText(bluetoothData.split("/")[1]);
                    contentColor1_1.setText(bluetoothData.split("/")[2]);
                    contentWeight1_1.setText((bluetoothData.split("/")[3] + "g - " + bluetoothData.split("/")[4] + "g").replaceAll("&B2", ""));

                    contentShape2_2.setText("X");
                    contentColor2_2.setText("X");
                    contentWeight2_2.setText("Xg - Xg");
                    contentShape3_3.setText("X");
                    contentColor3_3.setText("X");
                    contentWeight3_3.setText("Xg - Xg");


                } else if (bluetoothData.contains("B1") && bluetoothData.contains("B2") && !bluetoothData.contains("B3")) {
                    contentShape1_1.setText(bluetoothData.split("/")[1]);
                    contentColor1_1.setText(bluetoothData.split("/")[2]);
                    contentWeight1_1.setText((bluetoothData.split("/")[3] + "g - " + bluetoothData.split("/")[4] + "g").replaceAll("&B2", ""));
                    contentShape2_2.setText(bluetoothData.split("/")[5]);
                    contentColor2_2.setText(bluetoothData.split("/")[6]);
                    contentWeight2_2.setText((bluetoothData.split("/")[7] + "g - " + bluetoothData.split("/")[8] + "g").replaceAll("&B3", ""));

                    contentShape3_3.setText("X");
                    contentColor3_3.setText("X");
                    contentWeight3_3.setText("Xg - Xg");


                } else if (bluetoothData.contains("B1") && !bluetoothData.contains("B2") && bluetoothData.contains("B3")) {
                    contentShape1_1.setText(bluetoothData.split("/")[1]);
                    contentColor1_1.setText(bluetoothData.split("/")[2]);
                    contentWeight1_1.setText((bluetoothData.split("/")[3] + "g - " + bluetoothData.split("/")[4] + "g").replaceAll("&B2", ""));

                    contentShape2_2.setText("X");
                    contentColor2_2.setText("X");
                    contentWeight2_2.setText("Xg - Xg");

                    contentShape3_3.setText(bluetoothData.split("/")[5]);
                    contentColor3_3.setText(bluetoothData.split("/")[6]);
                    contentWeight3_3.setText(bluetoothData.split("/")[7] + "g - " + bluetoothData.split("/")[8] + "g");


                } else if (bluetoothData.contains("B1") && bluetoothData.contains("B2") && bluetoothData.contains("B3")) {
                    contentShape1_1.setText(bluetoothData.split("/")[1]);
                    contentColor1_1.setText(bluetoothData.split("/")[2]);
                    contentWeight1_1.setText((bluetoothData.split("/")[3] + "g - " + bluetoothData.split("/")[4] + "g").replaceAll("&B2", ""));
                    contentShape2_2.setText(bluetoothData.split("/")[5]);
                    contentColor2_2.setText(bluetoothData.split("/")[6]);
                    contentWeight2_2.setText((bluetoothData.split("/")[7] + "g - " + bluetoothData.split("/")[8] + "g").replaceAll("&B3", ""));
                    contentShape3_3.setText(bluetoothData.split("/")[9]);
                    contentColor3_3.setText(bluetoothData.split("/")[10]);
                    contentWeight3_3.setText(bluetoothData.split("/")[11] + "g - " + bluetoothData.split("/")[12] + "g");


                } else if (!bluetoothData.contains("B1") && bluetoothData.contains("B2") && !bluetoothData.contains("B3")) {
                    contentShape1_1.setText("X");
                    contentColor1_1.setText("X");
                    contentWeight1_1.setText("Xg - Xg");

                    contentShape2_2.setText(bluetoothData.split("/")[1]);
                    contentColor2_2.setText(bluetoothData.split("/")[2]);
                    contentWeight2_2.setText((bluetoothData.split("/")[3] + "g - " + bluetoothData.split("/")[4] + "g").replaceAll("&B3", ""));

                    contentShape3_3.setText("X");
                    contentColor3_3.setText("X");
                    contentWeight3_3.setText("Xg - Xg");


                } else if (!bluetoothData.contains("B1") && bluetoothData.contains("B2") && bluetoothData.contains("B3")) {
                    contentShape1_1.setText("X");
                    contentColor1_1.setText("X");
                    contentWeight1_1.setText("Xg - Xg");

                    contentShape2_2.setText(bluetoothData.split("/")[1]);
                    contentColor2_2.setText(bluetoothData.split("/")[2]);
                    contentWeight2_2.setText((bluetoothData.split("/")[3] + "g - " + bluetoothData.split("/")[4] + "g").replaceAll("&B3", ""));
                    contentShape3_3.setText(bluetoothData.split("/")[5]);
                    contentColor3_3.setText(bluetoothData.split("/")[6]);
                    contentWeight3_3.setText(bluetoothData.split("/")[7] + "g - " + bluetoothData.split("/")[8] + "g");

                } else if (!bluetoothData.contains("B1") && !bluetoothData.contains("B2") && bluetoothData.contains("B3")) {
                    contentShape1_1.setText("X");
                    contentColor1_1.setText("X");
                    contentWeight1_1.setText("Xg - Xg");
                    contentShape2_2.setText("X");
                    contentColor2_2.setText("X");
                    contentWeight2_2.setText("Xg - Xg");

                    contentShape3_3.setText(bluetoothData.split("/")[1]);
                    contentColor3_3.setText(bluetoothData.split("/")[2]);
                    contentWeight3_3.setText(bluetoothData.split("/")[3] + "g - " + bluetoothData.split("/")[4] + "g");

                }

                if(!contentShape1_1.getText().equals("X") || !contentColor1_1.getText().equals("X") || !contentWeight1_1.getText().equals("Xg - Xg")) {
                    cross1.setVisibility(View.GONE);
                    check1.setVisibility(View.VISIBLE);
                }
                if(!contentShape2_2.getText().equals("X") || !contentColor2_2.getText().equals("X") || !contentWeight2_2.getText().equals("Xg - Xg")) {
                    cross2.setVisibility(View.GONE);
                    check2.setVisibility(View.VISIBLE);
                }
                if(!contentShape3_3.getText().equals("X") || !contentColor3_3.getText().equals("X") || !contentWeight3_3.getText().equals("Xg - Xg")) {
                    cross3.setVisibility(View.GONE);
                    check3.setVisibility(View.VISIBLE);
                }

        }

        Toast.makeText(getApplicationContext(),bluetoothData,Toast.LENGTH_SHORT).show();

    }

    public void sort(View view) {
        if(contentShape1_1.getText().equals("X") && contentColor1_1.getText().equals("X") && contentWeight1_1.getText().equals("Xg - Xg") &&
           contentShape2_2.getText().equals("X") && contentColor2_2.getText().equals("X") && contentWeight2_2.getText().equals("Xg - Xg") &&
           contentShape3_3.getText().equals("X") && contentColor3_3.getText().equals("X") && contentWeight3_3.getText().equals("Xg - Xg"))
        {
            Toast.makeText(getApplicationContext(),"Niste unijeli podatke za sortiranje!",Toast.LENGTH_SHORT).show();
        }
        else {
           checkLists();
           if(!jelMoze){
               if(cantGo.get(0).equals("1")) Toast.makeText(getApplicationContext(),"Promijeni kutiju: 1 !",Toast.LENGTH_SHORT).show();
               else if(cantGo.get(0).equals("2")) Toast.makeText(getApplicationContext(),"Promijeni kutiju: 2 !",Toast.LENGTH_SHORT).show();
               else if(cantGo.get(0).equals("3")) Toast.makeText(getApplicationContext(),"Promijeni kutiju: 3 !",Toast.LENGTH_SHORT).show();
           }
           else {
               Intent intent = new Intent(MenuActivity.this, Bluetooth.class);
               intent.putExtra("bluetoothData", bluetoothData);
               startActivity(intent);
               overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
           }
        }
    }

    private void checkLists() {
        String manji1,veci1,manji2,veci2,manji3,veci3,prvi,drugi,treci;

        if(box1List==null){
            box1List=new ArrayList<String>();
            box1List.add("X");
            box1List.add("X");
            box1List.add("Y-Y");
        }
        if(box2List==null){
            box2List=new ArrayList<String>();
            box2List.add("X");
            box2List.add("X");
            box2List.add("Y-Y");
        }
        if(box3List==null){
            box3List=new ArrayList<String>();
            box3List.add("X");
            box3List.add("X");
            box3List.add("Y-Y");
        }

        prvi=box1List.get(2);
        manji1=prvi.split("-")[0];
        veci1=prvi.split("-")[1];

        drugi=box2List.get(2);
        manji2=drugi.split("-")[0];
        veci2=drugi.split("-")[1];

        treci=box3List.get(2);
        manji3=treci.split("-")[0];
        veci3=treci.split("-")[1];

        if(manji1.equals("X"))
            manji1="0";
        if(manji2.equals("X"))
            manji2="0";
        if(manji3.equals("X"))
            manji3="0";
        if(veci1.equals("X"))
            veci1="500";
        if(veci2.equals("X"))
            veci2="500";
        if(veci3.equals("X"))
            veci3="500";

        if(box1List.get(0).equals(box2List.get(0).toString()) && box1List.get(1).equals(box2List.get(1).toString()))
            booleanList.set(0,true);
        if(box1List.get(0).equals(box3List.get(0).toString()) && box1List.get(1).equals(box3List.get(1).toString()))
            booleanList.set(1,true);
        if(box2List.get(0).equals(box3List.get(0).toString()) && box2List.get(1).equals(box3List.get(1).toString()))
            booleanList.set(2,true);

        if(booleanList.get(0)){
            if(!manji1.equals("Y") && !manji2.equals("Y")) {
                if ((Integer.parseInt(manji1) <= Integer.parseInt(manji2)) && (Integer.parseInt(veci1) >= Integer.parseInt(veci2))) {
                    weightBooleanList.set(1, true);
                    jelMoze = false;
                }
                if ((Integer.parseInt(manji1) >= Integer.parseInt(manji2)) && (Integer.parseInt(veci1) <= Integer.parseInt(veci2))) {
                    weightBooleanList.set(0, true);
                    jelMoze = false;
                }
            }
        }

        if(booleanList.get(1)){
            if(!manji1.equals("Y") && !manji3.equals("Y")) {
                if ((Integer.parseInt(manji1) <= Integer.parseInt(manji3)) && (Integer.parseInt(veci1) >= Integer.parseInt(veci3))) {
                    weightBooleanList.set(2, true);
                    jelMoze = false;
                }
                if ((Integer.parseInt(manji1) >= Integer.parseInt(manji3)) && (Integer.parseInt(veci1) <= Integer.parseInt(veci3))) {
                    weightBooleanList.set(0, true);
                    jelMoze = false;
                }
            }
        }

        if(booleanList.get(2)){
            if(!manji2.equals("Y") && !manji3.equals("Y")) {
                if ((Integer.parseInt(manji2) <= Integer.parseInt(manji3)) && (Integer.parseInt(veci2) >= Integer.parseInt(veci3))) {
                    weightBooleanList.set(2, true);
                    jelMoze = false;
                }
                if ((Integer.parseInt(manji2) >= Integer.parseInt(manji3)) && (Integer.parseInt(veci2) <= Integer.parseInt(veci3))) {
                    weightBooleanList.set(1, true);
                    jelMoze = false;
                }
            }
        }

        if(weightBooleanList.get(0))
            cantGo.add("1");
        if(weightBooleanList.get(1))
            cantGo.add("2");
        if(weightBooleanList.get(2))
            cantGo.add("3");

    }


    public void openBox3(View view) {
        Intent intent = new Intent(MenuActivity.this, Box3.class);
        intent.putExtra("bluetoothData", bluetoothData);
        intent.putExtra("flag", flag);
        intent.putStringArrayListExtra("box1List", (ArrayList<String>) box1List);
        intent.putStringArrayListExtra("box2List", (ArrayList<String>) box2List);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void openBox2(View view) {
        Intent intent = new Intent(MenuActivity.this, Box2.class);
        intent.putExtra("bluetoothData", bluetoothData);
        intent.putExtra("flag", flag);
        intent.putStringArrayListExtra("box1List", (ArrayList<String>) box1List);
        intent.putStringArrayListExtra("box3List", (ArrayList<String>) box3List);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    public void openBox1(View view) {
            Intent intent = new Intent(MenuActivity.this, Box1.class);
            intent.putExtra("bluetoothData", bluetoothData);
            intent.putExtra("flag", flag);
            intent.putStringArrayListExtra("box3List", (ArrayList<String>) box3List);
            intent.putStringArrayListExtra("box2List", (ArrayList<String>) box2List);
            startActivity(intent);
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showContentOfBox1(View view) {
        arrow1=findViewById(R.id.downArrow1);
        arrow2=findViewById(R.id.downArrow2);
        arrow3=findViewById(R.id.downArrow3);
        if(flagBox1==false) {
            arrow1.animate().rotation(180).start();
            arrow2.animate().rotation(0).start();
            arrow3.animate().rotation(0).start();

            flagBox1=true;
            flagBox2=false;
            flagBox3=false;
            content1.setVisibility(View.VISIBLE);
            content2.setVisibility(View.GONE);
            content3.setVisibility(View.GONE);

            content1.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.background_down_corners));
            content2.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));
            content3.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));

            box1.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.background_up_corners));
            box2.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));
            box3.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));

            cross1.setImageResource(R.mipmap.colored_cross);
            cross2.setImageResource(R.mipmap.cross);
            cross3.setImageResource(R.mipmap.cross);

            check1.setImageResource(R.mipmap.colored_check);
            check2.setImageResource(R.mipmap.check);
            check3.setImageResource(R.mipmap.check);

            containterImage1.setImageResource(R.mipmap.colored_box);
            containterImage2.setImageResource(R.mipmap.box);
            containterImage3.setImageResource(R.mipmap.box);

            containterName1.setTextColor(Color.parseColor("#FFFFFF"));
            contentShape1.setTextColor(Color.parseColor("#FFFFFF"));
            contentColor1.setTextColor(Color.parseColor("#FFFFFF"));
            contentWeight1.setTextColor(Color.parseColor("#FFFFFF"));
            contentShape1_1.setTextColor(Color.parseColor("#FFFFFF"));
            contentColor1_1.setTextColor(Color.parseColor("#FFFFFF"));
            contentWeight1_1.setTextColor(Color.parseColor("#FFFFFF"));

            containterName2.setTextColor(Color.parseColor("#000000"));
            contentShape2.setTextColor(Color.parseColor("#000000"));
            contentColor2.setTextColor(Color.parseColor("#000000"));
            contentWeight2.setTextColor(Color.parseColor("#000000"));
            contentShape2_2.setTextColor(Color.parseColor("#000000"));
            contentColor2_2.setTextColor(Color.parseColor("#000000"));
            contentWeight2_2.setTextColor(Color.parseColor("#000000"));

            containterName3.setTextColor(Color.parseColor("#000000"));
            contentShape3.setTextColor(Color.parseColor("#000000"));
            contentColor3.setTextColor(Color.parseColor("#000000"));
            contentWeight3.setTextColor(Color.parseColor("#000000"));
            contentShape3_3.setTextColor(Color.parseColor("#000000"));
            contentColor3_3.setTextColor(Color.parseColor("#000000"));
            contentWeight3_3.setTextColor(Color.parseColor("#000000"));

        }
        else{
            arrow1.animate().rotation(0).start();
            flagBox1=false;
            content1.setVisibility(View.GONE);
            content1.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));
            box1.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));

            cross1.setImageResource(R.mipmap.cross);
            check1.setImageResource(R.mipmap.check);
            containterImage1.setImageResource(R.mipmap.box);

            containterName1.setTextColor(Color.parseColor("#000000"));
            contentShape1.setTextColor(Color.parseColor("#000000"));
            contentColor1.setTextColor(Color.parseColor("#000000"));
            contentWeight1.setTextColor(Color.parseColor("#000000"));
            contentShape1_1.setTextColor(Color.parseColor("#000000"));
            contentColor1_1.setTextColor(Color.parseColor("#000000"));
            contentWeight1_1.setTextColor(Color.parseColor("#000000"));
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showContentOfBox2(View view) {
        arrow1=findViewById(R.id.downArrow1);
        arrow2=findViewById(R.id.downArrow2);
        arrow3=findViewById(R.id.downArrow3);
        if(flagBox2==false) {
            arrow1.animate().rotation(0).start();
            arrow2.animate().rotation(180).start();
            arrow3.animate().rotation(0).start();

            flagBox1=false;
            flagBox2=true;
            flagBox3=false;

            content1.setVisibility(View.GONE);
            content2.setVisibility(View.VISIBLE);
            content3.setVisibility(View.GONE);

            content1.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));
            content2.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.background_down_corners));
            content3.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));

            box1.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));
            box2.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.background_up_corners));
            box3.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));

            cross1.setImageResource(R.mipmap.cross);
            cross2.setImageResource(R.mipmap.colored_cross);
            cross3.setImageResource(R.mipmap.cross);

            check1.setImageResource(R.mipmap.check);
            check2.setImageResource(R.mipmap.colored_check);
            check3.setImageResource(R.mipmap.check);

            containterImage1.setImageResource(R.mipmap.box);
            containterImage2.setImageResource(R.mipmap.colored_box);
            containterImage3.setImageResource(R.mipmap.box);

            containterName1.setTextColor(Color.parseColor("#000000"));
            contentShape1.setTextColor(Color.parseColor("#000000"));
            contentColor1.setTextColor(Color.parseColor("#000000"));
            contentWeight1.setTextColor(Color.parseColor("#000000"));
            contentShape1_1.setTextColor(Color.parseColor("#000000"));
            contentColor1_1.setTextColor(Color.parseColor("#000000"));
            contentWeight1_1.setTextColor(Color.parseColor("#000000"));

            containterName2.setTextColor(Color.parseColor("#FFFFFF"));
            contentShape2.setTextColor(Color.parseColor("#FFFFFF"));
            contentColor2.setTextColor(Color.parseColor("#FFFFFF"));
            contentWeight2.setTextColor(Color.parseColor("#FFFFFF"));
            contentShape2_2.setTextColor(Color.parseColor("#FFFFFF"));
            contentColor2_2.setTextColor(Color.parseColor("#FFFFFF"));
            contentWeight2_2.setTextColor(Color.parseColor("#FFFFFF"));

            containterName3.setTextColor(Color.parseColor("#000000"));
            contentShape3.setTextColor(Color.parseColor("#000000"));
            contentColor3.setTextColor(Color.parseColor("#000000"));
            contentWeight3.setTextColor(Color.parseColor("#000000"));
            contentShape3_3.setTextColor(Color.parseColor("#000000"));
            contentColor3_3.setTextColor(Color.parseColor("#000000"));
            contentWeight3_3.setTextColor(Color.parseColor("#000000"));
        }
        else{
            arrow2.animate().rotation(0).start();
            flagBox2=false;
            content2.setVisibility(View.GONE);
            content2.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));
            box2.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));

            cross2.setImageResource(R.mipmap.cross);
            check2.setImageResource(R.mipmap.check);
            containterImage2.setImageResource(R.mipmap.box);

            containterName2.setTextColor(Color.parseColor("#000000"));
            contentShape2.setTextColor(Color.parseColor("#000000"));
            contentColor2.setTextColor(Color.parseColor("#000000"));
            contentWeight2.setTextColor(Color.parseColor("#000000"));
            contentShape2_2.setTextColor(Color.parseColor("#000000"));
            contentColor2_2.setTextColor(Color.parseColor("#000000"));
            contentWeight2_2.setTextColor(Color.parseColor("#000000"));

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void showContentOfBox3(View view) {
        arrow1=findViewById(R.id.downArrow1);
        arrow2=findViewById(R.id.downArrow2);
        arrow3=findViewById(R.id.downArrow3);
        if(flagBox3==false) {
            arrow1.animate().rotation(0).start();
            arrow2.animate().rotation(0).start();
            arrow3.animate().rotation(180).start();

            flagBox1=false;
            flagBox2=false;
            flagBox3=true;

            content1.setVisibility(View.GONE);
            content2.setVisibility(View.GONE);
            content3.setVisibility(View.VISIBLE);

            content1.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));
            content2.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));
            content3.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.background_down_corners));
            box1.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));
            box2.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));
            box3.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.background_up_corners));

            cross1.setImageResource(R.mipmap.cross);
            cross2.setImageResource(R.mipmap.cross);
            cross3.setImageResource(R.mipmap.colored_cross);

            check1.setImageResource(R.mipmap.check);
            check2.setImageResource(R.mipmap.check);
            check3.setImageResource(R.mipmap.colored_check);

            containterImage1.setImageResource(R.mipmap.box);
            containterImage2.setImageResource(R.mipmap.box);
            containterImage3.setImageResource(R.mipmap.colored_box);

            containterName1.setTextColor(Color.parseColor("#000000"));
            contentShape1.setTextColor(Color.parseColor("#000000"));
            contentColor1.setTextColor(Color.parseColor("#000000"));
            contentWeight1.setTextColor(Color.parseColor("#000000"));
            contentShape1_1.setTextColor(Color.parseColor("#000000"));
            contentColor1_1.setTextColor(Color.parseColor("#000000"));
            contentWeight1_1.setTextColor(Color.parseColor("#000000"));

            containterName2.setTextColor(Color.parseColor("#000000"));
            contentShape2.setTextColor(Color.parseColor("#000000"));
            contentColor2.setTextColor(Color.parseColor("#000000"));
            contentWeight2.setTextColor(Color.parseColor("#000000"));
            contentShape2_2.setTextColor(Color.parseColor("#000000"));
            contentColor2_2.setTextColor(Color.parseColor("#000000"));
            contentWeight2_2.setTextColor(Color.parseColor("#000000"));

            containterName3.setTextColor(Color.parseColor("#FFFFFF"));
            contentShape3.setTextColor(Color.parseColor("#FFFFFF"));
            contentColor3.setTextColor(Color.parseColor("#FFFFFF"));
            contentWeight3.setTextColor(Color.parseColor("#FFFFFF"));
            contentShape3_3.setTextColor(Color.parseColor("#FFFFFF"));
            contentColor3_3.setTextColor(Color.parseColor("#FFFFFF"));
            contentWeight3_3.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else{
            arrow3.animate().rotation(0).start();
            flagBox3=false;
            content3.setVisibility(View.GONE);
            content3.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));
            box3.setBackground(getApplicationContext().getResources().getDrawable(R.drawable.containter_background));

            cross3.setImageResource(R.mipmap.cross);
            check3.setImageResource(R.mipmap.check);
            containterImage3.setImageResource(R.mipmap.box);

            containterName3.setTextColor(Color.parseColor("#000000"));
            contentShape3.setTextColor(Color.parseColor("#000000"));
            contentColor3.setTextColor(Color.parseColor("#000000"));
            contentWeight3.setTextColor(Color.parseColor("#000000"));
            contentShape3_3.setTextColor(Color.parseColor("#000000"));
            contentColor3_3.setTextColor(Color.parseColor("#000000"));
            contentWeight3_3.setTextColor(Color.parseColor("#000000"));
        }
    }

    private void initialization() {
        content1=findViewById(R.id.content1);
        content2=findViewById(R.id.content2);
        content3=findViewById(R.id.content3);

        box1=findViewById(R.id.box1);
        box2=findViewById(R.id.box2);
        box3=findViewById(R.id.box3);

        cross1=findViewById(R.id.cross1);
        cross2=findViewById(R.id.cross2);
        cross3=findViewById(R.id.cross3);

        check1=findViewById(R.id.check1);
        check2=findViewById(R.id.check2);
        check3=findViewById(R.id.check3);

        containterImage1=findViewById(R.id.containterImage1);
        containterImage2=findViewById(R.id.containterImage2);
        containterImage3=findViewById(R.id.containterImage3);

        containterName1=findViewById(R.id.containterName1);
        containterName2=findViewById(R.id.containterName2);
        containterName3=findViewById(R.id.containterName3);

        contentShape1=findViewById(R.id.contentShape1);
        contentShape2=findViewById(R.id.contentShape2);
        contentShape3=findViewById(R.id.contentShape3);

        contentColor1=findViewById(R.id.contentColor1);
        contentColor2=findViewById(R.id.contentColor2);
        contentColor3=findViewById(R.id.contentColor3);

        contentWeight1=findViewById(R.id.contentWeight1);
        contentWeight2=findViewById(R.id.contentWeight2);
        contentWeight3=findViewById(R.id.contentWeight3);

        contentShape1_1=findViewById(R.id.contentShape1_1);
        contentShape2_2=findViewById(R.id.contentShape2_2);
        contentShape3_3=findViewById(R.id.contentShape3_3);

        contentColor1_1=findViewById(R.id.contentColor1_1);
        contentColor2_2=findViewById(R.id.contentColor2_2);
        contentColor3_3=findViewById(R.id.contentColor3_3);

        contentWeight1_1=findViewById(R.id.contentWeight1_1);
        contentWeight2_2=findViewById(R.id.contentWeight2_2);
        contentWeight3_3=findViewById(R.id.contentWeight3_3);

    }


}
