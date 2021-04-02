package com.usian.controller;
import com.usian.feign.SSOServiceFeign;
import com.usian.pojo.TbUser;
import com.usian.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName:SSOController
 * Author:maodi
 * CreateTime:2021/03/26/09:24
 */
@RestController
@RequestMapping("/frontend/sso")
public class SSOController {
    @Autowired
    private SSOServiceFeign ssoSeriviceFeign;

    @RequestMapping("/checkUserInfo/{checkValue}/{checkFlag}")
    public Result checkUserInfo(@PathVariable String checkValue,
                                @PathVariable Integer checkFlag){
        Boolean checkUserInfo= ssoSeriviceFeign.checkUserInfo(checkValue,checkFlag);
        if(checkUserInfo){
            return Result.ok(checkUserInfo);
        }
        return Result.error("校验失败");
    }
    /**
     * 用户注册
     */
    @RequestMapping("/userRegister")
    public Result userRegister(TbUser user){
        Integer userRegister = ssoSeriviceFeign.userRegister(user);
        if(userRegister==1){
            return Result.ok();
        }
        return Result.error("注册失败");
    }

    /**
     * 用户登录
     */
    @RequestMapping("/userLogin")
    public Result userLogin(String username,String password){

        Map map = ssoSeriviceFeign.userLogin(username, password);
        if(map!=null){
            return Result.ok(map);
        }
        return Result.error("登录失败");
    }

    /**
     * 查询用户登录是否过期
     * @param token
     * @return
     */
    @RequestMapping("/getUserByToken/{token}")
    public Result getUserByToken(@PathVariable String token) {
        TbUser tbUser = ssoSeriviceFeign.getUserByToken(token);
        if(tbUser!=null){
            return Result.ok();
        }
        return Result.error("登录过期");
    }

    /**
     * 用户退出登录
     */
    @RequestMapping("/logOut")
    public Result logOut(String token){
        Boolean logOut = ssoSeriviceFeign.logOut(token);
        if(logOut){
            return Result.ok();
        }
        return Result.error("退出失败");
    }

}
