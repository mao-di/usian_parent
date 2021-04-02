package com.usian.controller;

import com.usian.pojo.TbItemParam;
import com.usian.pojo.TbItemParamItem;
import com.usian.service.ItemParamService;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:ItemParamController
 * Author:maodi
 * CreateTime:2021/03/11/16:46
 */
@RestController
@RequestMapping("/service/itemParam")
public class ItemParamController {
    @Autowired
    private ItemParamService itemParamService;

    /**
     * 根据商品分类 ID 查询规格参数模板
     */
    @RequestMapping("/selectItemParamByItemCatId")
    public TbItemParam selectItemParamByItemCatId(Long itemCatId) {
        return this.itemParamService.selectItemParamByItemCatId(itemCatId);
    }
    @RequestMapping("/selectItemParamAll")
    public PageResult selectItemParamAll(Integer page, Integer rows){
        return this.itemParamService.selectItemParamAll(page,rows);
    }
    @RequestMapping("/insertItemParam")
    public Integer insertItemParam(Long itemCatId, String paramData){
        return itemParamService.insertItemParam(itemCatId,paramData);
    }
    @RequestMapping("/deleteItemParamById")
    public Result deleteItemParamById( Long id) {
        Integer integer = itemParamService.deleteItemParamById(id);
        if (integer!=0){
            return Result.ok("删除成功");
        }else {
            return Result.error("删除失败");
        }
    }
    /**
     * 根据商品 ID 查询商品规格
     */
    @RequestMapping("/selectTbItemParamItemByItemId")
    public TbItemParamItem selectTbItemParamItemByItemId(@RequestParam Long itemId){
        return itemParamService.selectTbItemParamItemByItemId(itemId);
    }
}
