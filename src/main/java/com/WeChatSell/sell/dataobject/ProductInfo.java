package com.WeChatSell.sell.dataobject;

import com.WeChatSell.sell.enums.ProductStatusEnum;
import com.WeChatSell.sell.util.EnumUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@DynamicUpdate
public class ProductInfo implements Serializable{

    private static final long serialVersionUID = -9063842887165716494L;

    @Id
    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private Integer productStock;

    private String productDescription;

    // 小图
    private String productIcon;

    // 商品状态，0正常1下架
    private Integer productStatus = ProductStatusEnum.DOWN.getCode();

    private Integer categoryType;

    private Date createTime;

    private Date updateTime;

    @JsonIgnore
    public ProductStatusEnum getProductStatusEnum() {
        return EnumUtil.getByCode(productStatus, ProductStatusEnum.class);
    }

}
