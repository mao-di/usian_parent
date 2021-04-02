package com.usian.controller;

import com.usian.pojo.TbContentCategory;
import com.usian.service.ContentCategoryService;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName:ContentCategoryController
 * Author:maodi
 * CreateTime:2021/03/15/21:22
 */
@RestController
@RequestMapping("/service/contentCategory")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    /**
     * 根据父节点 ID 查询子节点
     */
    @RequestMapping("/selectContentCategoryByParentId")
    public List<TbContentCategory>
    selectContentCategoryByParentId(@RequestParam Long parentId) {
        return this.contentCategoryService.selectContentCategoryByParentId(parentId);
    }

    @RequestMapping("/insertContentCategory")
    public Integer insertContentCategory(@RequestBody TbContentCategory
                                                 tbContentCategory) {
        return this.contentCategoryService.insertContentCategory(tbContentCategory);
    }
    @RequestMapping("/deleteContentCategoryById")
    public Integer deleteContentCategoryById(Long categoryId){
        return this.contentCategoryService.deleteContentCategoryById(categoryId);
    }

    @RequestMapping("/updateContentCategory")
    public Result updateContentCategory(Integer id ,String name){
        Integer status = contentCategoryService.updateContentCategory(id,name);
        if(status == 200){
            return Result.ok("修改成功");
        }
        return Result.error("修改失败");
    }

}
