package com.WeChatSell.sell.repository;

import com.WeChatSell.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String > {

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
