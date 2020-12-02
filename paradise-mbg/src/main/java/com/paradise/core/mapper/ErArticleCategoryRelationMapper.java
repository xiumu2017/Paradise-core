package com.paradise.core.mapper;

import com.paradise.core.example.ErArticleCategoryRelationExample;
import com.paradise.core.model.ErArticleCategoryRelation;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErArticleCategoryRelationMapper {
    long countByExample(ErArticleCategoryRelationExample example);

    int deleteByExample(ErArticleCategoryRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ErArticleCategoryRelation record);

    int insertSelective(@Param("record") ErArticleCategoryRelation record, @Param("selective") ErArticleCategoryRelation.Column ... selective);

    ErArticleCategoryRelation selectOneByExample(ErArticleCategoryRelationExample example);

    ErArticleCategoryRelation selectOneByExampleSelective(@Param("example") ErArticleCategoryRelationExample example, @Param("selective") ErArticleCategoryRelation.Column ... selective);

    List<ErArticleCategoryRelation> selectByExampleSelective(@Param("example") ErArticleCategoryRelationExample example, @Param("selective") ErArticleCategoryRelation.Column ... selective);

    List<ErArticleCategoryRelation> selectByExample(ErArticleCategoryRelationExample example);

    ErArticleCategoryRelation selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ErArticleCategoryRelation.Column ... selective);

    ErArticleCategoryRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ErArticleCategoryRelation record, @Param("example") ErArticleCategoryRelationExample example, @Param("selective") ErArticleCategoryRelation.Column ... selective);

    int updateByExample(@Param("record") ErArticleCategoryRelation record, @Param("example") ErArticleCategoryRelationExample example);

    int updateByPrimaryKeySelective(@Param("record") ErArticleCategoryRelation record, @Param("selective") ErArticleCategoryRelation.Column ... selective);

    int updateByPrimaryKey(ErArticleCategoryRelation record);

    int batchInsert(@Param("list") List<ErArticleCategoryRelation> list);

    int batchInsertSelective(@Param("list") List<ErArticleCategoryRelation> list, @Param("selective") ErArticleCategoryRelation.Column ... selective);
}