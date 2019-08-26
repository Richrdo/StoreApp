package com.example.shopstore.data;

import android.media.Image;
import android.widget.ImageView;

import java.io.Serializable;
import java.util.Objects;

public class Commodity implements Serializable {
    private String imagePath;
    private String commodityName;
    private String describe;
    private double price;
    private String type;
    private String groundDate;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Commodity() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGroundDate(String groundDate) {
        this.groundDate = groundDate;
    }

    public String getGroundDate() {
        return groundDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Commodity commodity = (Commodity) o;
        return id == commodity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}