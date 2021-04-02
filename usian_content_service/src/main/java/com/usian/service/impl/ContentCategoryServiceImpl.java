package com.usian.service.impl;

import com.usian.mapper.TbContentCategoryMapper;
import com.usian.pojo.TbContentCategory;
import com.usian.pojo.TbContentCategoryExample;
import com.usian.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * ClassName:ContentCategoryServiceImpl
 * Author:maodi
 * CreateTime:2021/03/15/21:21
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Override
    public List<TbContentCategory> selectContentCategoryByParentId(Long parentId) {
        TbContentCategoryExample tbContentCategoryExample = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> list =
                this.tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        return list;
    }

    @Override
    public Integer insertContentCategory(TbContentCategory tbContentCategory) {
        //1、添加内容分类
        tbContentCategory.setUpdated(new Date());
        tbContentCategory.setCreated(new Date());
        tbContentCategory.setIsParent(false);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setStatus(1);
        Integer contentCategoryNum =
                this.tbContentCategoryMapper.insert(tbContentCategory);
        //2、如果他爹不是爹，要把他爹改成爹
        //2.1、查询当前新节点的父节点
        TbContentCategory contentCategory =
                this.tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        //2.2、判断当前父节点是否是叶子节点
        if (!contentCategory.getIsParent()) {
            contentCategory.setIsParent(true);
            contentCategory.setUpdated(new Date());
            this.tbContentCategoryMapper.updateByPrimaryKey(contentCategory);
        }
        return contentCategoryNum;
    }

    @Override
    public Integer deleteContentCategoryById(Long categoryId) {
        //查询当前节点
        TbContentCategory tbContentCategory =
                this.tbContentCategoryMapper.selectByPrimaryKey(categoryId);
        //父节点 不允许删除
        if(tbContentCategory.getIsParent()==true){
            return 0;
        }
        //不是父节点
        tbContentCategoryMapper.deleteByPrimaryKey(categoryId);
        //当前节点的兄弟节点
        TbContentCategoryExample tbContentCategoryExample = new
                TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria =
                tbContentCategoryExample.createCriteria();
        criteria.andParentIdEqualTo(tbContentCategory.getParentId());
        List<TbContentCategory> tbContentCategoryList =
                tbContentCategoryMapper.selectByExample(tbContentCategoryExample);
        //删除之后如果父节点没有孩子，则修改isParent为false
        if(tbContentCategoryList.size()==0){
            TbContentCategory parenttbContentCategory = new TbContentCategory();
            parenttbContentCategory.setId(tbContentCategory.getParentId());
            parenttbContentCategory.setIsParent(false);
            parenttbContentCategory.setUpdated(new Date());
            this.tbContentCategoryMapper.updateByPrimaryKeySelective(
                    parenttbContentCategory);
        }
        return 200;
    }

    @Override
    public Integer updateContentCategory(Integer id, String name) {
       Integer integer= tbContentCategoryMapper.update(id,name);
        if (integer>0){
            return 200;
        }
        return 0;
    }
}
