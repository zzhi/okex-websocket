package com.biup.okex.Impl;


import com.biup.okex.dao.BiupDao;
import com.biup.okex.po.PlatInfo;
import com.biup.okex.po.PlatSymbol;
import com.biup.okex.service.PlatInfoService;
import com.biup.okex.service.PlatSymbolService;
import com.biup.okex.util.MyEAESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PlatSymbolService")
public class PlatSymbolServiceImpl implements PlatSymbolService {

    private static final Logger logger = LoggerFactory.getLogger(PlatSymbolServiceImpl.class);
    @Autowired
    private BiupDao biupDao;

    @Override
    public List<PlatSymbol> list(PlatSymbol platSymbol) {
        return biupDao.selectList(platSymbol);
    }


}