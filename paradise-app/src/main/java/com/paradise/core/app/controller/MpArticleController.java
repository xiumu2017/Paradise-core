package com.paradise.core.app.controller;

import com.paradise.core.app.service.MpArticleService;
import com.paradise.core.app.service.MpMemberService;
import com.paradise.core.common.api.CommonPage;
import com.paradise.core.common.api.Result;
import com.paradise.core.model.ErArticle;
import com.paradise.core.model.ErCategory;
import com.paradise.core.query.ErArticleQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Paradise
 */
@Api(tags = "文章相关接口")
@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class MpArticleController {

    private final MpMemberService memberService;
    private final MpArticleService articleService;


    @ApiOperation("文章分类")
    @GetMapping("/category/all")
    public Result<List<ErCategory>> categoryList() {
        return Result.success(articleService.categoryList());
    }

    @ApiOperation("文章列表-分页")
    @GetMapping("/page")
    public Result<CommonPage<ErArticle>> articlePage(ErArticleQuery query) {
        return Result.success(CommonPage.restPage(articleService.articleList(query)));
    }

    @ApiOperation("文章详情")
    @GetMapping("/{id}")
    public Result<ErArticle> detail(@PathVariable Long id) {
        return Result.success(articleService.detail(id, memberService.getCurrentMember()));
    }

}
