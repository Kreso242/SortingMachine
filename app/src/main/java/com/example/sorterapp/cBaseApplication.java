package com.example.sorterapp;

import android.app.Application;

public class cBaseApplication extends Application {

    public Bluetooth bluetooth;

    @Override
    public void onCreate()
    {
        super.onCreate();
        bluetooth = new Bluetooth();
    }

}