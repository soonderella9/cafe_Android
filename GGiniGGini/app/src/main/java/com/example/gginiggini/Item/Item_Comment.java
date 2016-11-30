package com.example.gginiggini.Item;

import android.graphics.drawable.Drawable;

/**
 * Created by 이용준 on 2016-11-26.
 */

public class Item_Comment {
    private String uid;
    private String content;
    private int commentNum;
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(int commentNum) {
        this.commentNum = commentNum;
    }
}
