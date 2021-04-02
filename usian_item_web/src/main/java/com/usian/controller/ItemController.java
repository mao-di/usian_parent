package com.usian.controller;

import com.netflix.discovery.converters.Auto;
import com.usian.feign.ItemServiceFeign;
import com.usian.pojo.TbItem;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * ClassName:ItemController
 * Author:maodi
 * CreateTime:2021/03/11/13:08
 */
@RestController
@RequestMapping("/backend/item")
@Api("商品管理接口")
public class ItemController {
    @Autowired
    private ItemServiceFeign itemServiceFeign;

    /**
     * 查询商品基本信息
     */
    @RequestMapping("/selectItemInfo")
    @ApiOperation(value = "查询商品基本信息", notes = "根据itemId查询该商品的基本信息")
    @ApiImplicitParam(value = "商品Id", name = "itemId", type = "Long")
    public Result selectItemInfo(@RequestParam("itemId") Long itemId) {
        TbItem tbItem = itemServiceFeign.selectItemInfo(itemId);
        if (tbItem != null) {
            return Result.ok(tbItem);
        }
        return Result.error("查无结果");
    }

    @RequestMapping("/selectTbItemAllByPage")
    @ApiOperation(value = "查询商品基本信息分页", notes = "分页展示每页两条")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", type = "Integer", value = "当前页码", defaultValue = "1"),
            @ApiImplicitParam(name = "rows", type = "Integer", value = "每页展示条数", defaultValue = "2")
    })
    public Result selectTbItemAllByPage(@RequestParam(defaultValue = "1")
                                                Integer page, @RequestParam(defaultValue = "2") Integer rows) {

        PageResult pageResult = itemServiceFeign.selectTbItemAllByPage(
                page, rows);
        if (pageResult.getResult() != null &&
                pageResult.getResult().size() > 0) {
            return Result.ok(pageResult);
        }
        return Result.error("查无结果");
    }

    @RequestMapping("/insertTbItem")
    @ApiOperation(value = "添加商品",notes = "添加商品及描述和规格参数信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="desc",type = "String",value = "商品描述信息"),
            @ApiImplicitParam(name="itemParams",type = "String",value = "商品规格参数")
    })
    public Result insertTbItem(TbItem tbItem, String desc, String itemParams) {
        Integer insertTbItemNum = itemServiceFeign.insertTbItem(tbItem, desc, itemParams);
        if (insertTbItemNum == 3) {
            return Result.ok("添加成功");
        }
        return Result.error("添加失败");
    }

    @RequestMapping("/updateTbItem")
    @ApiOperation(value = "修改商品",notes = "修改商品及描述和规格参数信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name="desc",type = "String",value = "商品描述信息"),
            @ApiImplicitParam(name="itemParams",type = "String",value = "商品规格参数")
    })
    public Result updateTbItem(TbItem tbItem, String desc, String itemParams) {
        Integer insertTbItemNum = itemServiceFeign.updateTbItem(tbItem, desc, itemParams);
        if (insertTbItemNum > 0) {
            return Result.ok("修改成功");
        }
        return Result.error("修改失败");
    }

    @RequestMapping("/preUpdateItem")
    public Result preUpdateItem(Long itemId) {
        Map<String, Object> map = itemServiceFeign.preUpdateItem(itemId);
        if (map.size() > 0) {
            return Result.ok(map);
        }
        return Result.error("查无结果");
    }

    @RequestMapping("/deleteItemById")
    @ApiOperation(value = "删除商品",notes = "根据商品id删除商品信息")
    @ApiImplicitParam(name="itemId",type = "Long",value = "商品Id")
    public Result deleteItemById(Long itemId) {
        itemServiceFeign.deleteItemById(itemId);
        return Result.ok("删除成功");
    }

}
