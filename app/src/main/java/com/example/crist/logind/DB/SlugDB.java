package com.example.crist.logind.DB;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Cristine Aguirre on 30/10/2018.
 * Company: Mobility Apps Inc
 * Insist, Persist, Resist and Never Give Up
 */

/// SE CREA LA BASE DE DATOS

@Database(name = SlugDB.NAME, version = SlugDB.VERSION)
public class SlugDB {

    public static final  String NAME= "SlugDataBase";
    public static final  int VERSION = 1;
}
