package com.example.gginiggini.Adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gginiggini.Activity.Home;
import com.example.gginiggini.Class.Flag;
//import com.example.gginiggini.Fragment.MainTab_Cafe1;
//import com.example.gginiggini.Fragment.TodayMenu_Domi;
//mport com.example.gginiggini.Fragment.TodayMenu_Gru;
import com.example.gginiggini.Fragment.TodayMenu_Domi;
import com.example.gginiggini.Fragment.TodayMenu_Gru;
import com.example.gginiggini.Fragment.TodayMenu_Sangrok;
import com.example.gginiggini.Fragment.TodayMenu_Staff;
//import com.example.gginiggini.Fragment.TodayMenu_Staff;

public class Home_PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    Flag flag=new Flag();
    Context context;
    //constructor
    public Home_PageAdapter( FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;

    }
    public Home_PageAdapter(FragmentManager fm) {
        super(fm);
    }

    //selcet fragment
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                TodayMenu_Sangrok todayMenu_Sangrok = new  TodayMenu_Sangrok();
                Log.v("after_Testdasdassdfafd!", "??");
                flag.setCafeFlag("상록원");
                return todayMenu_Sangrok;
            case 1:
                TodayMenu_Domi todayMenu_Domi = new TodayMenu_Domi();
                Log.v("***************!", "??");
                flag.setCafeFlag("기숙사식당");
                return todayMenu_Domi;
            case 2:
                TodayMenu_Gru todayMenu_Gru = new TodayMenu_Gru();
                Log.v("&&&&&&&&&&&&", "??");
                flag.setCafeFlag("그루터기");
                return todayMenu_Gru;
            case 3:
                TodayMenu_Staff todayMenu_Staff = new TodayMenu_Staff();
                Log.v("after_Testdasdassdfafd!", "??");
                flag.setCafeFlag("교직원");
                return todayMenu_Staff;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}