package com.biup.okex.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;

public class Data implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 交易币对，如ltc_btc
     */
    private String symbol;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 委托价格（市价买单代表购买总金额； 限价单代表委托价格）
     */
    private BigDecimal tradeUnitPrice;
    /**
     * 委托数量（市价卖代表要卖总数量；限价单代表委托数量）
     */
    private BigDecimal tradeAmount;
    /**
     * 创建日期
     */
    private Date createdDate;
    /**
     * 平均成交价
     */
    private BigDecimal averagePrice;
    /**
     * 成交金额
     */
    private BigDecimal tradePrice;
    /**
     * 交易类型（buy:买入；sell:卖出；buy_market:按市价买入；sell_market:按市价卖出）
     */
    private String tradeType;
    /**
     * -1已撤销,0等待成交,1部分成交,2完全成交,4撤单处理中
     */
    private Integer status;
    /**
     * 当按市场价买币时表示剩余金额，其他情况表示此笔交易剩余买/卖币的数量
     */
    private BigDecimal unTrade;
    /**
     * 已完成成交量
     */
    private BigDecimal completedTradeAmount;

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getUnTrade() {
        return unTrade;
    }

    public void setUnTrade(BigDecimal unTrade) {
        this.unTrade = unTrade;
    }

    public BigDecimal getCompletedTradeAmount() {
        return completedTradeAmount;
    }

    public void setCompletedTradeAmount(BigDecimal completedTradeAmount) {
        this.completedTradeAmount = completedTradeAmount;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getTradeUnitPrice() {
        return tradeUnitPrice;
    }

    public void setTradeUnitPrice(BigDecimal tradeUnitPrice) {
        this.tradeUnitPrice = tradeUnitPrice;
    }

    public BigDecimal getTradeAmount() {
        return tradeAmount;
    }

    public void setTradeAmount(BigDecimal tradeAmount) {
        this.tradeAmount = tradeAmount;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(BigDecimal tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Data{" +
                "symbol='" + symbol + '\'' +
                ", orderId=" + orderId +
                ", tradeUnitPrice=" + tradeUnitPrice +
                ", tradeAmount=" + tradeAmount +
                ", createdDate=" + createdDate +
                ", averagePrice=" + averagePrice +
                ", tradePrice=" + tradePrice +
                ", tradeType='" + tradeType + '\'' +
                ", status=" + status +
                ", unTrade=" + unTrade +
                ", completedTradeAmount=" + completedTradeAmount +
                '}';
    }
}