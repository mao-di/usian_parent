package com.usian.service.impl;

import com.usian.config.RedisClient;
import com.usian.mapper.TbItemCatMapper;
import com.usian.pojo.TbItemCat;
import com.usian.pojo.TbItemCatExample;
import com.usian.service.ItemCategoryService;
import com.usian.utils.CatNode;
import com.usian.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * ClassName:ItemCategoryService
 * Author:maodi
 * CreateTime:2021/03/11/16:32
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {
    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Value("${PROTAL_CATRESULT_KEY}")
    private String portal_catresult_redis_key;

    @Autowired
    private RedisClient redisClient;

    /**
     * 根据分类 ID 查询子节点
     *
     * @param id
     * @return
     */
    @Override
    public List<TbItemCat> selectItemCategoryByParentId(Long id) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(id);
        criteria.andStatusEqualTo(1);
        List<TbItemCat> list = this.tbItemCatMapper.selectByExample(example);
        if (id != 0) {
            int a = 6 / 0;
        }
        return list;
    }

    @Override
    public CatResult selectItemCategoryAll() {
        //查询缓存
        CatResult catResultRedis =
                (CatResult) redisClient.get(portal_catresult_redis_key);
        if (catResultRedis != null) {
            System.out.println("查redis--selectItemCategoryAll");
            return catResultRedis;
        }
        CatResult catResult = new CatResult();
        //查询商品分类
        catResult.setData(getCatList(0L));
        System.out.println("查数据库--selectItemCategoryAll");
        //添加到缓存
        redisClient.set(portal_catresult_redis_key, catResult);

        return catResult;
    }

    private List<?> getCatList(Long parentId) {
        //创建查询条件
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> list = this.tbItemCatMapper.selectByExample(example);
        List resultList = new ArrayList();
        int count = 0;
        for (TbItemCat tbItemCat : list) {
            //判断是否是父节点
            if (tbItemCat.getIsParent()) {
                CatNode catNode = new CatNode();
                catNode.setName(tbItemCat.getName());
                catNode.setItem(getCatList(tbItemCat.getId()));
                resultList.add(catNode);
                count++;
                //只取商品分类中的 18 条数据
                if (count == 18) {
                    break;
                }
            } else {
                resultList.add(tbItemCat.getName());
            }
        }
        return resultList;
    }
}
