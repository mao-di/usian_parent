package com.usian.mapper;

import com.usian.pojo.TbItem;
import com.usian.pojo.TbItemExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface TbItemMapper {
    int countByExample(TbItemExample example);

    int deleteByExample(TbItemExample example);

    @Update("update tb_item set status=3 where id=#{id}")
    int deleteByPrimaryKey(Long id);

    int insert(TbItem record);

    int insertSelective(TbItem record);

    List<TbItem> selectByExample(TbItemExample example);

//    @Select("select  * from tb_item where id=#{id}")
    TbItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByExample(@Param("record") TbItem record, @Param("example") TbItemExample example);

    int updateByPrimaryKeySelective(TbItem record);

    int updateByPrimaryKey(TbItem record);

    @Select("select  * from tb_item where cid=#{itemCatId}")
    List<TbItem> selectByCid(Long itemCatId);

}