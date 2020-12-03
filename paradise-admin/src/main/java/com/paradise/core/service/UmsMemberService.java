package com.paradise.core.service;

import com.github.pagehelper.PageHelper;
import com.paradise.core.common.utils.DateUtil;
import com.paradise.core.example.UmsMemberExample;
import com.paradise.core.mapper.UmsMemberMapper;
import com.paradise.core.model.UmsMember;
import com.paradise.core.query.UmsMemberQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Paradise
 */
@Slf4j
@Service
@AllArgsConstructor
public class UmsMemberService {
    private final UmsMemberMapper umsMemberMapper;

    public int deleteByPrimaryKey(Long id) {
        return this.umsMemberMapper.deleteByPrimaryKey(id);
    }

    public int insert(UmsMember record) {
        return this.umsMemberMapper.insert(record);
    }

    public UmsMember selectByPrimaryKey(Long id) {
        return this.umsMemberMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKey(UmsMember record) {
        return this.umsMemberMapper.updateByPrimaryKey(record);
    }

    public List<UmsMember> selectByPage(UmsMemberQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return this.umsMemberMapper.selectByExample(new UmsMemberExample()
                .createCriteria()
                .when(StringUtils.hasText(query.getNickName()), criteria -> criteria.andNicknameLike("%" + query.getNickName() + "%"))
                .when(query.getEnable() != null, criteria -> criteria.andStatusEqualTo(query.getEnable()))
                .example().orderBy(UmsMember.Column.createTime.desc()));
    }

    public Long dailyCount() {
        return umsMemberMapper.countByExample(new UmsMemberExample().createCriteria()
                .andCreateTimeBetween(DateUtil.getStartDateOfToday(), DateUtil.getEndDateOfToday()).example());
    }
}