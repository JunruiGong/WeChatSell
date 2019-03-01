package com.WeChatSell.sell.controller;

import com.WeChatSell.sell.dto.OrderDTO;
import com.WeChatSell.sell.enums.ResultEnum;
import com.WeChatSell.sell.exception.SellException;
import com.WeChatSell.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pay")
@Slf4j
public class PayController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/create")
    public void create(@RequestParam("orderId")String orderId, @RequestParam("returnUrl") String returnUrl){
        // 1. 查询订单
        OrderDTO orderDTO  =orderService.findOne(orderId);
        if(orderDTO==null){
            log.error("【支付订单】无法查询到该订单");
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        // 发起支付



    }
}
