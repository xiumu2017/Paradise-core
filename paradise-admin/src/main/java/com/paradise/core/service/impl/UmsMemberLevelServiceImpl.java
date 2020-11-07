package com.paradise.core.service.impl;

import com.paradise.core.example.UmsMemberLevelExample;
import com.paradise.core.mapper.UmsMemberLevelMapper;
import com.paradise.core.model.UmsMemberLevel;
import com.paradise.core.service.UmsMemberLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员等级管理Service实现类
 *
 * @author Paradise
 * @date 2018/4/26
 */
@Service
public class UmsMemberLevelServiceImpl implements UmsMemberLevelService {
    private final UmsMemberLevelMapper memberLevelMapper;

    public UmsMemberLevelServiceImpl(UmsMemberLevelMapper memberLevelMapper) {
        this.memberLevelMapper = memberLevelMapper;
    }

    @Override
    public List<UmsMemberLevel> list(Integer defaultStatus) {
        UmsMemberLevelExample example = new UmsMemberLevelExample();
        example.createCriteria().andDefaultStatusEqualTo(defaultStatus);
        return memberLevelMapper.selectByExample(example);
    }
}
