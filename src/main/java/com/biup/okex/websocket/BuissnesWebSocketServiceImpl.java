package com.biup.okex.websocket;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.biup.okex.Impl.OkexSender;
import com.biup.okex.model.Data;
import com.biup.okex.model.OrderInfo;
import com.biup.okex.po.OrderRes;
import com.biup.okex.util.MqConstant;
import com.biup.okex.util.OrderInfoStatus;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * 订阅信息处理类需要实现WebSocketService接口
 *
 * @author okcoin
 */
@Service("webSocketService")
public class BuissnesWebSocketServiceImpl implements WebSocketService {

    @Autowired
    private OkexSender okexSender;
    private static final Integer plateId = 2;//OKEX 平台ID
    private static final Logger log = LoggerFactory.getLogger(WebSocketBase.class);

    @Override
    public void onReceive(String msg) {

        //okex 返回的订单信息，需要哪些要自己解析
        if (!Strings.isNullOrEmpty(msg)) {
            //有些返回信息带[ 和]
            msg = msg.replace("[", "").replace("]", "");
            try {
                OrderInfo orderInfo = JSON.parseObject(msg, OrderInfo.class);
                if (orderInfo != null && orderInfo.getData() != null && orderInfo.getData().getOrderId() != null) {
                    Data data = orderInfo.getData();

                    OrderRes orderRes = new OrderRes();
                    orderRes.setPlateId(plateId);//OKEX 平台ID
                    orderRes.setOrderId(data.getOrderId());//第三方订单id
                    String symbol = data.getSymbol().toUpperCase();//币对 BTCUSDT
                    orderRes.setSymbol(symbol);
                    String side = data.getTradeType().toUpperCase();//（buy:买入；sell:卖出；buy_market:按市价买入；sell_market:按市价卖出）
                    orderRes.setSide(side);//enum('BUY','SELL')
                    orderRes.setPrice(data.getTradeUnitPrice());//限价单挂单价格
                    orderRes.setVolume(data.getTradeAmount());//挂单总数量
                    orderRes.setDealVolume(data.getCompletedTradeAmount());//成交数量
                    orderRes.setDealMoney(data.getTradePrice());//已成交金额
                    orderRes.setAvgPrice(data.getAveragePrice());//平均价
                    int state = formOrderStatus(data.getStatus());//-1已撤销,0等待成交,1部分成交,2完全成交,4撤单处理中
                    //订单状态：0 init 下单中，1 new 新订单，2 filled 完全成交，
                    orderRes.setStatus(state);
                    orderRes.setOrderTime(data.getCreatedDate());//时间
                    log.info("订单序列化-正常订单信息:{}", msg);
                    if (orderRes.getStatus()== 4) {
                        okexSender.send(MqConstant.QUEUE_OKEX_CANCEL,orderRes);
                    } else if (orderRes.getStatus() == 2 ||
                            orderRes.getStatus() == 3) {
                        okexSender.send(MqConstant.QUEUE_OKEX_CREATE,orderRes);
                    } else {
                        //
                    }
                } else {
                    log.info("订单序列化-非订单信息:{}", msg);
                }

            } catch (Exception e) {
                log.error("订单序列化-异常:{}", msg);
                log.error("订单序列化-异常:", e);
            }
        } else {
            //
        }
    }


    private int formOrderStatus(Integer status) {
        if (status.equals(-1)) {//已撤销
            status = OrderInfoStatus.CANCELED.value;
        } else if (status.equals(0)) {//未成交
            status = OrderInfoStatus.NEW.value;
        } else if (status.equals(1)) {//部分成交
            status = OrderInfoStatus.PART_FILLED.value;
        } else if (status.equals(2)) {//完全成交
            status = OrderInfoStatus.FILLED.value;
        } else if (status.equals(3)) { // 撤单处理中
            status = OrderInfoStatus.PENDING_CANCEL.value;
        } else {
            status = OrderInfoStatus.EXCEPTION_CANCEL.value;
        }
        return status;
    }
}
