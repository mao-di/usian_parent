package com.usian.controller;

import com.usian.pojo.TbItem;
import com.usian.pojo.TbItemDesc;
import com.usian.service.ItemService;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName:ItemController
 * Author:maodi
 * CreateTime:2021/03/11/13:04
 */
@RestController
@RequestMapping("/service/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    /**
     * 查询商品信息
     * 根据商品id
     *
     * @param itemId
     * @return
     */
    @RequestMapping("/selectItemInfo")
    public TbItem selectItemInfo(Long itemId) {
        return this.itemService.selectItemInfo(itemId);
    }

    /**
     * 根据商品 ID 查询商品描述
     */
    @RequestMapping("/selectItemDescByItemId")
    public TbItemDesc selectItemDescByItemId(Long itemId){
        return this.itemService.selectItemDescByItemId(itemId);
    }


    @RequestMapping(value = "/selectTbItemAllByPage")
    public PageResult selectTbItemAllByPage(Integer page, Integer rows) {
        return this.itemService.selectTbItemAllByPage(page, rows);
    }

    @RequestMapping("/insertTbItem")
    public Integer insertTbItem(@RequestBody TbItem tbItem, String desc,
                                String itemParams) {
        return this.itemService.insertTbItem(tbItem, desc, itemParams);
    }

    @RequestMapping("/updateTbItem")
    public Integer updateTbItem(@RequestBody TbItem tbItem, String desc,
                                String itemParams) {
        return this.itemService.updateTbItem(tbItem, desc, itemParams);
    }

    @RequestMapping("/preUpdateItem")
    public Map<String, Object> preUpdateItem(Long itemId) {
        return this.itemService.preUpdateItem(itemId);
    }

    @RequestMapping("/deleteItemById")
    public Result deleteItemById(Long itemId) {
        itemService.deleteItemById(itemId);
        return Result.ok("删除成功");
    }
}
