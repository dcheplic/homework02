package com.homework.cheplic.homework02;

import java.io.Serializable;
import java.util.UUID;

/**
 * Created by Devin on 2/6/2018.
 */

public class FavoritePage implements Serializable{
    private String name;
    private String url;
    private int viewCount = 0;
    private UUID mUUID;

    public FavoritePage(String name, String url, int viewCount){
        this.name = name;
        this.url = url;
        this.viewCount = viewCount;
        this.mUUID = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void incrementViewCount() {
        viewCount++;
    }

    public UUID getUUID() {
        return mUUID;
    }
}
