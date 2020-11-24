package com.paradise.core.controller;

import com.paradise.core.body.AccountPassCreateBody;
import com.paradise.core.common.api.CommonPage;
import com.paradise.core.common.api.Result;
import com.paradise.core.common.utils.ParadiseBeanUtils;
import com.paradise.core.model.AccountPass;
import com.paradise.core.service.AccountPassService;
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
@RestController
@AllArgsConstructor
@Api(tags = "密码管理器")
@RequestMapping("/accountPass")
public class AccountPassController {
    private final AccountPassService accountPassService;

    @ApiOperation(value = "分页查询")
    @GetMapping(value = "/page")
    public Result<CommonPage<AccountPass>> selectByPage(Integer pageNum, Integer pageSize) {
        List<AccountPass> result = this.accountPassService.selectByPage(pageNum, pageSize);
        return Result.success(CommonPage.restPage(result));
    }

    @ApiOperation("添加")
    @PostMapping(value = "/create")
    public Result<Integer> insert(@RequestBody @Validated AccountPassCreateBody record) {
        int count = this.accountPassService.insert(ParadiseBeanUtils.copy(record, AccountPass.class));
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("修改")
    @PostMapping(value = "/update")
    public Result<Integer> updateByPrimaryKey(AccountPass record) {
        int count = this.accountPassService.updateByPrimaryKey(record);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("详情")
    @GetMapping(value = "/detail/{id}")
    public Result<AccountPass> selectByPrimaryKey(@PathVariable("id") Long id) {
        AccountPass accountPass = this.accountPassService.selectByPrimaryKey(id);
        return Result.success(accountPass);
    }

    @ApiOperation("删除")
    @PostMapping(value = "/delete/{id}")
    public Result<Integer> deleteByPrimaryKey(@PathVariable("id") Long id) {
        int count = this.accountPassService.deleteByPrimaryKey(id);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }
}