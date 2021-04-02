package com.usian.service;

import com.usian.pojo.DeDuplication;

/**
 * ClassName:DeDuplicationService
 * Author:maodi
 * CreateTime:2021/03/29/18:39
 */
public interface DeDuplicationService {
    DeDuplication selectDeDuplicationByTxNo(String txNo);

    void insertDeDuplication(String txNo);
}
