package com.paradise.core.app.service;

import com.github.pagehelper.PageHelper;
import com.paradise.core.app.dao.MpArticleDao;
import com.paradise.core.example.ErCategoryExample;
import com.paradise.core.mapper.ErArticleMapper;
import com.paradise.core.mapper.ErCategoryMapper;
import com.paradise.core.mapper.ErReadRecordMapper;
import com.paradise.core.model.ErArticle;
import com.paradise.core.model.ErCategory;
import com.paradise.core.model.ErReadRecord;
import com.paradise.core.model.UmsMember;
import com.paradise.core.query.ErArticleQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Paradise
 */
@Slf4j
@Service
@AllArgsConstructor
public class MpArticleService {

    private final ErCategoryMapper categoryMapper;
    private final ErArticleMapper articleMapper;
    private final ErReadRecordMapper readRecordMapper;
    private final MpArticleDao articleDao;

    public List<ErCategory> categoryList() {
        return categoryMapper.selectByExample(
                new ErCategoryExample().createCriteria()
                        .andDeletedEqualTo(0)
                        .andEnableEqualTo(1)
                        .example().orderBy(ErCategory.Column.createAt.desc())
        );
    }

    public List<ErArticle> articleList(ErArticleQuery query) {
        PageHelper.startPage(query.getPageNum(), query.getPageSize());
        return articleDao.pageQuery(query);
    }

    public ErArticle detail(Long id, UmsMember member) {
        if (member == null) {
            member = UmsMember.builder().id(0L).build();
        }
        // 添加阅读记录
        readRecordMapper.insertSelective(ErReadRecord.builder().articleId(id).memberId(member.getId()).build());
        // 增加阅读量？
        ErArticle article = articleMapper.selectByPrimaryKeySelective(id, ErArticle.Column.excludes(ErArticle.Column.content));
        article.setReadCount(article.getReadCount() + 1);
        int x = articleMapper.updateByPrimaryKeySelective(article, ErArticle.Column.readCount, ErArticle.Column.updateTime);
        log.info("更新阅读量 result：{}", x);
        return articleMapper.selectByPrimaryKey(id);
    }


}
