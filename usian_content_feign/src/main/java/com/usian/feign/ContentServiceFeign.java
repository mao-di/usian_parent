package com.usian.feign;

import com.usian.pojo.TbContent;
import com.usian.pojo.TbContentCategory;
import com.usian.utils.AdNode;
import com.usian.utils.PageResult;
import com.usian.utils.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * ClassName:ContentServiceFeign
 * Author:maodi
 * CreateTime:2021/03/15/21:23
 */
@FeignClient(value = "usian-content-service")
public interface ContentServiceFeign {

    @PostMapping("/service/contentCategory/selectContentCategoryByParentId")
    List<TbContentCategory> selectContentCategoryByParentId(@RequestParam("parentId")
                                                                    Long parentId);

    @PostMapping("/service/contentCategory/insertContentCategory")
    Integer insertContentCategory(TbContentCategory tbContentCategory);

    @PostMapping("/service/contentCategory/deleteContentCategoryById")
    public Integer deleteContentCategoryById(@RequestParam("categoryId") Long categoryId);

    @RequestMapping("/service/contentCategory/updateContentCategory")
    Result updateContentCategory(@RequestParam Integer id, @RequestParam String name);

    @PostMapping("/service/content/selectTbContentAllByCategoryId")
    PageResult selectTbContentAllByCategoryId(@RequestParam("page") Integer page,
                                              @RequestParam("rows") Integer rows, @RequestParam("categoryId") Long categoryId);

    @RequestMapping("/service/content/insertTbContent")
    Integer insertTbContent(TbContent tbContent);

    @RequestMapping("/service/content/deleteContentByIds")
    Integer deleteContentByIds(@RequestParam("id") Long id);

    @PostMapping("/service/content/selectFrontendContentByAD")
    List<AdNode> selectFrontendContentByAD();

}
