package com.paradise.core.service.impl;

import com.github.pagehelper.PageHelper;
import com.paradise.core.example.UmsMemberExample;
import com.paradise.core.mapper.UmsMemberMapper;
import com.paradise.core.model.UmsMember;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Paradise
 */
@Service
@AllArgsConstructor
public class UmsMemberService {
    private final UmsMemberMapper umsMemberMapper;

    public List<UmsMember> list(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return umsMemberMapper.selectByExample(new UmsMemberExample());
    }

}
