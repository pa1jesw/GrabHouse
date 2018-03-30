package com.fcproject.grabhouce;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by MUKESH on 14-02-2018.
 */

public class tabsPager extends FragmentStatePagerAdapter{

    int mNumberOfTabs;
    String [] titles = new String[]{"Purchase","Rent","Sell"};



    public tabsPager(FragmentManager fm,int NumberOfTabs) {
        super(fm);
        this.mNumberOfTabs = NumberOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return titles[position];
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
              //  purchaseFragment pf = new purchaseFragment();
               // return pf;
            case 1:
             //   rentFragment rf = new rentFragment();
               // return rf;
            case 2:
           //     sellFragment sf = new sellFragment();
           //     return sf;
            default:
                return null;
        }
    }

    @Override
    public int getCount()
    {
        return mNumberOfTabs;
    }
}
