package com.usian.service;

import com.usian.pojo.TbContentCategory;

import java.util.List;

/**
 * ClassName:ContentCategoryService
 * Author:maodi
 * CreateTime:2021/03/15/21:21
 */
public interface ContentCategoryService {
    public List<TbContentCategory> selectContentCategoryByParentId(Long parentId);

    public Integer insertContentCategory(TbContentCategory tbContentCategory);

    public Integer deleteContentCategoryById(Long categoryId);

    Integer updateContentCategory(Integer id, String name);

}
