package com.biup.okex.service;


import com.biup.okex.core.page.QueryResult;
import com.biup.okex.po.OrderRes;

import java.util.List;

public interface OrderResService {

    OrderRes get(OrderRes order);

    List<OrderRes> list(OrderRes order);

    Integer count(OrderRes order);

    Integer insert(OrderRes order);

    Integer remove(OrderRes order);

    Integer update(OrderRes order);

    QueryResult<OrderRes> getOrderRes(OrderRes input, int pageStart, int rows);

    Integer testInsert(OrderRes order,String iString);

    OrderRes testGet(OrderRes order);

    void testTransactional(OrderRes order) ;
    void testTransactional0(OrderRes order);

}
