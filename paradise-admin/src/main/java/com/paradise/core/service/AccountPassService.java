package com.paradise.core.service;

import com.github.pagehelper.PageHelper;
import com.paradise.core.example.AccountPassExample;
import com.paradise.core.mapper.AccountPassMapper;
import com.paradise.core.model.AccountPass;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Service
 *
 * @author Paradise
 */
@Slf4j
@Service
@AllArgsConstructor
public class AccountPassService {
    private final AccountPassMapper accountPassMapper;

    public int deleteByPrimaryKey(Long id) {
        return this.accountPassMapper.deleteByPrimaryKey(id);
    }

    public int insert(AccountPass record) {
        return this.accountPassMapper.insertSelective(record);
    }

    public AccountPass selectByPrimaryKey(Long id) {
        return this.accountPassMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKey(AccountPass record) {
        return this.accountPassMapper.updateByPrimaryKey(record);
    }

    public List<AccountPass> selectByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return this.accountPassMapper.selectByExample(new AccountPassExample());
    }
}