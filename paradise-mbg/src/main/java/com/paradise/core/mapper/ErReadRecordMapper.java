package com.paradise.core.mapper;

import com.paradise.core.example.ErReadRecordExample;
import com.paradise.core.model.ErReadRecord;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErReadRecordMapper {
    long countByExample(ErReadRecordExample example);

    int deleteByExample(ErReadRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ErReadRecord record);

    int insertSelective(@Param("record") ErReadRecord record, @Param("selective") ErReadRecord.Column ... selective);

    ErReadRecord selectOneByExample(ErReadRecordExample example);

    ErReadRecord selectOneByExampleSelective(@Param("example") ErReadRecordExample example, @Param("selective") ErReadRecord.Column ... selective);

    List<ErReadRecord> selectByExampleSelective(@Param("example") ErReadRecordExample example, @Param("selective") ErReadRecord.Column ... selective);

    List<ErReadRecord> selectByExample(ErReadRecordExample example);

    ErReadRecord selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") ErReadRecord.Column ... selective);

    ErReadRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ErReadRecord record, @Param("example") ErReadRecordExample example, @Param("selective") ErReadRecord.Column ... selective);

    int updateByExample(@Param("record") ErReadRecord record, @Param("example") ErReadRecordExample example);

    int updateByPrimaryKeySelective(@Param("record") ErReadRecord record, @Param("selective") ErReadRecord.Column ... selective);

    int updateByPrimaryKey(ErReadRecord record);

    int batchInsert(@Param("list") List<ErReadRecord> list);

    int batchInsertSelective(@Param("list") List<ErReadRecord> list, @Param("selective") ErReadRecord.Column ... selective);
}