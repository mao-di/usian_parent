package com.usian.service;

import com.usian.pojo.SearchItem;

import java.io.IOException;
import java.util.List;

/**
 * ClassName:SearchItemService
 * Author:maodi
 * CreateTime:2021/03/24/15:51
 */
public interface SearchItemService {
    Boolean importAll();

    public int insertDocument(String itemId) throws IOException;

    List<SearchItem> selectByQ(String q, Long page, Integer pageSize);

    public int updateDocument(String  itemId) throws IOException;

    public int deleteDocument(String itemId)throws IOException;

}
