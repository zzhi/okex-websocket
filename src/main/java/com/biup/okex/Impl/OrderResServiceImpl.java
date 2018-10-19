package com.biup.okex.Impl;


import com.biup.okex.core.page.QueryResult;
import com.biup.okex.core.utils.common.BaseUtils;
import com.biup.okex.dao.BiupDao;
import com.biup.okex.po.OrderRes;
import com.biup.okex.service.OrderResService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Calendar;
import java.util.List;

@Service("orderResService")
public class OrderResServiceImpl implements OrderResService {

    private static final Logger logger = LoggerFactory.getLogger(OrderResServiceImpl.class);
    @Autowired
    private BiupDao biupDao;

    @Override
    public OrderRes get(OrderRes order) {
        return biupDao.selectOne(order);
    }

    @Override
    public List<OrderRes> list(OrderRes order) {
        return biupDao.selectList(order);
    }

    @Override
    public Integer count(OrderRes order) {
        return biupDao.count(order);
    }

    @Override
    public Integer insert(OrderRes order) {
        biupDao.insert(order);
        return order.getId();
    }

    @Override
    public Integer remove(OrderRes order) {
        return biupDao.update(order);
    }

    @Override
    public Integer update(OrderRes order) {
        return biupDao.update(order);
    }


    @Override
    public QueryResult<OrderRes> getOrderRes(OrderRes input, int pageStart, int rows) {
        try {
            QueryResult<OrderRes> result = biupDao.selectListAndCount(BaseUtils.makeClazzPath(OrderRes.class,
                    "getOrderRes"), input, pageStart, rows, null);

            return result;
        } catch (Exception e) {
            logger.error("查询异常:{}", e.getMessage());
            return null;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public Integer testInsert(OrderRes order, String iString) {
        try {
            this.insert(order);
            Integer i = Integer.valueOf(iString);
            return order.getId();
        } catch (Exception e) {
            logger.error("异常" + e);
            //TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            throw new RuntimeException(e.getMessage());
        }
        //return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public OrderRes testGet(OrderRes order) {

        Integer i = Integer.valueOf("dfdf");
        return this.get(order);

    }

    @Override
    //@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void testTransactional(OrderRes order) {

        // try {
//        for (int i = 0; i < 10; i++) {
//            testInsert(order, String.valueOf(i));
//        }
        testInsert(order, String.valueOf("wewew"));
//        } catch (Exception e) {
//            //logger.error("异常" + e);
//            throw new RuntimeException(e.getMessage());
//        }
    }

    @Override
    //@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void testTransactional0(OrderRes order) {

        //this.testTransactional(order);

    }
}