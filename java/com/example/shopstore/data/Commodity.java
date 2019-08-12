package com.example.shopstore.data;

import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;

public class Commodity implements Serializable {
    private int imageId;
    private String commodityName;
    private String describe;

    public Commodity(String commodityName, int imageId, String describe) {
        this.imageId = imageId;
        this.commodityName = commodityName;
        this.describe = describe;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getCommodityName() {
        return commodityName;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
