package com.biup.okex.service;

import com.biup.okex.po.PlatInfo;
import com.biup.okex.po.PlatSymbol;

import java.util.List;

public interface PlatSymbolService {

    List<PlatSymbol> list(PlatSymbol platSymbol);
}
