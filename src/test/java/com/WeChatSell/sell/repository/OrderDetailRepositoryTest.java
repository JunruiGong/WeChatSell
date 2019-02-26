package com.WeChatSell.sell.repository;

import com.WeChatSell.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();

        orderDetail.setDetailId("123234");
        orderDetail.setOrderId("aadg");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("11111");
        orderDetail.setProductName("sdvsd");
        orderDetail.setProductPrice(BigDecimal.valueOf(345.6));
        orderDetail.setProductQuantity(2);

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> result = repository.findByOrderId("aadg");
        Assert.assertNotEquals(0,result.size());
    }
}