package com.example.gginiggini.Item;

/**
 * Created by 이용준 on 2016-11-24.
 */

public class Item_Notice {
    private int nNum;
    private String title;
    private String content;
    private String Date;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public int getnNum() {
        return nNum;
    }

    public void setnNum(int nNum) {
        this.nNum = nNum;
    }
}
