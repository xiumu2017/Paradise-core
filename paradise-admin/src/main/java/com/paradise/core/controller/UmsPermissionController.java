package com.paradise.core.controller;

import com.paradise.core.common.api.Result;
import com.paradise.core.dto.UmsPermissionNode;
import com.paradise.core.model.UmsPermission;
import com.paradise.core.service.UmsPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户权限管理
 *
 * @author Paradise
 * @date 2018/9/29
 */
@Api(tags = "3. 后台用户权限管理")
@RestController
@RequestMapping("/permission")
public class UmsPermissionController {
    private final UmsPermissionService permissionService;

    public UmsPermissionController(UmsPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @ApiOperation("添加权限")
    @PostMapping(value = "/create")
    public Result<Integer> create(@RequestBody UmsPermission permission) {
        int count = permissionService.create(permission);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("修改权限")
    @PostMapping(value = "/update/{id}")
    public Result<Integer> update(@PathVariable Long id, @RequestBody UmsPermission permission) {
        int count = permissionService.update(id, permission);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("根据id批量删除权限")
    @DeleteMapping(value = "/delete")
    public Result<Integer> delete(@RequestParam("ids") List<Long> ids) {
        int count = permissionService.delete(ids);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("以层级结构返回所有权限")
    @GetMapping(value = "/treeList")
    public Result<List<UmsPermissionNode>> treeList() {
        List<UmsPermissionNode> permissionNodeList = permissionService.treeList();
        return Result.success(permissionNodeList);
    }

    @ApiOperation("获取所有权限列表")
    @GetMapping(value = "/list")
    public Result<List<UmsPermission>> list() {
        List<UmsPermission> permissionList = permissionService.list();
        return Result.success(permissionList);
    }
}
