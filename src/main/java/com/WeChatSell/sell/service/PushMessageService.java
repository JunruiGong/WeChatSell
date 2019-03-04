package com.WeChatSell.sell.service;

import com.WeChatSell.sell.dto.OrderDTO;

/**
 * 消息推送
 */
public interface PushMessageService {

    /**
     * 订单状态变更消息
     * @param orderDTO
     */
    void orderStatus(OrderDTO orderDTO);

}
