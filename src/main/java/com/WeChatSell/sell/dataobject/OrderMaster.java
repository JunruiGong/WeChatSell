package com.WeChatSell.sell.dataobject;

import com.WeChatSell.sell.enums.OrderStatusEnum;
import com.WeChatSell.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    @Id
    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    // 订单状态，默认为0，新订单
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();

    // 支付状态，默认为0，未支付
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    private Date createTime;

    private Date updateTime;

}
