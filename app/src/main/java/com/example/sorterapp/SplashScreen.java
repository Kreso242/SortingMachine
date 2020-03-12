package com.example.sorterapp;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;


public class SplashScreen extends AppCompatActivity{
    private GifImageView gifImageView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide(); //hide app name bar
        gifImageView=(GifImageView)findViewById(R.id.gifImageView);

        try{
            InputStream inputStream=getAssets().open("embedded_gif.gif");
            byte[] bytes= IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //wait for 3 seconds
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void run() {
                SplashScreen.this.startActivity(new Intent(SplashScreen.this,MenuActivity.class));
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                SplashScreen.this.finish();
            }
        },5000);
    }

}