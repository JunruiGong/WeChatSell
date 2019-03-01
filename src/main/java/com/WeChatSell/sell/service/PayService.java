package com.WeChatSell.sell.service;

import com.WeChatSell.sell.dataobject.OrderDetail;
import com.WeChatSell.sell.dto.OrderDTO;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.model.RefundResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayService{

    PayResponse create(OrderDTO orderDTO);

    PayResponse notify(String notifyData);

    RefundResponse refund(OrderDTO orderDTO);
}
