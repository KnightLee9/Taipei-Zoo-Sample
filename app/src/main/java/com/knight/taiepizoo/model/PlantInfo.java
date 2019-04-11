package com.knight.taiepizoo.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlantInfo implements Serializable {
    @SerializedName("F_Pic01_URL")
    String imgUrl;
    @SerializedName("F_Name_Ch")
    String name;
    @SerializedName("F_Name_En")
    String nameEn;
    @SerializedName("F_AlsoKnown")
    String alsoKnown;
    @SerializedName("F_Brief")
    String brief;
    @SerializedName("F_Feature")
    String feature;
    @SerializedName("F_Function&Application")
    String function;
    @SerializedName("F_Update")
    String lastUpdate;

    public String getImgUrl() {
        return imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getNameEn() {
        return nameEn;
    }

    public String getAlsoKnown() {
        return alsoKnown;
    }

    public String getBrief() {
        return brief;
    }

    public String getFeature() {
        return feature;
    }

    public String getFunction() {
        return function;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }
}
