package com.example.gginiggini.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.gginiggini.Fragment.MainTab_Cafe1;
public class Home_PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    public Home_PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MainTab_Cafe1 mainTab_Cafe1 = new MainTab_Cafe1();
                return mainTab_Cafe1;
            case 1:
                MainTab_Cafe1 mainTab_Cafe2 = new MainTab_Cafe1();
                return mainTab_Cafe2;
            case 2:
                MainTab_Cafe1 mainTab_Cafe3 = new MainTab_Cafe1();
                return mainTab_Cafe3;
            case 3:
                MainTab_Cafe1 mainTab_Cafe4 = new MainTab_Cafe1();
                return mainTab_Cafe4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}