package com.paradise.core.mapper;

import com.paradise.core.example.UmsMemberExample;
import com.paradise.core.model.UmsMember;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberMapper {
    long countByExample(UmsMemberExample example);

    int deleteByExample(UmsMemberExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMember record);

    int insertSelective(@Param("record") UmsMember record, @Param("selective") UmsMember.Column ... selective);

    UmsMember selectOneByExample(UmsMemberExample example);

    UmsMember selectOneByExampleSelective(@Param("example") UmsMemberExample example, @Param("selective") UmsMember.Column ... selective);

    List<UmsMember> selectByExampleSelective(@Param("example") UmsMemberExample example, @Param("selective") UmsMember.Column ... selective);

    List<UmsMember> selectByExample(UmsMemberExample example);

    UmsMember selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsMember.Column ... selective);

    UmsMember selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMember record, @Param("example") UmsMemberExample example, @Param("selective") UmsMember.Column ... selective);

    int updateByExample(@Param("record") UmsMember record, @Param("example") UmsMemberExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsMember record, @Param("selective") UmsMember.Column ... selective);

    int updateByPrimaryKey(UmsMember record);

    int batchInsert(@Param("list") List<UmsMember> list);

    int batchInsertSelective(@Param("list") List<UmsMember> list, @Param("selective") UmsMember.Column ... selective);
}