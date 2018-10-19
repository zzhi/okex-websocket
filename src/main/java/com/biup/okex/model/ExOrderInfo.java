/*
package com.biup.okex.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

*/
/**
 * @author Autorun
 *//*

public class ExOrderInfo implements Serializable {
    */
/**
     *
     *//*

    private Long id;

    */
/**
     *
     *//*

    private Integer userId;

    */
/**
     * 平台id
     *//*

    private Integer plateId;

    */
/**
     * 第三方订单id
     *//*

    private String orderId;

    */
/**
     * 币对 BTCUSDT
     *//*

    private String symbol;

    */
/**
     * 基础币种
     *//*

    private String baseSymbol;

    */
/**
     * 计价币种
     *//*

    private String quoteSymbol;

    */
/**
     * 买卖方向
     *//*

    private String side;

    */
/**
     * 限价单挂单价格
     *//*

    private BigDecimal price;

    */
/**
     * 挂单总数量
     *//*

    private BigDecimal volume;

    */
/**
     * 手续费
     *//*

    private BigDecimal fee;

    */
/**
     * 支付手续费币种折扣，0为关闭该币种支付手续费
     *//*

    private Double feeCoinRate;

    */
/**
     * 成交数量
     *//*

    private BigDecimal dealVolume;

    */
/**
     * 已成交金额
     *//*

    private BigDecimal dealMoney;

    */
/**
     * 成交均价
     *//*

    private BigDecimal avgPrice;

    */
/**
     * 订单状态：0 init 下单中，1 new 新订单，2 filled 完全成交，3 part_filled 部分成交，4 canceled 已撤单，5 expired 异常订单
     *//*

    private Byte status;

    */
/**
     * 委托类型：1 limit，2 market，3 stop
     *//*

    private Byte type;

    */
/**
     *
     *//*

    private Date ctime;

    */
/**
     *
     *//*

    private Date mtime;

    */
/**
     * 订单来源：1web，2app，3api
     *//*

    private Byte source;

    */
/**
     * 币安扩展信息字段
     *//*

    private String binanceExtOrderinfo;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getPlateId() {
        return plateId;
    }

    public void setPlateId(Integer plateId) {
        this.plateId = plateId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public String getBaseSymbol() {
        return baseSymbol;
    }

    public void setBaseSymbol(String baseSymbol) {
        this.baseSymbol = baseSymbol == null ? null : baseSymbol.trim();
    }

    public String getQuoteSymbol() {
        return quoteSymbol;
    }

    public void setQuoteSymbol(String quoteSymbol) {
        this.quoteSymbol = quoteSymbol == null ? null : quoteSymbol.trim();
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side == null ? null : side.trim();
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

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public Double getFeeCoinRate() {
        return feeCoinRate;
    }

    public void setFeeCoinRate(Double feeCoinRate) {
        this.feeCoinRate = feeCoinRate;
    }

    public BigDecimal getDealVolume() {
        return dealVolume;
    }

    public void setDealVolume(BigDecimal dealVolume) {
        this.dealVolume = dealVolume;
    }

    public BigDecimal getDealMoney() {
        return dealMoney;
    }

    public void setDealMoney(BigDecimal dealMoney) {
        this.dealMoney = dealMoney;
    }

    public BigDecimal getAvgPrice() {
        return avgPrice;
    }

    public void setAvgPrice(BigDecimal avgPrice) {
        this.avgPrice = avgPrice;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Date getCtime() {
        return ctime;
    }

    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Date getMtime() {
        return mtime;
    }

    public void setMtime(Date mtime) {
        this.mtime = mtime;
    }

    public Byte getSource() {
        return source;
    }

    public void setSource(Byte source) {
        this.source = source;
    }

    public String getBinanceExtOrderinfo() {
        return binanceExtOrderinfo;
    }

    public void setBinanceExtOrderinfo(String binanceExtOrderinfo) {
        this.binanceExtOrderinfo = binanceExtOrderinfo == null ? null : binanceExtOrderinfo.trim();
    }

    public static class Builder {
        private ExOrderInfo obj;

        public Builder() {
            this.obj = new ExOrderInfo();
        }

        public Builder id(Long id) {
            obj.id = id;
            return this;
        }

        public Builder userId(Integer userId) {
            obj.userId = userId;
            return this;
        }

        public Builder plateId(Integer plateId) {
            obj.plateId = plateId;
            return this;
        }

        public Builder orderId(String orderId) {
            obj.orderId = orderId;
            return this;
        }

        public Builder symbol(String symbol) {
            obj.symbol = symbol;
            return this;
        }

        public Builder baseSymbol(String baseSymbol) {
            obj.baseSymbol = baseSymbol;
            return this;
        }

        public Builder quoteSymbol(String quoteSymbol) {
            obj.quoteSymbol = quoteSymbol;
            return this;
        }

        public Builder side(String side) {
            obj.side = side;
            return this;
        }

        public Builder price(BigDecimal price) {
            obj.price = price;
            return this;
        }

        public Builder volume(BigDecimal volume) {
            obj.volume = volume;
            return this;
        }

        public Builder fee(BigDecimal fee) {
            obj.fee = fee;
            return this;
        }

        public Builder feeCoinRate(Double feeCoinRate) {
            obj.feeCoinRate = feeCoinRate;
            return this;
        }

        public Builder dealVolume(BigDecimal dealVolume) {
            obj.dealVolume = dealVolume;
            return this;
        }

        public Builder dealMoney(BigDecimal dealMoney) {
            obj.dealMoney = dealMoney;
            return this;
        }

        public Builder avgPrice(BigDecimal avgPrice) {
            obj.avgPrice = avgPrice;
            return this;
        }

        public Builder status(Byte status) {
            obj.status = status;
            return this;
        }

        public Builder type(Byte type) {
            obj.type = type;
            return this;
        }

        public Builder ctime(Date ctime) {
            obj.ctime = ctime;
            return this;
        }

        public Builder mtime(Date mtime) {
            obj.mtime = mtime;
            return this;
        }

        public Builder source(Byte source) {
            obj.source = source;
            return this;
        }

        public Builder binanceExtOrderinfo(String binanceExtOrderinfo) {
            obj.binanceExtOrderinfo = binanceExtOrderinfo;
            return this;
        }

        public ExOrderInfo build() {
            return this.obj;
        }
    }

    @Override
    public String toString() {
        return "ExOrderInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", plateId=" + plateId +
                ", orderId=" + orderId +
                ", symbol='" + symbol + '\'' +
                ", baseSymbol='" + baseSymbol + '\'' +
                ", quoteSymbol='" + quoteSymbol + '\'' +
                ", side='" + side + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                ", fee=" + fee +
                ", feeCoinRate=" + feeCoinRate +
                ", dealVolume=" + dealVolume +
                ", dealMoney=" + dealMoney +
                ", avgPrice=" + avgPrice +
                ", status=" + status +
                ", type=" + type +
                ", ctime=" + ctime +
                ", mtime=" + mtime +
                ", source=" + source +
                ", binanceExtOrderinfo='" + binanceExtOrderinfo + '\'' +
                '}';
    }
}*/
