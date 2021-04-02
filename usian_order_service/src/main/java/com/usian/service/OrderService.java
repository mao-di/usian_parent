package com.usian.service;

import com.usian.pojo.OrderInfo;
import com.usian.pojo.TbOrder;

import java.util.List;

/**
 * ClassName:OrderService
 * Author:maodi
 * CreateTime:2021/03/29/13:44
 */
public interface OrderService {

    Long insertOrder(OrderInfo orderInfo);

    List<TbOrder> selectOvertimeOrder();

    void updateOverTimeTbOrder(TbOrder tbOrder);

    void updateTbItemByOrderId(String orderId);
}
