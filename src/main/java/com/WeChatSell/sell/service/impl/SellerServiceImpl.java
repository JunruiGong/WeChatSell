package com.WeChatSell.sell.service.impl;

import com.WeChatSell.sell.dataobject.SellerInfo;
import com.WeChatSell.sell.repository.SellInfoRepository;
import com.WeChatSell.sell.service.SellerService;
import com.WeChatSell.sell.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellInfoRepository repository;

    @Override
    public SellerInfo findSellerInfoByOpenid(String openid) {
        return repository.findByOpenid(openid);

    }
}
