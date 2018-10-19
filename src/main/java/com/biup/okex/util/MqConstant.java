package com.biup.okex.util;

/**
 * RabbitMQ常量类
 */
public  class MqConstant {

    /**
     * 测试队列
     */
    public static final String MQ_TEST_KEY = "huobi";

    /**
     * 默认的交换机
     */
    public static final String EXCHANGE_MQ = "exchange.biup";

    /**
     * 火币key-挂单
     */
    public static final String KEY_HUOBI_CREATE = "key.huobi.create";

    /**
     * 币安key--挂单
     */
    public static final String KEY_BINANCE_CREATE = "key.binance.create";

    /**
     * OKEX key--挂单
     */
    public static final String KEY_OKEX_CREATE = "key.okex.create";

    /**
     * 火币key-撤单
     */
    public static final String KEY_HUOBI_CANCEL = "key.huobi.cancel";

    /**
     * 币安key--撤单
     */
    public static final String KEY_BINANCE_CANCEL = "key.binance.cancel";

    /**
     * OKEX key--撤单
     */
    public static final String KEY_OKEX_CANCEL = "key.okex.cancel";

    /**
     * 火币queue--挂单
     */
    public static final String QUEUE_HUOBI_CREATE = "queue.huobi.create";

    /**
     * 币安queue--挂单
     */
    public static final String QUEUE_BINANCE_CREATE = "queue.binance.create";

    /**
     * OKEX queue--挂单
     */
    public static final String QUEUE_OKEX_CREATE = "queue.okex.create";

    /**
     * 火币queue--撤单
     */
    public static final String QUEUE_HUOBI_CANCEL = "queue.huobi.cancel";

    /**
     * 币安queue--撤单
     */
    public static final String QUEUE_BINANCE_CANCEL = "queue.binance.cancel";

    /**
     * OKEX queue--撤单
     */
    public static final String QUEUE_OKEX_CANCEL = "queue.okex.cancel";


}