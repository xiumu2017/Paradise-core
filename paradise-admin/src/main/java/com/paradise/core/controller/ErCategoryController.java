package com.paradise.core.controller;

import com.paradise.core.body.ErCategoryBody;
import com.paradise.core.common.api.CommonPage;
import com.paradise.core.common.api.Result;
import com.paradise.core.model.ErCategory;
import com.paradise.core.query.ErCategoryQuery;
import com.paradise.core.service.ErCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 控制器
 *
 * @author Paradise
 */
@Api(tags = "文章类别管理")
@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class ErCategoryController {
    private final ErCategoryService erCategoryService;

    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/page")
    public Result<CommonPage<ErCategory>> selectByPage(ErCategoryQuery query) {
        List<ErCategory> result = this.erCategoryService.selectByPage(query);
        return Result.success(CommonPage.restPage(result));
    }

    @ApiOperation("添加")
    @PostMapping(value = "/create")
    public Result<Integer> insert(@RequestBody @Validated ErCategoryBody record) {
        int count = this.erCategoryService.insert(record);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("修改")
    @PostMapping(value = "/update")
    public Result<Integer> updateByPrimaryKey(@RequestBody @Validated ErCategoryBody record) {
        int count = this.erCategoryService.updateByPrimaryKey(record);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("详情")
    @GetMapping(value = "/detail/{id}")
    public Result<ErCategory> selectByPrimaryKey(@PathVariable("id") Long id) {
        ErCategory erCategory = this.erCategoryService.selectByPrimaryKey(id);
        return Result.success(erCategory);
    }

    @ApiOperation("删除")
    @PostMapping(value = "/delete/{id}")
    public Result<Integer> deleteByPrimaryKey(@PathVariable("id") Long id) {
        int count = this.erCategoryService.deleteByPrimaryKey(id);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }
}