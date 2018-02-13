package com.fcproject.grabhouce;

/**
 * Created by MUKESH on 13-02-2018.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class tab1purchase extends  Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1purchase, container, false);
        return rootView;
    }
}
