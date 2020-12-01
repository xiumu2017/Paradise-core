package com.paradise.core.service;

import com.github.pagehelper.PageHelper;
import com.paradise.core.body.ErCategoryBody;
import com.paradise.core.example.ErCategoryExample;
import com.paradise.core.mapper.ErCategoryMapper;
import com.paradise.core.model.ErCategory;
import com.paradise.core.query.ErCategoryQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author Paradise
 */
@Slf4j
@Service
@AllArgsConstructor
public class ErCategoryService {
    private final ErCategoryMapper erCategoryMapper;

    public int deleteByPrimaryKey(Long id) {
        return this.erCategoryMapper.updateByPrimaryKeySelective(ErCategory.builder().id(id).deleted(1).build(),
                ErCategory.Column.deleted);
    }

    public int insert(ErCategoryBody record) {
        ErCategory erCategory = new ErCategory();
        BeanUtils.copyProperties(record, erCategory);
        erCategory.setCreateAt(new Date());
        erCategory.setUpdateAt(new Date());
        return this.erCategoryMapper.insertSelective(erCategory);
    }

    public ErCategory selectByPrimaryKey(Long id) {
        return this.erCategoryMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKey(ErCategoryBody record) {
        ErCategory erCategory = new ErCategory();
        BeanUtils.copyProperties(record, erCategory);
        erCategory.setUpdateAt(new Date());
        return this.erCategoryMapper.updateByPrimaryKeySelective(erCategory);
    }

    public List<ErCategory> selectByPage(ErCategoryQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return this.erCategoryMapper.selectByExample(new ErCategoryExample()
                .createCriteria().andDeletedEqualTo(0)
                .when(query.getEnable() != null, criteria -> criteria.andEnableEqualTo(query.getEnable()))
                .when(StringUtils.hasText(query.getName()), criteria -> criteria.andNameLike("%" + query.getName() + "%"))
                .example()
                .orderBy(ErCategory.Column.createAt.desc()));
    }
}