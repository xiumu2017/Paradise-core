package com.paradise.core.mapper;

import com.paradise.core.example.UmsAdminExample;
import com.paradise.core.model.UmsAdmin;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminMapper {
    long countByExample(UmsAdminExample example);

    int deleteByExample(UmsAdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdmin record);

    int insertSelective(@Param("record") UmsAdmin record, @Param("selective") UmsAdmin.Column ... selective);

    UmsAdmin selectOneByExample(UmsAdminExample example);

    UmsAdmin selectOneByExampleSelective(@Param("example") UmsAdminExample example, @Param("selective") UmsAdmin.Column ... selective);

    List<UmsAdmin> selectByExampleSelective(@Param("example") UmsAdminExample example, @Param("selective") UmsAdmin.Column ... selective);

    List<UmsAdmin> selectByExample(UmsAdminExample example);

    UmsAdmin selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsAdmin.Column ... selective);

    UmsAdmin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAdmin record, @Param("example") UmsAdminExample example, @Param("selective") UmsAdmin.Column ... selective);

    int updateByExample(@Param("record") UmsAdmin record, @Param("example") UmsAdminExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsAdmin record, @Param("selective") UmsAdmin.Column ... selective);

    int updateByPrimaryKey(UmsAdmin record);

    int batchInsert(@Param("list") List<UmsAdmin> list);

    int batchInsertSelective(@Param("list") List<UmsAdmin> list, @Param("selective") UmsAdmin.Column ... selective);
}