package com.usian.service.impl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usian.config.RedisClient;
import com.usian.mapper.TbItemMapper;
import com.usian.mapper.TbItemParamItemMapper;
import com.usian.mapper.TbItemParamMapper;
import com.usian.pojo.*;
import com.usian.service.ItemParamService;
import com.usian.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

/**
 * ClassName:ItemParamServiceImpl
 * Author:maodi
 * CreateTime:2021/03/11/16:41
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {
    @Value("${ITEM_INFO}")
    private String ITEM_INFO;

    @Value("${PARAM}")
    private String PARAM;

    @Value("${ITEM_INFO_EXPIRE}")
    private Integer ITEM_INFO_EXPIRE;

    @Value("${SETNX_PARAM_LOCK_KEY}")
    private String SETNX_PARAM_LOCK_KEY;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private TbItemParamMapper tbItemParamMapper;
    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Override
    public TbItemParamItem selectTbItemParamItemByItemId(Long itemId) {
        //1、先查询redis,如果有直接返回
        TbItemParamItem tbItemParamItem = (TbItemParamItem) redisClient.get(ITEM_INFO +
                ":" + itemId + ":" + PARAM);
        if(tbItemParamItem!=null){
            return tbItemParamItem;
        }
        // 获取分布式锁
        if(redisClient.setnx(SETNX_PARAM_LOCK_KEY+":"+itemId,itemId,30L)){
            //2、再查询mysql,并把查询结果缓存到redis,并设置失效时间
            TbItemParamItemExample tbItemParamItemExample = new TbItemParamItemExample();
            TbItemParamItemExample.Criteria criteria =
                    tbItemParamItemExample.createCriteria();
            criteria.andItemIdEqualTo(itemId);
            List<TbItemParamItem> tbItemParamItems =
                    tbItemParamItemMapper.selectByExampleWithBLOBs(tbItemParamItemExample);
            if(tbItemParamItems!=null && tbItemParamItems.size()>0){
                tbItemParamItem = tbItemParamItems.get(0);
                redisClient.set(ITEM_INFO + ":" + itemId + ":" + PARAM,tbItemParamItem);
                redisClient.expire(ITEM_INFO + ":" + itemId + ":" +
                        PARAM,ITEM_INFO_EXPIRE);

            }else{
                redisClient.set(ITEM_INFO + ":" + itemId + ":" + PARAM,null);
                redisClient.expire(ITEM_INFO + ":" + itemId + ":" + PARAM,30L);
            }
            // 释放分布式锁
            redisClient.del(SETNX_PARAM_LOCK_KEY+":"+itemId);
            return  tbItemParamItem;
        }else{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 重试分布式锁
            return selectTbItemParamItemByItemId(itemId);
        }
    }

    @Override
    public TbItemParam selectItemParamByItemCatId(Long itemCatId) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(itemCatId);
        List<TbItemParam> list = tbItemParamMapper.selectByExampleWithBLOBs(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public PageResult selectItemParamAll(Integer page, Integer rows) {
        PageHelper.startPage (page,5);
        TbItemParamExample example = new TbItemParamExample();
        example.setOrderByClause("updated DESC");
        List<TbItemParam> list =
                this.tbItemParamMapper.selectByExampleWithBLOBs(example);
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        PageResult pageResult = new PageResult();
        pageResult.setPageIndex(page);
        pageResult.setResult(pageInfo.getList());
        pageResult.setTotalPage(Math.toIntExact(Long.valueOf(pageInfo.getPages())));
        return pageResult;
    }

    @Override
    public Integer insertItemParam(Long itemCatId, String paramData) {
        //1、判断该类别的商品是否有规格模板
        TbItemParamExample tbItemParamExample = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = tbItemParamExample.createCriteria();
        criteria.andItemCatIdEqualTo(itemCatId);
        List<TbItemParam> itemParamList =
                tbItemParamMapper.selectByExample(tbItemParamExample);
        if(itemParamList.size()>0){
            return 0;
        }

        //2、保存规格模板
        Date date = new Date();
        TbItemParam tbItemParam = new TbItemParam();
        tbItemParam.setItemCatId(itemCatId);
        tbItemParam.setParamData(paramData);
        tbItemParam.setUpdated(date);
        tbItemParam.setCreated(date);
        return tbItemParamMapper.insertSelective(tbItemParam);
    }
    @Autowired
    TbItemMapper tbItemMapper;

    @Override
    public Integer deleteItemParamById(Long id) {
        //根据id查询itemParam表的对象，获取item对象
        TbItemParam tbItemParam = tbItemParamMapper.selectByPrimaryKey(id);
        //根据itemparam对象的item_cat_id找到该对象使用的模板id
        Long itemCatId = tbItemParam.getItemCatId();
        //根据模板id查询是否有使用该模板的item对象
        List<TbItem> itemList=tbItemMapper.selectByCid(itemCatId);
        //如果集合不为空则说明有对象；
        if (itemList!=null && itemList.size()>0){
            //返回数据不执行删除
            return 0;
        }else {
            //如果没有则执行删除
            return tbItemParamMapper.delete(id);
        }
    }
}
