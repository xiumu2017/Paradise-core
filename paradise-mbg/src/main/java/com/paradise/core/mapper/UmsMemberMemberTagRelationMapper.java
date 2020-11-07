package com.paradise.core.mapper;

import com.paradise.core.example.UmsMemberMemberTagRelationExample;
import com.paradise.core.model.UmsMemberMemberTagRelation;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface UmsMemberMemberTagRelationMapper {
    long countByExample(UmsMemberMemberTagRelationExample example);

    int deleteByExample(UmsMemberMemberTagRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsMemberMemberTagRelation record);

    int insertSelective(@Param("record") UmsMemberMemberTagRelation record, @Param("selective") UmsMemberMemberTagRelation.Column ... selective);

    UmsMemberMemberTagRelation selectOneByExample(UmsMemberMemberTagRelationExample example);

    UmsMemberMemberTagRelation selectOneByExampleSelective(@Param("example") UmsMemberMemberTagRelationExample example, @Param("selective") UmsMemberMemberTagRelation.Column ... selective);

    List<UmsMemberMemberTagRelation> selectByExampleSelective(@Param("example") UmsMemberMemberTagRelationExample example, @Param("selective") UmsMemberMemberTagRelation.Column ... selective);

    List<UmsMemberMemberTagRelation> selectByExample(UmsMemberMemberTagRelationExample example);

    UmsMemberMemberTagRelation selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsMemberMemberTagRelation.Column ... selective);

    UmsMemberMemberTagRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsMemberMemberTagRelation record, @Param("example") UmsMemberMemberTagRelationExample example, @Param("selective") UmsMemberMemberTagRelation.Column ... selective);

    int updateByExample(@Param("record") UmsMemberMemberTagRelation record, @Param("example") UmsMemberMemberTagRelationExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsMemberMemberTagRelation record, @Param("selective") UmsMemberMemberTagRelation.Column ... selective);

    int updateByPrimaryKey(UmsMemberMemberTagRelation record);

    int batchInsert(@Param("list") List<UmsMemberMemberTagRelation> list);

    int batchInsertSelective(@Param("list") List<UmsMemberMemberTagRelation> list, @Param("selective") UmsMemberMemberTagRelation.Column ... selective);
}