package com.paradise.core.mapper;

import com.paradise.core.example.UmsAdminLoginLogExample;
import com.paradise.core.model.UmsAdminLoginLog;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminLoginLogMapper {
    long countByExample(UmsAdminLoginLogExample example);

    int deleteByExample(UmsAdminLoginLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminLoginLog record);

    int insertSelective(@Param("record") UmsAdminLoginLog record, @Param("selective") UmsAdminLoginLog.Column ... selective);

    UmsAdminLoginLog selectOneByExample(UmsAdminLoginLogExample example);

    UmsAdminLoginLog selectOneByExampleSelective(@Param("example") UmsAdminLoginLogExample example, @Param("selective") UmsAdminLoginLog.Column ... selective);

    List<UmsAdminLoginLog> selectByExampleSelective(@Param("example") UmsAdminLoginLogExample example, @Param("selective") UmsAdminLoginLog.Column ... selective);

    List<UmsAdminLoginLog> selectByExample(UmsAdminLoginLogExample example);

    UmsAdminLoginLog selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsAdminLoginLog.Column ... selective);

    UmsAdminLoginLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAdminLoginLog record, @Param("example") UmsAdminLoginLogExample example, @Param("selective") UmsAdminLoginLog.Column ... selective);

    int updateByExample(@Param("record") UmsAdminLoginLog record, @Param("example") UmsAdminLoginLogExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsAdminLoginLog record, @Param("selective") UmsAdminLoginLog.Column ... selective);

    int updateByPrimaryKey(UmsAdminLoginLog record);

    int batchInsert(@Param("list") List<UmsAdminLoginLog> list);

    int batchInsertSelective(@Param("list") List<UmsAdminLoginLog> list, @Param("selective") UmsAdminLoginLog.Column ... selective);
}