package com.usian.com.usian.controller;

import com.usian.feign.ItemServiceFeign;
import com.usian.utils.CatResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:ItemCategoryController
 * Author:maodi
 * CreateTime:2021/03/16/11:33
 */
@RestController
@RequestMapping("/frontend/itemCategory")
public class ItemCategoryController {
    @Autowired
    private ItemServiceFeign itemServiceFeign;

    /**
     * 查询首页商品分类
     */
    @RequestMapping("/selectItemCategoryAll")
    public Result selectItemCategoryAll() {
        CatResult catResult = itemServiceFeign.selectItemCategoryAll();
        if(catResult.getData().size()>0){
            return Result.ok(catResult);
        }
        return Result.error("查无结果");
    }
}
