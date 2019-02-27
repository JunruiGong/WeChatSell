package com.WeChatSell.sell.service.impl;

import com.WeChatSell.sell.dataobject.OrderDetail;
import com.WeChatSell.sell.dto.OrderDTO;
import com.WeChatSell.sell.enums.OrderStatusEnum;
import com.WeChatSell.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;

    private final static String BUYER_OPENID = "110";

    private final static String ORDER_ID = "aadg";

    @Test
    public void createOrder() {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("浙江");
        orderDTO.setBuyerName("龚俊瑞");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerPhone("123123123");
        orderDTO.setOrderAmount(BigDecimal.valueOf(123.6));
        orderDTO.setOrderStatus(0);

        // 购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("11111");
        o1.setProductQuantity(2);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("123456");
        o2.setProductQuantity(12);
        orderDetailList.add(o2);

        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.createOrder(orderDTO);
        log.info("「创建订单」 result={}", result);

        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findOne() {

        OrderDTO result = orderService.findOne(ORDER_ID);
        log.info("「查询单个订单」 result={}", result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList() {

        PageRequest pageRequest=  new PageRequest(0,2);
        Page<OrderDTO> orderDTOPage = orderService.findList(BUYER_OPENID, pageRequest);
        Assert.assertNotEquals(0,orderDTOPage.getTotalElements());
    }

    @Test
    public void cancel() {

        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnum.FINISHED.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderDTO orderDTO = orderService.findOne(ORDER_ID);
        OrderDTO result = orderService.paid(orderDTO);
        Assert.assertEquals(PayStatusEnum.SUCCESS.getCode(),result.getPayStatus());
    }
}