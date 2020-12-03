package com.paradise.core.service;

import com.github.pagehelper.PageHelper;
import com.paradise.core.body.ErArticleBody;
import com.paradise.core.common.utils.DateUtil;
import com.paradise.core.dao.ErArticleDao;
import com.paradise.core.dto.detail.ErArticleDetail;
import com.paradise.core.example.ErArticleCategoryRelationExample;
import com.paradise.core.example.ErArticleExample;
import com.paradise.core.mapper.ErArticleCategoryRelationMapper;
import com.paradise.core.mapper.ErArticleMapper;
import com.paradise.core.model.ErArticle;
import com.paradise.core.model.ErArticleCategoryRelation;
import com.paradise.core.query.ErArticleQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Paradise
 */
@Slf4j
@Service
@AllArgsConstructor
public class ErArticleService {
    private final ErArticleMapper erArticleMapper;
    private final ErArticleDao articleDao;
    private final ErArticleCategoryRelationMapper relationMapper;

    public int deleteByPrimaryKey(Long id) {
        return this.erArticleMapper.updateByPrimaryKeySelective(ErArticle.builder().id(id).deleted(1).build());
    }

    public int insert(ErArticleBody record) {
        ErArticle article = new ErArticle();
        BeanUtils.copyProperties(record, article);
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        if (article.getEnable() == 1) {
            article.setPublishTime(new Date());
        }
        this.erArticleMapper.insertSelective(article);
        // 关联分类
        if (article.getId() != null) {
            this.createRelation(record, article.getId());
            return 1;
        }
        return 0;
    }

    public ErArticle selectByPrimaryKey(Long id) {
        List<Long> categoryIds = new ArrayList<>();
        List<ErArticleCategoryRelation> relationList = relationMapper.selectByExample(
                new ErArticleCategoryRelationExample().createCriteria().andArticleIdEqualTo(id).example());
        if (!CollectionUtils.isEmpty(relationList)) {
            categoryIds.addAll(relationList.stream().map(ErArticleCategoryRelation::getCategoryId).collect(Collectors.toList()));
        }
        ErArticle article = this.erArticleMapper.selectByPrimaryKey(id);
        ErArticleDetail detail = new ErArticleDetail();
        BeanUtils.copyProperties(article, detail);
        detail.setCategoryIds(categoryIds);
        return detail;
    }

    public int updateByPrimaryKey(ErArticleBody record) {
        ErArticle article = new ErArticle();
        BeanUtils.copyProperties(record, article);
        article.setUpdateTime(new Date());
        // 删除原有分类关联
        relationMapper.deleteByExample(new ErArticleCategoryRelationExample().createCriteria()
                .andArticleIdEqualTo(record.getId()).example());
        // 关联分类
        this.createRelation(record, article.getId());
        return this.erArticleMapper.updateByPrimaryKeySelective(article);
    }

    private void createRelation(ErArticleBody record, Long id) {
        if (!CollectionUtils.isEmpty(record.getCategoryIds())) {
            List<ErArticleCategoryRelation> relationList = new ArrayList<>();
            record.getCategoryIds().forEach(item -> relationList.add(ErArticleCategoryRelation.builder()
                    .articleId(id).categoryId(item).build()));
            relationMapper.batchInsert(relationList);
        }
    }

    public List<ErArticle> selectByPage(ErArticleQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return this.articleDao.selectByPage(query);
    }

    public int changeStatus(Long id, Integer enable) {
        return erArticleMapper.updateByPrimaryKeySelective(ErArticle.builder().id(id).enable(enable).build());
    }

    public Long dailyPublishCount() {
        return erArticleMapper.countByExample(
                new ErArticleExample().createCriteria()
                        .andEnableEqualTo(1)
                        .andPublishTimeBetween(DateUtil.getStartDateOfToday(), DateUtil.getEndDateOfToday())
                        .example());
    }

    public List<ErArticle> listTop(int top) {
        PageHelper.startPage(1, top);
        return erArticleMapper.selectByExample(new ErArticleExample().createCriteria()
                .andEnableEqualTo(1).andDeletedEqualTo(0)
                .example().orderBy(ErArticle.Column.readCount.desc()));
    }
}