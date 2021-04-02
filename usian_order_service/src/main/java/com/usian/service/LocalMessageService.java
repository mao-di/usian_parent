package com.usian.service;

import com.usian.pojo.LocalMessage;

import java.util.List;

/**
 * ClassName:LocalMessageService
 * Author:maodi
 * CreateTime:2021/03/29/18:35
 */
public interface LocalMessageService {
    List<LocalMessage> selectlocalMessageByStatus(Integer status);
}
