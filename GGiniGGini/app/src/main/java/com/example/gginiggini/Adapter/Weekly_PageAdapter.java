package com.example.gginiggini.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.gginiggini.Fragment.WeeklyMenu_Fri;
import com.example.gginiggini.Fragment.WeeklyMenu_Mon;
import com.example.gginiggini.Fragment.WeeklyMenu_Sat;
import com.example.gginiggini.Fragment.WeeklyMenu_Sun;
import com.example.gginiggini.Fragment.WeeklyMenu_Thu;
import com.example.gginiggini.Fragment.WeeklyMenu_Tue;
import com.example.gginiggini.Fragment.WeeklyMenu_Wed;

public class Weekly_PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    String cafeName;
    //constructor
    public Weekly_PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.cafeName = cafeName;
        this.mNumOfTabs = NumOfTabs;
    }
    public Weekly_PageAdapter(FragmentManager fm) {
        super(fm);
    }

    //selcet fragment
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                WeeklyMenu_Sun mainTab_Cafe7 = new  WeeklyMenu_Sun();
                return mainTab_Cafe7;
            case 1:
                WeeklyMenu_Mon mainTab_Cafe1 = new WeeklyMenu_Mon();
                return mainTab_Cafe1;
            case 2:
                WeeklyMenu_Tue mainTab_Cafe2 = new  WeeklyMenu_Tue();
                return mainTab_Cafe2;
            case 3:
                WeeklyMenu_Wed mainTab_Cafe3 = new  WeeklyMenu_Wed();
                return mainTab_Cafe3;
            case 4:
                WeeklyMenu_Thu mainTab_Cafe4 = new  WeeklyMenu_Thu();
                return mainTab_Cafe4;
            case 5:
                WeeklyMenu_Fri mainTab_Cafe5 = new  WeeklyMenu_Fri();
                return mainTab_Cafe5;
            case 6:
                WeeklyMenu_Sat mainTab_Cafe6 = new  WeeklyMenu_Sat();
                return mainTab_Cafe6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}