package com.paradise.core.controller;

import com.paradise.core.common.api.Result;
import com.paradise.core.dto.CheatData;
import com.paradise.core.model.ErArticle;
import com.paradise.core.service.ErArticleService;
import com.paradise.core.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 统计分析接口
 *
 * @author Paradise
 */
@Api(tags = "统计分析")
@RestController
@AllArgsConstructor
@RequestMapping("/analyze")
public class AnalyzeController {

    private final ErArticleService articleService;
    private final UmsMemberService memberService;

    @ApiOperation("查询每日新增发布量")
    @GetMapping("/publish-count/query")
    public Result<List<CheatData>> queryPublishCount(@RequestParam(required = false, defaultValue = "7") Integer day) {
        // 每日新增发布量
        List<CheatData> cheatDataList = articleService.cheatDataList(day);
        return Result.success(cheatDataList);
    }

    @ApiOperation("查询每日新增用户量")
    @GetMapping("/member-count/query")
    public Result<List<CheatData>> queryMemberCount(@RequestParam(required = false, defaultValue = "7") Integer day) {
        // 每日新增发布量
        List<CheatData> cheatDataList = memberService.cheatDataList(day);
        return Result.success(cheatDataList);
    }

    @ApiOperation("查询阅读排行榜")
    @GetMapping("/top-article/query")
    public Result<List<ErArticle>> queryTopArticle(@RequestParam(required = false, defaultValue = "10") Integer top) {
        // 累计top
        List<ErArticle> articleList = articleService.listTop(top);
        return Result.success(articleList);
    }


}
