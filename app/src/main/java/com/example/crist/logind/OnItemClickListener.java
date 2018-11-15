package com.example.crist.logind;

import com.example.crist.logind.Modelo.Slug;

/**
 * Created by Cristine Aguirre on 25/10/2018.
 * Company: Mobility Apps Inc
 * Insist, Persist, Resist and Never Give Up
 */
public interface OnItemClickListener {

    void onItemCLick(Slug slug);

    void onLongItemClick(Slug slug);
}
