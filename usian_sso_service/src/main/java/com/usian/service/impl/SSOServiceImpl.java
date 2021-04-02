package com.usian.service.impl;
import com.usian.config.RedisClient;
import com.usian.mapper.TbUserMapper;
import com.usian.pojo.TbUser;
import com.usian.pojo.TbUserExample;
import com.usian.service.SSOService;
import com.usian.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

/**
 * ClassName:SSOServiceImpl
 * Author:maodi
 * CreateTime:2021/03/26/09:22
 */
@Service
@Transactional
public class SSOServiceImpl implements SSOService {
    @Autowired
    private TbUserMapper userMapper;
    @Autowired
    private RedisClient redisClient;

    private final String USER_INFO = "USER_INFO";

    private Long SESSION_EXPIRE = 80000L;

    /**
     * 对用户的注册信息(用户名与电话号码)做数据校验
     */
    @Override
    public boolean checkUserInfo(String checkValue, Integer checkFlag) {
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        if (checkFlag == 1) {
            criteria.andUsernameEqualTo(checkValue);
        } else if (checkFlag == 2) {
            criteria.andPhoneEqualTo(checkValue);
        }
        List<TbUser> tbUsers = userMapper.selectByExample(example);
        if (tbUsers == null || tbUsers.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public Integer userRegister(TbUser user) {
        //将密码做加密处理。
        String pwd = MD5Utils.digest(user.getPassword());
        user.setPassword(pwd);
        //补齐数据
        user.setCreated(new Date());
        user.setUpdated(new Date());
        return this.userMapper.insert(user);
    }

    @Override
    public Map userLogin(String username, String password) {
        // 1、判断用户名密码是否正确。
        String pwd = MD5Utils.digest(password);
        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(pwd);
        List<TbUser> userList = this.userMapper.selectByExample(example);
        if (userList == null || userList.size() <= 0) {
            return null;
        }
        TbUser tbUser = userList.get(0);
        // 2、登录成功后生成token。Token相当于原来的sessionid，字符串，可以使用uuid。
        String token = UUID.randomUUID().toString();
        // 3、把用户信息保存到redis。Key就是token，value就是TbUser对象转换成json。
        tbUser.setPassword(null);
        redisClient.set(USER_INFO + ":" + token, tbUser);
        // 5、设置key的过期时间。模拟Session的过期时间。
        redisClient.expire(USER_INFO + ":" + token, SESSION_EXPIRE);

        Map<String, String> map = new HashMap<String, String>();
        map.put("token", token);
        map.put("userid", tbUser.getId().toString());
        map.put("username", tbUser.getUsername());
        return map;
    }

    /**
     * 查询用户登录是否过期
     *
     * @param token
     * @return
     */
    @Override
    public TbUser getUserByToken(String token) {
        TbUser tbUser = (TbUser) redisClient.get(USER_INFO + ":" + token);
        if (tbUser != null) {
            //需要重置key的过期时间。
            redisClient.expire(USER_INFO + ":" + token, SESSION_EXPIRE);
            return tbUser;
        }
        return null;
    }

    /**
     * 用户退出登录
     *
     * @param token
     */
    @Override
    public Boolean logOut(String token) {
        return redisClient.del(USER_INFO + ":" + token);
    }
}
