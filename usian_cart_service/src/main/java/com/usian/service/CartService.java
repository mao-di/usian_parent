package com.usian.service;

import com.usian.pojo.TbItem;

import java.util.Map;

/**
 * ClassName:CartService
 * Author:maodi
 * CreateTime:2021/03/29/08:50
 */
public interface CartService {

    Map<String, TbItem> selectCartByUserId(String userId);

    Boolean insertCart(String userId, Map<String, TbItem> cart);
}
