package com.usian.controller;

/**
 * ClassName:SSOController
 * Author:maodi
 * CreateTime:2021/03/26/09:23
 */

import com.usian.pojo.TbUser;
import com.usian.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 用户注册与登录
 */
@RestController
@RequestMapping("/service/sso")
public class SSOController {
    @Autowired
    private SSOService ssoService;

    /**
     * 对用户的注册信息(用户名与电话号码)做数据校验
     */
    @RequestMapping("/checkUserInfo/{checkValue}/{checkFlag}")
    public boolean checkUserInfo(@PathVariable String checkValue,
                                 @PathVariable Integer checkFlag) {
        return this.ssoService.checkUserInfo(checkValue, checkFlag);
    }

    /**
     * 用户注册
     */
    @RequestMapping("/userRegister")
    public Integer userRegister(@RequestBody TbUser user) {
        return this.ssoService.userRegister(user);
    }

    /**
     * 用户登录
     */
    @RequestMapping("/userLogin")
    public Map userLogin(@RequestParam String username, @RequestParam String password) {
        return this.ssoService.userLogin(username, password);
    }

    /**
     * 查询用户登录是否过期
     * @param token
     * @return
     */
    @RequestMapping("/getUserByToken/{token}")
    public TbUser getUserByToken(@PathVariable String token) {
        return ssoService.getUserByToken(token);
    }

    /**
     * 用户退出登录
     */
    @RequestMapping("/logOut")
    public Boolean logOut(@RequestParam String token){
        return this.ssoService.logOut(token);
    }


}
