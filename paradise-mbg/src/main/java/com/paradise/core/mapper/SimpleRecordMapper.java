package com.paradise.core.mapper;

import com.paradise.core.example.SimpleRecordExample;
import com.paradise.core.model.SimpleRecord;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface SimpleRecordMapper {
    long countByExample(SimpleRecordExample example);

    int deleteByExample(SimpleRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(SimpleRecord record);

    int insertSelective(@Param("record") SimpleRecord record, @Param("selective") SimpleRecord.Column ... selective);

    SimpleRecord selectOneByExample(SimpleRecordExample example);

    SimpleRecord selectOneByExampleSelective(@Param("example") SimpleRecordExample example, @Param("selective") SimpleRecord.Column ... selective);

    List<SimpleRecord> selectByExampleSelective(@Param("example") SimpleRecordExample example, @Param("selective") SimpleRecord.Column ... selective);

    List<SimpleRecord> selectByExample(SimpleRecordExample example);

    SimpleRecord selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") SimpleRecord.Column ... selective);

    SimpleRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") SimpleRecord record, @Param("example") SimpleRecordExample example, @Param("selective") SimpleRecord.Column ... selective);

    int updateByExample(@Param("record") SimpleRecord record, @Param("example") SimpleRecordExample example);

    int updateByPrimaryKeySelective(@Param("record") SimpleRecord record, @Param("selective") SimpleRecord.Column ... selective);

    int updateByPrimaryKey(SimpleRecord record);

    int batchInsert(@Param("list") List<SimpleRecord> list);

    int batchInsertSelective(@Param("list") List<SimpleRecord> list, @Param("selective") SimpleRecord.Column ... selective);
}