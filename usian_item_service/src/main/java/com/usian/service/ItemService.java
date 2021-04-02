package com.usian.service;

import com.usian.pojo.TbItem;
import com.usian.pojo.TbItemDesc;
import com.usian.utils.PageResult;
import com.usian.utils.Result;

import java.util.Map;

/**
 * ClassName:ItemService
 * Author:maodi
 * CreateTime:2021/03/11/13:03
 */
public interface ItemService {

    public TbItem selectItemInfo(Long itemId);

    public TbItemDesc selectItemDescByItemId(Long itemId);

    public PageResult selectTbItemAllByPage(Integer page, Integer rows);

    public Integer insertTbItem(TbItem tbItem, String desc, String itemParams);

    public Map<String, Object> preUpdateItem(Long itemId);

    void deleteItemById(Long itemId);

    Integer updateTbItem(TbItem tbItem, String desc, String itemParams);


    Integer updateTbItemByOrderId(String orderId);
}
