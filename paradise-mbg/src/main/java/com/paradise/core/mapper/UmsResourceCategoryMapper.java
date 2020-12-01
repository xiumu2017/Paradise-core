package com.paradise.core.mapper;

import com.paradise.core.example.UmsResourceCategoryExample;
import com.paradise.core.model.UmsResourceCategory;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsResourceCategoryMapper {
    long countByExample(UmsResourceCategoryExample example);

    int deleteByExample(UmsResourceCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsResourceCategory record);

    int insertSelective(@Param("record") UmsResourceCategory record, @Param("selective") UmsResourceCategory.Column ... selective);

    UmsResourceCategory selectOneByExample(UmsResourceCategoryExample example);

    UmsResourceCategory selectOneByExampleSelective(@Param("example") UmsResourceCategoryExample example, @Param("selective") UmsResourceCategory.Column ... selective);

    List<UmsResourceCategory> selectByExampleSelective(@Param("example") UmsResourceCategoryExample example, @Param("selective") UmsResourceCategory.Column ... selective);

    List<UmsResourceCategory> selectByExample(UmsResourceCategoryExample example);

    UmsResourceCategory selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsResourceCategory.Column ... selective);

    UmsResourceCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsResourceCategory record, @Param("example") UmsResourceCategoryExample example, @Param("selective") UmsResourceCategory.Column ... selective);

    int updateByExample(@Param("record") UmsResourceCategory record, @Param("example") UmsResourceCategoryExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsResourceCategory record, @Param("selective") UmsResourceCategory.Column ... selective);

    int updateByPrimaryKey(UmsResourceCategory record);

    int batchInsert(@Param("list") List<UmsResourceCategory> list);

    int batchInsertSelective(@Param("list") List<UmsResourceCategory> list, @Param("selective") UmsResourceCategory.Column ... selective);
}