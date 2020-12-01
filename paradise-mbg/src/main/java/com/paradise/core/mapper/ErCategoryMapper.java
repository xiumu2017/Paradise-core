package com.paradise.core.mapper;

import com.paradise.core.example.ErCategoryExample;
import com.paradise.core.model.ErCategory;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErCategoryMapper {
    long countByExample(ErCategoryExample example);

    int deleteByExample(ErCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ErCategory record);

    int insertSelective(@Param("record") ErCategory record, @Param("selective") ErCategory.Column ... selective);

    ErCategory selectOneByExample(ErCategoryExample example);

    ErCategory selectOneByExampleSelective(@Param("example") ErCategoryExample example, @Param("selective") ErCategory.Column ... selective);

    List<ErCategory> selectByExampleSelective(@Param("example") ErCategoryExample example, @Param("selective") ErCategory.Column ... selective);

    List<ErCategory> selectByExample(ErCategoryExample example);

    ErCategory selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ErCategory.Column ... selective);

    ErCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ErCategory record, @Param("example") ErCategoryExample example, @Param("selective") ErCategory.Column ... selective);

    int updateByExample(@Param("record") ErCategory record, @Param("example") ErCategoryExample example);

    int updateByPrimaryKeySelective(@Param("record") ErCategory record, @Param("selective") ErCategory.Column ... selective);

    int updateByPrimaryKey(ErCategory record);

    int batchInsert(@Param("list") List<ErCategory> list);

    int batchInsertSelective(@Param("list") List<ErCategory> list, @Param("selective") ErCategory.Column ... selective);
}