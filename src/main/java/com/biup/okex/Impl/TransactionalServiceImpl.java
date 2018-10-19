package com.biup.okex.Impl;

import com.biup.okex.po.OrderRes;
import com.biup.okex.service.OrderResService;
import com.biup.okex.service.TransactionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("transactionalService")
public class TransactionalServiceImpl implements TransactionalService {


    private static final Logger logger = LoggerFactory.getLogger(TransactionalServiceImpl.class);
    @Autowired
    private OrderResService orderResService;

    @Override
    //@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void testTransactional(OrderRes order) {

        try {
            for (int i = 0; i < 10; i++) {
                orderResService.testInsert(order, String.valueOf(i));
            }
            orderResService.testInsert(order, String.valueOf("wewew"));
            for (int i = 10; i < 20; i++) {
                orderResService.testInsert(order, String.valueOf(i));
            }
        } catch (Exception e) {
            //logger.error("异常" + e);
            //throw new RuntimeException(e.getMessage());
        }
    }
}
