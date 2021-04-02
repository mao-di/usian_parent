package com.usian.controller;

import com.usian.pojo.TbContent;
import com.usian.service.ContentService;
import com.usian.utils.AdNode;
import com.usian.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName:ContentController
 * Author:maodi
 * CreateTime:2021/03/16/09:07
 */
@RestController
@RequestMapping("/service/content")
public class ContentController {
    @Autowired
    private ContentService contentService;

    /**
     * 根据分类查询内容
     */
    @RequestMapping("/selectTbContentAllByCategoryId")
    public PageResult selectTbContentAllByCategoryId(@RequestParam Integer page,
                                                     @RequestParam Integer rows, @RequestParam Long categoryId) {
        return this.contentService.selectTbContentAllByCategoryId(page, rows, categoryId);
    }

    @RequestMapping("/insertTbContent")
    public Integer insertTbContent(@RequestBody TbContent tbContent) {
        return this.contentService.insertTbContent(tbContent);
    }

    @RequestMapping("/deleteContentByIds")
    public Integer deleteContentByIds(@RequestParam Long id) {
        return this.contentService.deleteContentByIds(id);
    }

    @RequestMapping("/selectFrontendContentByAD")
    public List<AdNode> selectFrontendContentByAD() {
        return this.contentService.selectFrontendContentByAD();
    }
}
