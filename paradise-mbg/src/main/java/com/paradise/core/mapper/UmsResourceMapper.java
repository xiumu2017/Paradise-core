package com.paradise.core.mapper;

import com.paradise.core.example.UmsResourceExample;
import com.paradise.core.model.UmsResource;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UmsResourceMapper {
    long countByExample(UmsResourceExample example);

    int deleteByExample(UmsResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsResource record);

    int insertSelective(@Param("record") UmsResource record, @Param("selective") UmsResource.Column ... selective);

    UmsResource selectOneByExample(UmsResourceExample example);

    UmsResource selectOneByExampleSelective(@Param("example") UmsResourceExample example, @Param("selective") UmsResource.Column ... selective);

    List<UmsResource> selectByExampleSelective(@Param("example") UmsResourceExample example, @Param("selective") UmsResource.Column ... selective);

    List<UmsResource> selectByExample(UmsResourceExample example);

    UmsResource selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") UmsResource.Column ... selective);

    UmsResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsResource record, @Param("example") UmsResourceExample example, @Param("selective") UmsResource.Column ... selective);

    int updateByExample(@Param("record") UmsResource record, @Param("example") UmsResourceExample example);

    int updateByPrimaryKeySelective(@Param("record") UmsResource record, @Param("selective") UmsResource.Column ... selective);

    int updateByPrimaryKey(UmsResource record);

    int batchInsert(@Param("list") List<UmsResource> list);

    int batchInsertSelective(@Param("list") List<UmsResource> list, @Param("selective") UmsResource.Column ... selective);
}