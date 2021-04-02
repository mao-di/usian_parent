package com.usian.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.usian.config.RedisClient;
import com.usian.mapper.TbContentMapper;
import com.usian.pojo.TbContent;
import com.usian.pojo.TbContentExample;
import com.usian.service.ContentService;
import com.usian.utils.AdNode;
import com.usian.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName:ContentServiceImpl
 * Author:maodi
 * CreateTime:2021/03/16/09:04
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Value("${PORTAL_AD_KEY}")
    private String PORTAL_AD_KEY;

    @Autowired
    private RedisClient redisClient;

    @Value("${AD_CATEGORY_ID}")
    private Long AD_CATEGORY_ID;

    @Value("${AD_HEIGHT}")
    private Integer AD_HEIGHT;

    @Value("${AD_WIDTH}")
    private Integer AD_WIDTH;

    @Value("${AD_HEIGHTB}")
    private Integer AD_HEIGHTB;

    @Value("${AD_WIDTHB}")
    private Integer AD_WIDTHB;

    @Autowired
    TbContentMapper tbContentMapper;

    @Override
    public PageResult selectTbContentAllByCategoryId(Integer page, Integer rows,
                                                     Long categoryId) {
        PageHelper.startPage(page, 5);
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> list = this.tbContentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
        PageResult result = new PageResult();
        result.setPageIndex(pageInfo.getPageNum());
        result.setTotalPage((int) pageInfo.getTotal());
        result.setResult(pageInfo.getList());
        return result;
    }

    @Override
    public Integer insertTbContent(TbContent tbContent) {
        tbContent.setUpdated(new Date());
        tbContent.setCreated(new Date());
        int num = this.tbContentMapper.insertSelective(tbContent);
        //缓存同步
        redisClient.hdel(PORTAL_AD_KEY, AD_CATEGORY_ID.toString());
        return num;
    }

    @Override
    public Integer deleteContentByIds(Long id) {
        int num = this.tbContentMapper.deleteByPrimaryKey(id);
        //缓存同步
        redisClient.hdel(PORTAL_AD_KEY, AD_CATEGORY_ID.toString());
        return num;
    }

    /**
     * 查询首页大广告位
     *
     * @return
     */
    @Override
    public List<AdNode> selectFrontendContentByAD() {
        //查询缓存
        List<AdNode> adNodeListRedis =
                (List<AdNode>) redisClient.hget(PORTAL_AD_KEY, AD_CATEGORY_ID.toString());
        if (adNodeListRedis != null) {
            System.out.println("查redis");
            return adNodeListRedis;
        }
        // 查询数据库
        TbContentExample tbContentExample = new TbContentExample();
        TbContentExample.Criteria criteria = tbContentExample.createCriteria();
        criteria.andCategoryIdEqualTo(AD_CATEGORY_ID);
        List<TbContent> tbContentList =
                tbContentMapper.selectByExample(tbContentExample);
        List<AdNode> adNodeList = new ArrayList<AdNode>();
        for (TbContent tbContent : tbContentList) {
            AdNode adNode = new AdNode();
            adNode.setSrc(tbContent.getPic());
            adNode.setSrcB(tbContent.getPic2());
            adNode.setHref(tbContent.getUrl());
            adNode.setHeight(AD_HEIGHT);
            adNode.setWidth(AD_WIDTH);
            adNode.setHeightB(AD_HEIGHTB);
            adNode.setWidthB(AD_WIDTHB);
            adNodeList.add(adNode);
        }
        //添加到缓存
        System.out.println("查数据库");
        redisClient.hset(PORTAL_AD_KEY, AD_CATEGORY_ID.toString(), adNodeList);
        return adNodeList;
    }
}
