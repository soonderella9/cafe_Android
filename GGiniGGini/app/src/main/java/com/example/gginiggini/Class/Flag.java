package com.example.gginiggini.Class;

/**
 * Created by 이용준 on 2016-11-29.
 */

public class Flag {
    public String cafeFlag="상록원";
    public String userUID;
    private String dateFlag;
    private String rankFlag;

    public String getCafeFlag() {
        return cafeFlag;
    }

    public void setCafeFlag(String cafeFlag) {
        this.cafeFlag = cafeFlag;
    }

    public String getDateFlag() {
        return dateFlag;
    }

    public void setDateFlag(String dateFlag) {
        this.dateFlag = dateFlag;
    }

    public String getRankFlag() {
        return rankFlag;
    }

    public void setRankFlag(String rankFlag) {
        this.rankFlag = rankFlag;
    }

    public String getUserUID() {
        return userUID;
    }

    public void setUserUID(String userUID) {
        this.userUID = userUID;
    }
}
