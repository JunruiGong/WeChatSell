package com.WeChatSell.sell.repository;

import com.WeChatSell.sell.dataobject.SellerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellInfoRepository extends JpaRepository<SellerInfo,String> {

    SellerInfo findByOpenid(String openid);


}
