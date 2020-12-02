package com.paradise.core.mapper;

import com.paradise.core.example.UmsRoleExample;
import com.paradise.core.model.UmsRole;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsRoleMapper {
    long countByExample(UmsRoleExample example);

    int deleteByExample(UmsRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsRole record);

    int insertSelective(@Param("record") UmsRole record, @Param("selective") UmsRole.Column ... selective);

    UmsRole selectOneByExample(UmsRoleExample example);

    UmsRole selectOneByExampleSelective(@Param("example") UmsRoleExample example, @Param("selective") UmsRole.Column ... selective);

    List<UmsRole> selectByExampleSelective(@Param("example") UmsRoleExample example, @Param("selective") UmsRole.Column ... selective);

    List<UmsRole> selectByExample(UmsRoleExample example);

    UmsRole selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsRole.Column ... selective);

    UmsRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsRole record, @Param("example") UmsRoleExample example, @Param("selective") UmsRole.Column ... selective);

    int updateByExample(@Param("record") UmsRole record, @Param("example") UmsRoleExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsRole record, @Param("selective") UmsRole.Column ... selective);

    int updateByPrimaryKey(UmsRole record);

    int batchInsert(@Param("list") List<UmsRole> list);

    int batchInsertSelective(@Param("list") List<UmsRole> list, @Param("selective") UmsRole.Column ... selective);
}