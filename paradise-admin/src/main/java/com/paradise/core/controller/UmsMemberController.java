package com.paradise.core.controller;

import com.paradise.core.common.api.CommonPage;
import com.paradise.core.common.api.Result;
import com.paradise.core.model.UmsMember;
import com.paradise.core.query.UmsMemberQuery;
import com.paradise.core.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 控制器
 *
 * @author Paradise
 */
@Api(tags = "人员管理")
@RestController
@AllArgsConstructor
@RequestMapping("/member")
public class UmsMemberController {
    private final UmsMemberService umsMemberService;

    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/page")
    public Result<CommonPage<UmsMember>> selectByPage(UmsMemberQuery query) {
        List<UmsMember> result = this.umsMemberService.selectByPage(query);
        return Result.success(CommonPage.restPage(result));
    }

    @ApiIgnore
    @ApiOperation("添加")
    @PostMapping(value = "/create")
    public Result<Integer> insert(@RequestBody @Validated UmsMember record) {
        int count = this.umsMemberService.insert(record);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiIgnore
    @ApiOperation("修改")
    @PostMapping(value = "/update")
    public Result<Integer> updateByPrimaryKey(UmsMember record) {
        int count = this.umsMemberService.updateByPrimaryKey(record);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("详情")
    @GetMapping(value = "/detail/{id}")
    public Result<UmsMember> selectByPrimaryKey(@PathVariable("id") Long id) {
        UmsMember umsMember = this.umsMemberService.selectByPrimaryKey(id);
        return Result.success(umsMember);
    }

    @ApiIgnore
    @ApiOperation("删除")
    @PostMapping(value = "/delete/{id}")
    public Result<Integer> deleteByPrimaryKey(@PathVariable("id") Long id) {
        int count = this.umsMemberService.deleteByPrimaryKey(id);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }
}