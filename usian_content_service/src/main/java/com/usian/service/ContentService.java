package com.usian.service;

import com.usian.pojo.TbContent;
import com.usian.utils.AdNode;
import com.usian.utils.PageResult;

import java.util.List;

/**
 * ClassName:ContentService
 * Author:maodi
 * CreateTime:2021/03/16/09:04
 */
public interface ContentService {
    public PageResult selectTbContentAllByCategoryId(Integer page, Integer rows,
                                                     Long categoryId);
    public Integer insertTbContent(TbContent tbContent);

    public Integer deleteContentByIds(Long id);

    public List<AdNode> selectFrontendContentByAD();



}
