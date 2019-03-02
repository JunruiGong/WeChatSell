package com.WeChatSell.sell.service.impl;

import com.WeChatSell.sell.dataobject.ProductInfo;
import com.WeChatSell.sell.enums.ProductStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    ProductServiceImpl  productService;

    @Test
    public void findOne() {
        ProductInfo result = productService.findOne("123456");
        Assert.assertEquals("123456",result.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> result = productService.findUpAll();
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void findAll() {
        PageRequest pageRequest=  new PageRequest(0,2);
        Page<ProductInfo> productInfoPage = productService.findAll(pageRequest);
        Assert.assertNotEquals(0,productInfoPage.getTotalElements());
    }

    @Test
    public void save() {

        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123");
        productInfo.setProductName("皮皮虾");
        productInfo.setProductPrice(new BigDecimal(3.2));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("很好喝的虾");
        productInfo.setProductIcon("http://xxxx.jpg");
        productInfo.setProductStatus(ProductStatusEnum.DOWN.getCode());
        productInfo.setCategoryType(2);
        ProductInfo result = productService.save(productInfo);
        Assert.assertNotNull(result);
    }

    @Test
    public void onSale(){
        ProductInfo productInfo = productService.onSale("11111");
        Assert.assertEquals(ProductStatusEnum.UP,productInfo.getProductStatusEnum());

    }

    @Test
    public void offSale(){
        ProductInfo productInfo = productService.offSale("11111");
        Assert.assertEquals(ProductStatusEnum.DOWN,productInfo.getProductStatusEnum());

    }
}