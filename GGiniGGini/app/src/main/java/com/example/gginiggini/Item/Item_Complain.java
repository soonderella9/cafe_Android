package com.example.gginiggini.Item;

/**
 * Created by 이용준 on 2016-11-24.
 */

public class Item_Complain {
    private int complainNum;
    private String title;
    private String date;
    private String content;
    private String uid;
    private String reply;
    private String isReply;

    /* getter, setter, toString methods */
    public int getComplainNum() {
        return complainNum;
    }

    public void setComplainNum(int complainNum) {
        this.complainNum = complainNum;
    }

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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getReply() {
        return reply;
    }

    public void setRelpy(String reply) {
        this.reply = reply;
    }

    public String getIsReply() {
        return isReply;
    }

    public void setIsReply(String isReply) {
        this.isReply = isReply;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    @Override
    public String toString() {
        return "ComplainVO [complainNum=" + complainNum + ", title=" + title + ", content=" + content + ", uid=" + uid
                + ", reply=" + reply + "]";
    }

}