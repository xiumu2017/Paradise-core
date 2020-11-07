package com.paradise.core.mapper;

import com.paradise.core.example.UmsMemberLevelExample;
import com.paradise.core.model.UmsMemberLevel;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UmsMemberLevelMapper {
    long countByExample(UmsMemberLevelExample example);

    int deleteByExample(UmsMemberLevelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberLevel record);

    int insertSelective(@Param("record") UmsMemberLevel record, @Param("selective") UmsMemberLevel.Column ... selective);

    UmsMemberLevel selectOneByExample(UmsMemberLevelExample example);

    UmsMemberLevel selectOneByExampleSelective(@Param("example") UmsMemberLevelExample example, @Param("selective") UmsMemberLevel.Column ... selective);

    List<UmsMemberLevel> selectByExampleSelective(@Param("example") UmsMemberLevelExample example, @Param("selective") UmsMemberLevel.Column ... selective);

    List<UmsMemberLevel> selectByExample(UmsMemberLevelExample example);

    UmsMemberLevel selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsMemberLevel.Column ... selective);

    UmsMemberLevel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberLevel record, @Param("example") UmsMemberLevelExample example, @Param("selective") UmsMemberLevel.Column ... selective);

    int updateByExample(@Param("record") UmsMemberLevel record, @Param("example") UmsMemberLevelExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsMemberLevel record, @Param("selective") UmsMemberLevel.Column ... selective);

    int updateByPrimaryKey(UmsMemberLevel record);

    int batchInsert(@Param("list") List<UmsMemberLevel> list);

    int batchInsertSelective(@Param("list") List<UmsMemberLevel> list, @Param("selective") UmsMemberLevel.Column ... selective);
}