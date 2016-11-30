package com.example.gginiggini.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.gginiggini.Fragment.RankLikeTop10;
import com.example.gginiggini.Fragment.RankScoreTop10;

public class Rank_PageAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    //constructor
    public Rank_PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }
    public Rank_PageAdapter(FragmentManager fm) {
        super(fm);
    }

    //selcet fragment
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RankLikeTop10 rankLikeTop10= new RankLikeTop10();
                return rankLikeTop10;
            case 1:
                RankScoreTop10 rankScoreTop10= new RankScoreTop10();
                return rankScoreTop10;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}