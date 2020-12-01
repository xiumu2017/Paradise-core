package com.paradise.core.mapper;

import com.paradise.core.example.UmsPermissionExample;
import com.paradise.core.model.UmsPermission;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsPermissionMapper {
    long countByExample(UmsPermissionExample example);

    int deleteByExample(UmsPermissionExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsPermission record);

    int insertSelective(@Param("record") UmsPermission record, @Param("selective") UmsPermission.Column ... selective);

    UmsPermission selectOneByExample(UmsPermissionExample example);

    UmsPermission selectOneByExampleSelective(@Param("example") UmsPermissionExample example, @Param("selective") UmsPermission.Column ... selective);

    List<UmsPermission> selectByExampleSelective(@Param("example") UmsPermissionExample example, @Param("selective") UmsPermission.Column ... selective);

    List<UmsPermission> selectByExample(UmsPermissionExample example);

    UmsPermission selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsPermission.Column ... selective);

    UmsPermission selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsPermission record, @Param("example") UmsPermissionExample example, @Param("selective") UmsPermission.Column ... selective);

    int updateByExample(@Param("record") UmsPermission record, @Param("example") UmsPermissionExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsPermission record, @Param("selective") UmsPermission.Column ... selective);

    int updateByPrimaryKey(UmsPermission record);

    int batchInsert(@Param("list") List<UmsPermission> list);

    int batchInsertSelective(@Param("list") List<UmsPermission> list, @Param("selective") UmsPermission.Column ... selective);
}