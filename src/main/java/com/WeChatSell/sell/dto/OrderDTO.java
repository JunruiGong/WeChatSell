package com.WeChatSell.sell.dto;

import com.WeChatSell.sell.dataobject.OrderDetail;
import com.WeChatSell.sell.enums.OrderStatusEnum;
import com.WeChatSell.sell.enums.PayStatusEnum;
import com.WeChatSell.sell.util.serializer.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDTO {

    private String orderId;

    private String buyerName;

    private String buyerPhone;

    private String buyerAddress;

    private String buyerOpenid;

    private BigDecimal orderAmount;

    // 订单状态，默认为0，新订单
    private Integer orderStatus;

    // 支付状态，默认为0，未支付
    private Integer payStatus;

    @JsonSerialize(using= Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using= Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

}
