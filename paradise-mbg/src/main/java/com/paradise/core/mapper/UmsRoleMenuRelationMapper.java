package com.paradise.core.mapper;

import com.paradise.core.example.UmsRoleMenuRelationExample;
import com.paradise.core.model.UmsRoleMenuRelation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsRoleMenuRelationMapper {
    long countByExample(UmsRoleMenuRelationExample example);

    int deleteByExample(UmsRoleMenuRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsRoleMenuRelation record);

    int insertSelective(@Param("record") UmsRoleMenuRelation record, @Param("selective") UmsRoleMenuRelation.Column ... selective);

    UmsRoleMenuRelation selectOneByExample(UmsRoleMenuRelationExample example);

    UmsRoleMenuRelation selectOneByExampleSelective(@Param("example") UmsRoleMenuRelationExample example, @Param("selective") UmsRoleMenuRelation.Column ... selective);

    List<UmsRoleMenuRelation> selectByExampleSelective(@Param("example") UmsRoleMenuRelationExample example, @Param("selective") UmsRoleMenuRelation.Column ... selective);

    List<UmsRoleMenuRelation> selectByExample(UmsRoleMenuRelationExample example);

    UmsRoleMenuRelation selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsRoleMenuRelation.Column ... selective);

    UmsRoleMenuRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsRoleMenuRelation record, @Param("example") UmsRoleMenuRelationExample example, @Param("selective") UmsRoleMenuRelation.Column ... selective);

    int updateByExample(@Param("record") UmsRoleMenuRelation record, @Param("example") UmsRoleMenuRelationExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsRoleMenuRelation record, @Param("selective") UmsRoleMenuRelation.Column ... selective);

    int updateByPrimaryKey(UmsRoleMenuRelation record);

    int batchInsert(@Param("list") List<UmsRoleMenuRelation> list);

    int batchInsertSelective(@Param("list") List<UmsRoleMenuRelation> list, @Param("selective") UmsRoleMenuRelation.Column ... selective);
}