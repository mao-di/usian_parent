package com.usian.pojo;

import java.io.Serializable;

/**
 * ClassName:OrderInfo
 * Author:maodi
 * CreateTime:2021/03/29/13:43
 */
public class OrderInfo implements Serializable {
    private TbOrder tbOrder;
    private TbOrderShipping tbOrderShipping;
    private String orderItem;

    public TbOrder getTbOrder() {
        return tbOrder;
    }

    public void setTbOrder(TbOrder tbOrder) {
        this.tbOrder = tbOrder;
    }

    public TbOrderShipping getTbOrderShipping() {
        return tbOrderShipping;
    }

    public void setTbOrderShipping(TbOrderShipping tbOrderShipping) {
        this.tbOrderShipping = tbOrderShipping;
    }

    public String getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(String orderItem) {
        this.orderItem = orderItem;
    }
}
