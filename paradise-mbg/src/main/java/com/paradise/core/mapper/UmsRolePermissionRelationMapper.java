package com.paradise.core.mapper;

import com.paradise.core.example.UmsRolePermissionRelationExample;
import com.paradise.core.model.UmsRolePermissionRelation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsRolePermissionRelationMapper {
    long countByExample(UmsRolePermissionRelationExample example);

    int deleteByExample(UmsRolePermissionRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsRolePermissionRelation record);

    int insertSelective(@Param("record") UmsRolePermissionRelation record, @Param("selective") UmsRolePermissionRelation.Column ... selective);

    UmsRolePermissionRelation selectOneByExample(UmsRolePermissionRelationExample example);

    UmsRolePermissionRelation selectOneByExampleSelective(@Param("example") UmsRolePermissionRelationExample example, @Param("selective") UmsRolePermissionRelation.Column ... selective);

    List<UmsRolePermissionRelation> selectByExampleSelective(@Param("example") UmsRolePermissionRelationExample example, @Param("selective") UmsRolePermissionRelation.Column ... selective);

    List<UmsRolePermissionRelation> selectByExample(UmsRolePermissionRelationExample example);

    UmsRolePermissionRelation selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsRolePermissionRelation.Column ... selective);

    UmsRolePermissionRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsRolePermissionRelation record, @Param("example") UmsRolePermissionRelationExample example, @Param("selective") UmsRolePermissionRelation.Column ... selective);

    int updateByExample(@Param("record") UmsRolePermissionRelation record, @Param("example") UmsRolePermissionRelationExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsRolePermissionRelation record, @Param("selective") UmsRolePermissionRelation.Column ... selective);

    int updateByPrimaryKey(UmsRolePermissionRelation record);

    int batchInsert(@Param("list") List<UmsRolePermissionRelation> list);

    int batchInsertSelective(@Param("list") List<UmsRolePermissionRelation> list, @Param("selective") UmsRolePermissionRelation.Column ... selective);
}