package com.example.shopstore.data;

import com.example.shopstore.Control.UserInfo;

import java.io.Serializable;
import java.util.List;

public class OrderBean implements Serializable {
    private long user_id;
    private List<Commodity> commodities;

    @Override
    public String toString() {
        return "OrderBean{" +
                "user_id=" + user_id +
                ", commodities=" + commodities +
                '}';
    }

    public OrderBean(long user_id, List<Commodity> commodities) {
        this.user_id = user_id;
        this.commodities = commodities;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public List<Commodity> getCommodities() {
        return commodities;
    }

    public void setCommodities(List<Commodity> commodities) {
        this.commodities = commodities;
    }
}
