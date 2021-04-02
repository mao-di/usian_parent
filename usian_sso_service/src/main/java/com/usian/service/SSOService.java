package com.usian.service;

import com.usian.pojo.TbUser;

import java.util.Map;

/**
 * ClassName:SSOService
 * Author:maodi
 * CreateTime:2021/03/26/09:22
 */
public interface SSOService {
    public boolean checkUserInfo(String checkValue, Integer checkFlag);

    public Integer userRegister(TbUser user);

    public Map userLogin(String username, String password);

    public TbUser getUserByToken(String token);

    public Boolean logOut(String token);

}
