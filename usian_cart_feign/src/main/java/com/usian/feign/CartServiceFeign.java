package com.usian.feign;

import com.usian.pojo.TbItem;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * ClassName:CartServiceFeign
 * Author:maodi
 * CreateTime:2021/03/29/08:53
 */

@FeignClient("usian-cart-service")
public interface CartServiceFeign {

    @RequestMapping("/service/cart/selectCartByUserId")
    Map<String, TbItem> selectCartByUserId(@RequestParam String userId);

    @RequestMapping("/service/cart/insertCart")
    Boolean insertCart(@RequestParam String userId,Map<String, TbItem> cart);
}
