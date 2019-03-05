package com.WeChatSell.sell.dataobject.mapper;

import com.WeChatSell.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
//@SpringBootTest
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;

    @Test
    public void insertByMap() {

        Map<String, Object> map = new HashMap<>();
        map.put("category_name","情趣用品");
        map.put("category_type",111111);
        int result = mapper.insertByMap(map);
        Assert.assertEquals(1,result);
    }

    @Test
    public void insertByObject() {

        ProductCategory productCategory = new ProductCategory();

        productCategory.setCategoryName("保健品");
        productCategory.setCategoryType(9574);
        int result = mapper.insertByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void findByCategoryType() {
        ProductCategory result = mapper.findByCategoryType(9574);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryName() {
        List<ProductCategory> productCategoryList = mapper.findByCategoryName("保健品");
        Assert.assertNotEquals(0,productCategoryList.size());
    }

    @Test
    public void updateByCategoryType() {

        int result = mapper.updateByCategoryType("保健品aaa", 9574);
        Assert.assertEquals(1,result);


    }

    @Test
    public void updateByObject() {
        ProductCategory productCategory = new ProductCategory();

        productCategory.setCategoryName("保健品");
        productCategory.setCategoryType(9574);
        int result = mapper.updateByObject(productCategory);
        Assert.assertEquals(1,result);
    }

    @Test
    public void deleteByCategoryType() {
        int result = mapper.deleteByCategoryType(9574);
        Assert.assertEquals(1,result);
    }

    @Test
    public void selectByCategoryType() {
        ProductCategory result = mapper.findByCategoryType(9574);
        Assert.assertNotNull(result);

    }
}