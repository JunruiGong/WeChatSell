package com.WeChatSell.sell.service.impl;

import com.WeChatSell.sell.dataobject.SellerInfo;
import com.WeChatSell.sell.service.SellerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerServiceImplTest {

    private static final String OPENID = "abc";

    @Autowired
    private SellerServiceImpl sellService;

    @Test
    public void findSellerInfoByOpenid() {


        SellerInfo sellerInfo = sellService.findSellerInfoByOpenid(OPENID);
        assertEquals(OPENID,sellerInfo.getOpenid());

    }
}