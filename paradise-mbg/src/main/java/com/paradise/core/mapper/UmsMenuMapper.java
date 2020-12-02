package com.paradise.core.mapper;

import com.paradise.core.example.UmsMenuExample;
import com.paradise.core.model.UmsMenu;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMenuMapper {
    long countByExample(UmsMenuExample example);

    int deleteByExample(UmsMenuExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMenu record);

    int insertSelective(@Param("record") UmsMenu record, @Param("selective") UmsMenu.Column ... selective);

    UmsMenu selectOneByExample(UmsMenuExample example);

    UmsMenu selectOneByExampleSelective(@Param("example") UmsMenuExample example, @Param("selective") UmsMenu.Column ... selective);

    List<UmsMenu> selectByExampleSelective(@Param("example") UmsMenuExample example, @Param("selective") UmsMenu.Column ... selective);

    List<UmsMenu> selectByExample(UmsMenuExample example);

    UmsMenu selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsMenu.Column ... selective);

    UmsMenu selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMenu record, @Param("example") UmsMenuExample example, @Param("selective") UmsMenu.Column ... selective);

    int updateByExample(@Param("record") UmsMenu record, @Param("example") UmsMenuExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsMenu record, @Param("selective") UmsMenu.Column ... selective);

    int updateByPrimaryKey(UmsMenu record);

    int batchInsert(@Param("list") List<UmsMenu> list);

    int batchInsertSelective(@Param("list") List<UmsMenu> list, @Param("selective") UmsMenu.Column ... selective);
}