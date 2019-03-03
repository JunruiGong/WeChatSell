package com.WeChatSell.sell.service;

import com.WeChatSell.sell.dataobject.SellerInfo;

public interface SellService {

    /***
     * 通过openid车讯卖家端信息
     * @param openid
     * @return
     */
    SellerInfo findSellerInfoByOpenid(String openid);

}
