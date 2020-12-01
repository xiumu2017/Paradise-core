package com.paradise.core.mapper;

import com.paradise.core.example.ErArticleExample;
import com.paradise.core.model.ErArticle;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErArticleMapper {
    long countByExample(ErArticleExample example);

    int deleteByExample(ErArticleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ErArticle record);

    int insertSelective(@Param("record") ErArticle record, @Param("selective") ErArticle.Column ... selective);

    ErArticle selectOneByExample(ErArticleExample example);

    ErArticle selectOneByExampleSelective(@Param("example") ErArticleExample example, @Param("selective") ErArticle.Column ... selective);

    ErArticle selectOneByExampleWithBLOBs(ErArticleExample example);

    List<ErArticle> selectByExampleSelective(@Param("example") ErArticleExample example, @Param("selective") ErArticle.Column ... selective);

    List<ErArticle> selectByExampleWithBLOBs(ErArticleExample example);

    List<ErArticle> selectByExample(ErArticleExample example);

    ErArticle selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ErArticle.Column ... selective);

    ErArticle selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ErArticle record, @Param("example") ErArticleExample example, @Param("selective") ErArticle.Column ... selective);

    int updateByExampleWithBLOBs(@Param("record") ErArticle record, @Param("example") ErArticleExample example);

    int updateByExample(@Param("record") ErArticle record, @Param("example") ErArticleExample example);

    int updateByPrimaryKeySelective(@Param("record") ErArticle record, @Param("selective") ErArticle.Column ... selective);

    int updateByPrimaryKeyWithBLOBs(ErArticle record);

    int updateByPrimaryKey(ErArticle record);

    int batchInsert(@Param("list") List<ErArticle> list);

    int batchInsertSelective(@Param("list") List<ErArticle> list, @Param("selective") ErArticle.Column ... selective);
}