package com.knight.taiepizoo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AreaInfo implements Serializable {
    @SerializedName("E_Name")
    private String title;
    @SerializedName("E_Info")
    private String info;
    @SerializedName("E_Memo")
    private String memo;
    @SerializedName("E_Pic_URL")
    private String imgUrl;
    @SerializedName("E_Category")
    private String category;

    @SerializedName("E_URL")
    private String url;


    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public String getMemo() {
        return memo;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getCategory() {
        return category;
    }

    public String getUrl() {
        return url;
    }
}
