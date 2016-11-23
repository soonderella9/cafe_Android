package com.example.gginiggini.Item;

import android.graphics.drawable.Drawable;

/**
 * Created by 이용준 on 2016-11-05.
 */

public class Item_Menu {
    private Drawable mPhoto;
    private String lPhoto;
    private String mName;
    private String lCount;
    private String mPrice;
    private String score;
    private String bestReply;

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

    public String getlCount() {
        return lCount;
    }

    public void setlCount(String lCount) {
        this.lCount = lCount;
    }

    public String getmPrice() {
        return mPrice;
    }

    public void setmPrice(String mPrice) {
        this.mPrice = mPrice;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getBestReply() {
        return bestReply;
    }

    public void setBestReply(String bestReply) {
        this.bestReply = bestReply;
    }
}
