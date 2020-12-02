package com.paradise.core.mapper;

import com.paradise.core.example.UmsMemberLoginLogExample;
import com.paradise.core.model.UmsMemberLoginLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsMemberLoginLogMapper {
    long countByExample(UmsMemberLoginLogExample example);

    int deleteByExample(UmsMemberLoginLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberLoginLog record);

    int insertSelective(@Param("record") UmsMemberLoginLog record, @Param("selective") UmsMemberLoginLog.Column ... selective);

    UmsMemberLoginLog selectOneByExample(UmsMemberLoginLogExample example);

    UmsMemberLoginLog selectOneByExampleSelective(@Param("example") UmsMemberLoginLogExample example, @Param("selective") UmsMemberLoginLog.Column ... selective);

    List<UmsMemberLoginLog> selectByExampleSelective(@Param("example") UmsMemberLoginLogExample example, @Param("selective") UmsMemberLoginLog.Column ... selective);

    List<UmsMemberLoginLog> selectByExample(UmsMemberLoginLogExample example);

    UmsMemberLoginLog selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsMemberLoginLog.Column ... selective);

    UmsMemberLoginLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberLoginLog record, @Param("example") UmsMemberLoginLogExample example, @Param("selective") UmsMemberLoginLog.Column ... selective);

    int updateByExample(@Param("record") UmsMemberLoginLog record, @Param("example") UmsMemberLoginLogExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsMemberLoginLog record, @Param("selective") UmsMemberLoginLog.Column ... selective);

    int updateByPrimaryKey(UmsMemberLoginLog record);

    int batchInsert(@Param("list") List<UmsMemberLoginLog> list);

    int batchInsertSelective(@Param("list") List<UmsMemberLoginLog> list, @Param("selective") UmsMemberLoginLog.Column ... selective);
}