package com.paradise.core.controller;

import com.paradise.core.common.api.Result;
import com.paradise.core.dto.AnalyzeDto;
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

    @ApiOperation("查询")
    @GetMapping("/query")
    public Result<AnalyzeDto> analyzeDtoResult(@RequestParam(required = false, defaultValue = "10") Integer top) {
        // 每日新增发布量
        Integer publishCount = Math.toIntExact(articleService.dailyPublishCount());
        // 每日新增用户量
        Integer count = Math.toIntExact(memberService.dailyCount());
        // 累计top
        List<ErArticle> articleList = articleService.listTop(top);
        return Result.success(AnalyzeDto.builder().dailyArticleCount(publishCount).dailyArticleTopList(articleList)
                .dailyMemberCount(count).build());
    }


}
