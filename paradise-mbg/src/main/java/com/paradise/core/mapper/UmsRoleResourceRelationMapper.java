package com.paradise.core.mapper;

import com.paradise.core.example.UmsRoleResourceRelationExample;
import com.paradise.core.model.UmsRoleResourceRelation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsRoleResourceRelationMapper {
    long countByExample(UmsRoleResourceRelationExample example);

    int deleteByExample(UmsRoleResourceRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsRoleResourceRelation record);

    int insertSelective(@Param("record") UmsRoleResourceRelation record, @Param("selective") UmsRoleResourceRelation.Column ... selective);

    UmsRoleResourceRelation selectOneByExample(UmsRoleResourceRelationExample example);

    UmsRoleResourceRelation selectOneByExampleSelective(@Param("example") UmsRoleResourceRelationExample example, @Param("selective") UmsRoleResourceRelation.Column ... selective);

    List<UmsRoleResourceRelation> selectByExampleSelective(@Param("example") UmsRoleResourceRelationExample example, @Param("selective") UmsRoleResourceRelation.Column ... selective);

    List<UmsRoleResourceRelation> selectByExample(UmsRoleResourceRelationExample example);

    UmsRoleResourceRelation selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsRoleResourceRelation.Column ... selective);

    UmsRoleResourceRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsRoleResourceRelation record, @Param("example") UmsRoleResourceRelationExample example, @Param("selective") UmsRoleResourceRelation.Column ... selective);

    int updateByExample(@Param("record") UmsRoleResourceRelation record, @Param("example") UmsRoleResourceRelationExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsRoleResourceRelation record, @Param("selective") UmsRoleResourceRelation.Column ... selective);

    int updateByPrimaryKey(UmsRoleResourceRelation record);

    int batchInsert(@Param("list") List<UmsRoleResourceRelation> list);

    int batchInsertSelective(@Param("list") List<UmsRoleResourceRelation> list, @Param("selective") UmsRoleResourceRelation.Column ... selective);
}