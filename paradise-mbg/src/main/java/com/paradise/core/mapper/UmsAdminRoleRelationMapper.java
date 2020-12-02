package com.paradise.core.mapper;

import com.paradise.core.example.UmsAdminRoleRelationExample;
import com.paradise.core.model.UmsAdminRoleRelation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsAdminRoleRelationMapper {
    long countByExample(UmsAdminRoleRelationExample example);

    int deleteByExample(UmsAdminRoleRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdminRoleRelation record);

    int insertSelective(@Param("record") UmsAdminRoleRelation record, @Param("selective") UmsAdminRoleRelation.Column ... selective);

    UmsAdminRoleRelation selectOneByExample(UmsAdminRoleRelationExample example);

    UmsAdminRoleRelation selectOneByExampleSelective(@Param("example") UmsAdminRoleRelationExample example, @Param("selective") UmsAdminRoleRelation.Column ... selective);

    List<UmsAdminRoleRelation> selectByExampleSelective(@Param("example") UmsAdminRoleRelationExample example, @Param("selective") UmsAdminRoleRelation.Column ... selective);

    List<UmsAdminRoleRelation> selectByExample(UmsAdminRoleRelationExample example);

    UmsAdminRoleRelation selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsAdminRoleRelation.Column ... selective);

    UmsAdminRoleRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAdminRoleRelation record, @Param("example") UmsAdminRoleRelationExample example, @Param("selective") UmsAdminRoleRelation.Column ... selective);

    int updateByExample(@Param("record") UmsAdminRoleRelation record, @Param("example") UmsAdminRoleRelationExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsAdminRoleRelation record, @Param("selective") UmsAdminRoleRelation.Column ... selective);

    int updateByPrimaryKey(UmsAdminRoleRelation record);

    int batchInsert(@Param("list") List<UmsAdminRoleRelation> list);

    int batchInsertSelective(@Param("list") List<UmsAdminRoleRelation> list, @Param("selective") UmsAdminRoleRelation.Column ... selective);
}