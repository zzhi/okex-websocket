package com.biup.okex.service;

import com.biup.okex.po.PlatInfo;

import java.util.List;

public interface PlatInfoService {

    PlatInfo get(PlatInfo platInfo);

    List<PlatInfo> list(PlatInfo platInfo);

    Integer count(PlatInfo platInfo);

    Integer insert(PlatInfo platInfo);

    Integer remove(PlatInfo platInfo);

    Integer update(PlatInfo platInfo);
}
