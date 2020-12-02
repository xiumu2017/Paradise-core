package com.paradise.core.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 统计分析接口
 *
 * @author Paradise
 */
@Api(tags = "统计分析 #待确认")
@RestController
@AllArgsConstructor
@RequestMapping("/analyze")
public class AnalyzeController {
    // 用户增加量
    // 文章数量
    // 文章阅读排行
}
