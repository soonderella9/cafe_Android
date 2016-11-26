package com.example.gginiggini.Item;

import android.graphics.drawable.Drawable;

/**
 * Created by 이용준 on 2016-11-26.
 */

public class Item_Comment {
    private String uid;
    private String content;
    private String likeCount;
    private Drawable like;
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

    public String getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(String likeCount) {
        this.likeCount = likeCount;
    }

    public Drawable getLike() {
        return like;
    }

    public void setLike(Drawable like) {
        this.like = like;
    }
}
