package com.usian.mapper;

import com.usian.pojo.SearchItem;

import java.util.List;

/**
 * ClassName:SearchItemMapper
 * Author:maodi
 * CreateTime:2021/03/24/15:47
 */
public interface SearchItemMapper {

    List<SearchItem> getItemList();

    SearchItem getItemById(Long itemId);


}
