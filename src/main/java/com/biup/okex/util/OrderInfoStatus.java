package com.biup.okex.util;

/**
 * order->status 订单状态取值
 * @author Autorun
 * Created by Autorun on 2017/09/14.
 */
public enum OrderInfoStatus {

    //    0 init，1 new，2 filled，3 part_filled，4 canceled，5 pending_cancel，6 expired
    INIT(0, "trade.order.status.init", "下单中"),
    NEW(1, "trade.order.status.new", "未成交"),
    PART_FILLED(3, "trade.order.status.part.filled", "部分成交"),
    FILLED(2, "trade.order.status.filled", "完全成交"),
    CANCELED(4, "trade.order.status.canceled", "已撤单"),
    PENDING_CANCEL(5, "trade.order.status.cancel", "待撤单"),
    EXPIRED(6, "trade.order.status.expired", "异常关闭"),
    EXCEPTION_CANCEL(-100, "trade.order.status.exception.cancel", "异常撤单");

    public int value;
    public String languageKey;
    public String description;

    OrderInfoStatus(int value, String languageKey, String description) {
        this.value = value;
        this.languageKey = languageKey;
        this.description = description;
    }

    public String getLanguageKey() {
        return languageKey;
    }

    public void setLanguageKey(String languageKey) {
        this.languageKey = languageKey;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static OrderInfoStatus fromValue(int value) {
        for (OrderInfoStatus t : OrderInfoStatus.values()) {
            if (t.value == value) {
                return t;
            }
        }
        return null;
    }

    public static OrderInfoStatus fromName(String name) {
        for (OrderInfoStatus t : OrderInfoStatus.values()) {
            if (t.name().equals(name)) {
                return t;
            }
        }
        return null;
    }
}
