package com.WeChatSell.sell.VO;

import com.WeChatSell.sell.dataobject.ProductInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/***
 * 商品（包含类目）
 */
@Data
public class ProductVO implements Serializable {

    private static final long serialVersionUID = -2021478652282056672L;

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO>  productInfoVOList;


}
