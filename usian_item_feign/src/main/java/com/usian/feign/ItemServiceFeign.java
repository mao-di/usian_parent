package com.usian.feign;

import com.usian.fallback.ItemServiceFallback;
import com.usian.pojo.*;
import com.usian.utils.CatResult;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName:com.usian.feign.ItemServiceFeign
 * Author:maodi
 * CreateTime:2021/03/11/13:05
 */
@FeignClient(value = "usian-item-service",fallbackFactory = ItemServiceFallback.class)
public interface ItemServiceFeign {

    @RequestMapping("/service/item/selectItemInfo")
    TbItem selectItemInfo(@RequestParam("itemId") Long itemId);

    @GetMapping("/service/item/selectTbItemAllByPage")
    PageResult selectTbItemAllByPage(@RequestParam Integer page,
                                     @RequestParam Integer rows);

    @RequestMapping("/service/itemCategory/selectItemCategoryByParentId")
    List<TbItemCat> selectItemCategoryByParentId(@RequestParam Long id);

    @PostMapping("/service/itemParam/selectItemParamByItemCatId")
    TbItemParam selectItemParamByItemCatId(@RequestParam("itemCatId") Long itemCatId);

    @GetMapping("/service/item/insertTbItem")
    public Integer insertTbItem(@RequestBody TbItem tbItem, @RequestParam String desc,
                                @RequestParam String itemParams);

    @RequestMapping("/service/itemParam/selectItemParamAll")
    public Result selectItemParamAll();

    @RequestMapping("/service/item/preUpdateItem")
    Map<String, Object> preUpdateItem(@RequestParam("itemId") Long itemId);

    @RequestMapping("/service/item/deleteItemById")
    void deleteItemById(@RequestParam("itemId") Long itemId);

    @GetMapping("/service/item/updateTbItem")
    Integer updateTbItem(@RequestBody TbItem tbItem, @RequestParam String desc, @RequestParam String itemParams);

    @GetMapping("/service/itemParam/selectItemParamAll")
    PageResult selectItemParamAll(@RequestParam Integer page,
                                  @RequestParam Integer rows);

    @RequestMapping("/service/itemParam/insertItemParam")
    Integer insertItemParam(@RequestParam Long itemCatId,
                            @RequestParam String paramData);

    @RequestMapping("/service/itemParam/deleteItemParamById")
    Result deleteItemParamById(@RequestParam Long id);

    @RequestMapping("/service/itemCategory/selectItemCategoryAll")
    CatResult selectItemCategoryAll();

    @RequestMapping("/service/item/selectItemDescByItemId")
    TbItemDesc selectItemDescByItemId(@RequestParam Long itemId);

    @RequestMapping("/service/itemParam/selectTbItemParamItemByItemId")
    TbItemParamItem selectTbItemParamItemByItemId(@RequestParam Long itemId);

}
