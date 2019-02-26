package com.WeChatSell.sell.service;

import com.WeChatSell.sell.dataobject.ProductInfo;
import com.WeChatSell.sell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;

public interface ProductService {

    ProductInfo findOne(String productId);

    /***
     * 查询所有上架的商品
     * @return
     */
    List<ProductInfo> findUpAll();


    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDTO> cartDTOList);


    //减库存
    void decreaseStock(List<CartDTO> cartDTOList);


}
