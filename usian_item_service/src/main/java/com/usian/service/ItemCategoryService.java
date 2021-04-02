package com.usian.service;

import com.usian.pojo.TbItemCat;
import com.usian.utils.CatResult;

import java.util.List;

/**
 * ClassName:ItemCategoryService
 * Author:maodi
 * CreateTime:2021/03/11/16:32
 */
public interface ItemCategoryService {
    public List<TbItemCat> selectItemCategoryByParentId(Long id);

    CatResult selectItemCategoryAll();

}
