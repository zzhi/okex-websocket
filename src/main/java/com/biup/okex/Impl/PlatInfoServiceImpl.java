package com.biup.okex.Impl;


import com.biup.okex.core.page.QueryResult;
import com.biup.okex.core.utils.common.BaseUtils;
import com.biup.okex.dao.BiupDao;
import com.biup.okex.po.PlatInfo;
import com.biup.okex.service.OrderResService;
import com.biup.okex.service.PlatInfoService;
import com.biup.okex.util.MyEAESUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("platInfoService")
public class PlatInfoServiceImpl implements PlatInfoService {

    private static final Logger logger = LoggerFactory.getLogger(PlatInfoServiceImpl.class);
    @Autowired
    private BiupDao biupDao;

    @Override
    public PlatInfo get(PlatInfo platInfo) {

        PlatInfo info = biupDao.selectOne(platInfo);
        if (info != null) {
            String secretKey = deCodeSecretKey(info.getSecretKey());
            info.setSecretKey(secretKey);
            return info;
        }
        return null;
    }

    @Override
    public List<PlatInfo> list(PlatInfo platInfo) {
        return biupDao.selectList(platInfo);
    }

    @Override
    public Integer count(PlatInfo platInfo) {
        return biupDao.count(platInfo);
    }

    @Override
    public Integer insert(PlatInfo platInfo) {
        return biupDao.insert(platInfo);
    }

    @Override
    public Integer remove(PlatInfo platInfo) {
        return biupDao.update(platInfo);
    }

    @Override
    public Integer update(PlatInfo platInfo) {
        return biupDao.update(platInfo);
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
}