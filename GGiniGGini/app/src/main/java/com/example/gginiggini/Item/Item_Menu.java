package com.example.gginiggini.Item;

import android.graphics.drawable.Drawable;

/**
 * Created by 이용준 on 2016-11-05.
 */

public class Item_Menu {
    private Drawable mPhoto;
    private Drawable mLike;
    private String lPhoto;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    private String cName;
    private String mName;
    private int lCount;
    private String mPrice;
    private String mWhen;
    private String mDetail;
    private int score;

    public Drawable getmPhoto() {
        return mPhoto;
    }

    public void setmPhoto(Drawable mPhoto) {
        this.mPhoto = mPhoto;
    }

    public String getlPhoto() {
        return lPhoto;
    }

    public void setlPhoto(String lPhoto) {
        this.lPhoto = lPhoto;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getlCount() {
        return lCount;
    }

    public void setlCount(int lCount) {
        this.lCount = lCount;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getmWhen() {
        return mWhen;
    }

    public void setmWhen(String mWhen) {
        this.mWhen = mWhen;
    }

    public String getmDetail() {
        return mDetail;
    }

    public void setmDetail(String mDetail) {
        this.mDetail = mDetail;
    }

    public Drawable getmLike() {
        return mLike;
    }

    public void setmLike(Drawable mLike) {
        this.mLike = mLike;
    }
}
