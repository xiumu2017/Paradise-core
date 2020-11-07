package com.paradise.core.mapper;

import com.paradise.core.example.AccountPassExample;
import com.paradise.core.model.AccountPass;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AccountPassMapper {
    long countByExample(AccountPassExample example);

    int deleteByExample(AccountPassExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AccountPass record);

    int insertSelective(@Param("record") AccountPass record, @Param("selective") AccountPass.Column ... selective);

    AccountPass selectOneByExample(AccountPassExample example);

    AccountPass selectOneByExampleSelective(@Param("example") AccountPassExample example, @Param("selective") AccountPass.Column ... selective);

    List<AccountPass> selectByExampleSelective(@Param("example") AccountPassExample example, @Param("selective") AccountPass.Column ... selective);

    List<AccountPass> selectByExample(AccountPassExample example);

    AccountPass selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") AccountPass.Column ... selective);

    AccountPass selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AccountPass record, @Param("example") AccountPassExample example, @Param("selective") AccountPass.Column ... selective);

    int updateByExample(@Param("record") AccountPass record, @Param("example") AccountPassExample example);

    int updateByPrimaryKeySelective(@Param("record") AccountPass record, @Param("selective") AccountPass.Column ... selective);

    int updateByPrimaryKey(AccountPass record);

    int batchInsert(@Param("list") List<AccountPass> list);

    int batchInsertSelective(@Param("list") List<AccountPass> list, @Param("selective") AccountPass.Column ... selective);
}