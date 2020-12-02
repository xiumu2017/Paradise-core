package com.paradise.core.mapper;

import com.paradise.core.example.UmsAdminPermissionRelationExample;
import com.paradise.core.model.UmsAdminPermissionRelation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminPermissionRelationMapper {
    long countByExample(UmsAdminPermissionRelationExample example);

    int deleteByExample(UmsAdminPermissionRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminPermissionRelation record);

    int insertSelective(@Param("record") UmsAdminPermissionRelation record, @Param("selective") UmsAdminPermissionRelation.Column ... selective);

    UmsAdminPermissionRelation selectOneByExample(UmsAdminPermissionRelationExample example);

    UmsAdminPermissionRelation selectOneByExampleSelective(@Param("example") UmsAdminPermissionRelationExample example, @Param("selective") UmsAdminPermissionRelation.Column ... selective);

    List<UmsAdminPermissionRelation> selectByExampleSelective(@Param("example") UmsAdminPermissionRelationExample example, @Param("selective") UmsAdminPermissionRelation.Column ... selective);

    List<UmsAdminPermissionRelation> selectByExample(UmsAdminPermissionRelationExample example);

    UmsAdminPermissionRelation selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsAdminPermissionRelation.Column ... selective);

    UmsAdminPermissionRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAdminPermissionRelation record, @Param("example") UmsAdminPermissionRelationExample example, @Param("selective") UmsAdminPermissionRelation.Column ... selective);

    int updateByExample(@Param("record") UmsAdminPermissionRelation record, @Param("example") UmsAdminPermissionRelationExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsAdminPermissionRelation record, @Param("selective") UmsAdminPermissionRelation.Column ... selective);

    int updateByPrimaryKey(UmsAdminPermissionRelation record);

    int batchInsert(@Param("list") List<UmsAdminPermissionRelation> list);

    int batchInsertSelective(@Param("list") List<UmsAdminPermissionRelation> list, @Param("selective") UmsAdminPermissionRelation.Column ... selective);
}