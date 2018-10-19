package com.biup.okex.model;

import com.biup.okex.model.Data;

import java.io.Serializable;

public class OrderInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    public Integer getBinary() {
        return binary;
    }

    public void setBinary(Integer binary) {
        this.binary = binary;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    private Integer binary;
    private String channel;
    private Data data;

    @Override
    public String toString() {
        return "OrderInfo{" +
                "binary=" + binary +
                ", channel='" + channel + '\'' +
                ", data=" + data +
                '}';
    }
}
