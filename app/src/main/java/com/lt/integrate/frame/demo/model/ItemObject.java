package com.lt.integrate.frame.demo.model;

import java.io.Serializable;

/**
 * Created by iclick on 2017/9/26.
 */

public class ItemObject implements Serializable {

    private static final long serialVersionUID = 1L;

    private String title ="";
    private String imgUrl ="";
    private String webUrl ="";

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    @Override
    public String toString() {
        return "ItemObject{" +
                "title='" + title + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", webUrl='" + webUrl + '\'' +
                '}';
    }
}
