package com.biup.okex.po;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PlatSymbol implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer syId;

    private Integer platId;

    private String symbol;

    private String baseCurrency;

    private String quoteCurrency;

    private Integer pricePrecision;

    private Integer amountPrecision;

    private BigDecimal minAmount;

    private Double feeCoinRate;

    private BigDecimal limitPriceMin;

    private BigDecimal marketBuyMin;

    private BigDecimal marketSellMin;

    private Byte deleteFlag;

    private Byte releaseStatus;

    private Byte isRelease;

    private Date ctime;

    private Date mtime;

    public Integer getSyId() {
        return syId;
    }

    public void setSyId(Integer syId) {
        this.syId = syId;
    }

    public Integer getPlatId() {
        return platId;
    }

    public void setPlatId(Integer platId) {
        this.platId = platId;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol == null ? null : symbol.trim();
    }

    public String getBaseCurrency() {
        return baseCurrency;
    }

    public void setBaseCurrency(String baseCurrency) {
        this.baseCurrency = baseCurrency == null ? null : baseCurrency.trim();
    }

    public String getQuoteCurrency() {
        return quoteCurrency;
    }

    public void setQuoteCurrency(String quoteCurrency) {
        this.quoteCurrency = quoteCurrency == null ? null : quoteCurrency.trim();
    }

    public Integer getPricePrecision() {
        return pricePrecision;
    }

    public void setPricePrecision(Integer pricePrecision) {
        this.pricePrecision = pricePrecision;
    }

    public Integer getAmountPrecision() {
        return amountPrecision;
    }

    public void setAmountPrecision(Integer amountPrecision) {
        this.amountPrecision = amountPrecision;
    }

    public BigDecimal getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(BigDecimal minAmount) {
        this.minAmount = minAmount;
    }

    public Double getFeeCoinRate() {
        return feeCoinRate;
    }

    public void setFeeCoinRate(Double feeCoinRate) {
        this.feeCoinRate = feeCoinRate;
    }

    public BigDecimal getLimitPriceMin() {
        return limitPriceMin;
    }

    public void setLimitPriceMin(BigDecimal limitPriceMin) {
        this.limitPriceMin = limitPriceMin;
    }

    public BigDecimal getMarketBuyMin() {
        return marketBuyMin;
    }

    public void setMarketBuyMin(BigDecimal marketBuyMin) {
        this.marketBuyMin = marketBuyMin;
    }

    public BigDecimal getMarketSellMin() {
        return marketSellMin;
    }

    public void setMarketSellMin(BigDecimal marketSellMin) {
        this.marketSellMin = marketSellMin;
    }

    public Byte getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Byte deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Byte getReleaseStatus() {
        return releaseStatus;
    }

    public void setReleaseStatus(Byte releaseStatus) {
        this.releaseStatus = releaseStatus;
    }

    public Byte getIsRelease() {
        return isRelease;
    }

    public void setIsRelease(Byte isRelease) {
        this.isRelease = isRelease;
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
}