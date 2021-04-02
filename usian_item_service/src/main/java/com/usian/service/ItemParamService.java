package com.usian.service;

import com.usian.pojo.TbItemParam;
import com.usian.pojo.TbItemParamItem;
import com.usian.utils.PageResult;
import com.usian.utils.Result;

/**
 * ClassName:ItemParamService
 * Author:maodi
 * CreateTime:2021/03/11/16:41
 */
public interface ItemParamService {

    public TbItemParamItem selectTbItemParamItemByItemId(Long itemId);

    public TbItemParam selectItemParamByItemCatId(Long itemCatId);


    public PageResult selectItemParamAll(Integer page, Integer rows);


    Integer insertItemParam(Long itemCatId, String paramData);

    Integer deleteItemParamById(Long id);

}
