package com.paradise.core.controller;

import com.paradise.core.body.ErArticleBody;
import com.paradise.core.common.api.CommonPage;
import com.paradise.core.common.api.Result;
import com.paradise.core.model.ErArticle;
import com.paradise.core.query.ErArticleQuery;
import com.paradise.core.service.ErArticleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文章管理控制器
 *
 * @author Paradise
 */
@RestController
@AllArgsConstructor
@Api(tags = "文章管理")
@RequestMapping("/article")
public class ErArticleController {
    private final ErArticleService erArticleService;

    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/page")
    public Result<CommonPage<ErArticle>> selectByPage(ErArticleQuery query) {
        List<ErArticle> result = this.erArticleService.selectByPage(query);
        return Result.success(CommonPage.restPage(result));
    }

    @ApiOperation("添加")
    @PostMapping(value = "/create")
    public Result<Integer> insert(@RequestBody @Validated ErArticleBody record) {
        int count = this.erArticleService.insert(record);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("修改")
    @PostMapping(value = "/update")
    public Result<Integer> updateByPrimaryKey(@RequestBody @Validated ErArticleBody record) {
        int count = this.erArticleService.updateByPrimaryKey(record);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("修改状态")
    @PostMapping("/status/{id}")
    public Result<Integer> changeStatus(@PathVariable("id") Long id,
                                        @RequestParam Integer enable) {
        int count = this.erArticleService.changeStatus(id, enable);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("详情")
    @GetMapping(value = "/detail/{id}")
    public Result<ErArticle> selectByPrimaryKey(@PathVariable("id") Long id) {
        ErArticle erArticle = this.erArticleService.selectByPrimaryKey(id);
        return Result.success(erArticle);
    }

    @ApiOperation("删除")
    @PostMapping(value = "/delete/{id}")
    public Result<Integer> deleteByPrimaryKey(@PathVariable("id") Long id) {
        int count = this.erArticleService.deleteByPrimaryKey(id);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }
}