package com.WeChatSell.sell.service;

import com.WeChatSell.sell.dataobject.OrderDetail;
import com.WeChatSell.sell.dto.OrderDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayService{

    void create(OrderDTO orderDTO);
}
