package com.biup.okex.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class OrderRes implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 平台id
     */
    private Integer plateId;
    /**
     * 第三方订单id
     */
    private String orderId;
    /**
     * 交易对
     */
    private String symbol;

    /**
     * 订单方向
     */
    private String side;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 订单数量
     */
    private BigDecimal volume;

    /**
     * 已成交数量
     */
    private BigDecimal dealVolume;

    /**
     * 已成交金额
     */
    private BigDecimal dealMoney;

    /**
     * 平均成交价
     */
    private BigDecimal avgPrice;

    /**
     * 已成交手续费
     */
    private BigDecimal fee;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 针对币安dealVolume,平均价avgPrice,lastTradeId
     */
    private String extraInfo;
    /**
     * 订单创建时间
     */
    private Date orderTime;
    /**
     * 处理状态
     */
    private Integer flag;

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    /**
     * 订单修改时间
     */
    private Date mtime;


    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getExtraInfo() {
        return extraInfo;
    }

    public void setExtraInfo(String extraInfo) {
        this.extraInfo = extraInfo;
    }

    private String state;

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getDealVolume() {
        return dealVolume;
    }

    public void setDealVolume(BigDecimal dealVolume) {
        this.dealVolume = dealVolume;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(BigDecimal dealMoney) {
        this.dealMoney = dealMoney;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Integer getPlateId() {
        return plateId;
    }

    public void setPlateId(Integer plateId) {
        this.plateId = plateId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}