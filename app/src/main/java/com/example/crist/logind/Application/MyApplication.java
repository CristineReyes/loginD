package com.example.crist.logind.Application;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Cristine Aguirre on 30/10/2018.
 * Company: Mobility Apps Inc
 * Insist, Persist, Resist and Never Give Up
 */
public class MyApplication extends Application {

    // SE INICIALIZA EN LA PARTE DEL MANIFEST


    @Override
    public void onCreate() {
        super.onCreate();

        FlowManager.init(this);  // SE INICIALIZA LA LIBRERIA
    }
}
