package com.biup.okex;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.biup.okex.Impl.OkexSender;
import com.biup.okex.model.Data;
import com.biup.okex.model.OrderInfo;
import com.biup.okex.po.OrderRes;
import com.biup.okex.po.PlatInfo;
import com.biup.okex.po.PlatSymbol;
import com.biup.okex.service.OrderResService;
import com.biup.okex.service.PlatInfoService;
import com.biup.okex.service.PlatSymbolService;
import com.biup.okex.service.TransactionalService;
import com.biup.okex.util.MyEAESUtil;
import com.biup.okex.util.OrderInfoStatus;
import com.biup.okex.util.PlatConstant;
import com.google.common.base.Strings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebsocketOkexApplicationTests {
    private static final Logger logger = LoggerFactory.getLogger(WebsocketOkexApplication.class);

    @Autowired
    private OkexSender webSocketService;

    @Autowired
    private OrderResService orderResService;
    @Autowired
    private TransactionalService transactionalService;

   /* @Autowired
    private StringRedisTemplate stringRedisTemplate;*/

    @Autowired
    private PlatInfoService platInfoService;
    @Autowired
    private PlatSymbolService platSymbolService;

    @Test
    public void redisTest() {

        //Object obj = stringRedisTemplate.opsForHash().get(PlatConstant.SYSTEM_PLAT_INFO_ID_KEY, "1");
        logger.info("");
    }


    @Test
    public void platInfoTest() {

//        PlatSymbol platSymbol = new PlatSymbol();
//        platSymbol.setPlatId(2);
//        List<PlatSymbol> lists = platSymbolService.list(platSymbol);
//

        PlatInfo platInfo = new PlatInfo();
        platInfo.setPlatId(8);
        platInfo = platInfoService.get(platInfo);

        logger.info("信息:{}", JSONObject.toJSONString(platInfo));
    }

    private String deCodeSecretKey(final String secretKey) {
        try {
            byte[] jiemiCode = MyEAESUtil.jiemi(secretKey);
            if (jiemiCode != null && jiemiCode.length > 0) {
                return new String(jiemiCode);
            }
        } catch (Exception e) {
            logger.error("decode error, secretKey : {}", secretKey, e);
        }
        return null;
    }

    @Test
    //@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void orderResTest() throws Exception {


        OrderRes orderRes = new OrderRes();
        orderRes.setPlateId(2);
        orderRes.setOrderId("");
        orderRes.setSymbol("bnb_usdt");
        orderRes.setSide("BUY");
        orderRes.setPrice(new BigDecimal(1));
        orderRes.setVolume(new BigDecimal(1));
        orderRes.setDealVolume(new BigDecimal(1));
        orderRes.setDealMoney(new BigDecimal(1));
        orderRes.setAvgPrice(new BigDecimal(1));
        orderRes.setFlag(0);
        orderRes.setStatus(2);
        orderRes.setOrderTime(new Date());
        orderRes.setMtime(new Date());
        //try {
        transactionalService.testTransactional(orderRes);
        //orderResService.testTransactional(orderRes);
//        } catch (Exception e) {
//            logger.error("异常" + e);
//        }

        logger.info("结果:{}", orderRes);
    }

    @Test
    public void okexSenderTest() {

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
