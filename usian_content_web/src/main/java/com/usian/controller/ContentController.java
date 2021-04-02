package com.usian.controller;

import com.usian.feign.ContentServiceFeign;
import com.usian.pojo.TbContent;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:ContentController
 * Author:maodi
 * CreateTime:2021/03/16/09:09
 */
@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    private ContentServiceFeign contentServiceFeign;

    /**
     * 根据分类 ID 查询分类内容
     *
     * @param page
     * @param rows
     * @param categoryId
     * @return
     */
    @RequestMapping("/selectTbContentAllByCategoryId")
    public Result selectTbContentAllByCategoryId(@RequestParam(defaultValue = "1")
                                                         Integer page, @RequestParam(defaultValue = "30") Integer rows, Long categoryId) {
        PageResult pageResult = contentServiceFeign.selectTbContentAllByCategoryId(
                page, rows, categoryId);
        if (pageResult != null && pageResult.getResult().size() > 0) {
            return Result.ok(pageResult);
        }
        return Result.error("查无结果");
    }

    @RequestMapping("/insertTbContent")
    public Result insertTbContent(TbContent tbContent) {
        Integer num = contentServiceFeign.insertTbContent(tbContent);
        if (num != null) {
            return Result.ok("添加成功");
        }
        return Result.error("添加失败");
    }

    @RequestMapping("/deleteContentByIds")
    public Result deleteContentByIds(Long ids) {
        Integer num = contentServiceFeign.deleteContentByIds(ids);
        if (num != null) {
            return Result.ok("删除成功");
        }
        return Result.error("删除失败");
    }


}
