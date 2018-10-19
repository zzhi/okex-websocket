package com.biup.okex.service;

import com.biup.okex.po.OrderRes;

public interface TransactionalService {

    void testTransactional(OrderRes order) ;
}
