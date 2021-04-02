package com.usian.controller;

import com.usian.feign.ItemServiceFeign;
import com.usian.pojo.TbItemParam;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
/**
 * ClassName:ItemParamController
 * Author:maodi
 * CreateTime:2021/03/11/16:48
 */
@RestController
@RequestMapping("/backend/itemParam")
public class ItemParamController {

    @Autowired
    private ItemServiceFeign itemServiceFeign;

    /**
     * 根据商品分类 ID 查询规格参数模板
     */
    @RequestMapping("/selectItemParamByItemCatId/{itemCatId}")
    public Result selectItemParamByItemCatId(@PathVariable("itemCatId") Long itemCatId) {

        TbItemParam tbItemParam =
                this.itemServiceFeign.selectItemParamByItemCatId(itemCatId);
        if (tbItemParam != null) {
            return Result.ok(tbItemParam);
        }
        return Result.error("查无结果");
    }

    @RequestMapping("/selectItemParamAll")
    public Result selectItemParamAll(@RequestParam(defaultValue = "1") Integer
                                             page, @RequestParam(defaultValue = "3") Integer rows) {
        PageResult pageResult =
                this.itemServiceFeign.selectItemParamAll(page, rows);
        if (pageResult.getResult().size() > 0) {
            return Result.ok(pageResult);
        }
        return Result.error("查无结果");
    }

    @RequestMapping("/insertItemParam")
    public Result insertItemParam(@RequestParam Long itemCatId,@RequestParam String paramData) {
        Integer num = itemServiceFeign.insertItemParam(itemCatId, paramData);
        if (num == 1) {
            return Result.ok();
        }
        return Result.error("添加失败：该类目已有规格模板");
    }

    @RequestMapping("/deleteItemParamById")
    public Result deleteItemParamById(Long id) {
       return itemServiceFeign.deleteItemParamById(id);
    }

}
