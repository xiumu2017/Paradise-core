package com.paradise.core.controller;

import com.paradise.core.common.api.Result;
import com.paradise.core.dto.UmsPermissionNode;
import com.paradise.core.model.UmsPermission;
import com.paradise.core.service.UmsPermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 后台用户权限管理
 *
 * @author Paradise
 * @date 2018/9/29
 */
@Controller
@Api(tags = "8.7 后台用户权限管理")
@RequestMapping("/permission")
public class UmsPermissionController {
    private final UmsPermissionService permissionService;

    public UmsPermissionController(UmsPermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @ApiOperation("添加权限")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public Result create(@RequestBody UmsPermission permission) {
        int count = permissionService.create(permission);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("修改权限")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Result update(@PathVariable Long id, @RequestBody UmsPermission permission) {
        int count = permissionService.update(id, permission);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("根据id批量删除权限")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Result delete(@RequestParam("ids") List<Long> ids) {
        int count = permissionService.delete(ids);
        if (count > 0) {
            return Result.success(count);
        }
        return Result.failed();
    }

    @ApiOperation("以层级结构返回所有权限")
    @RequestMapping(value = "/treeList", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<UmsPermissionNode>> treeList() {
        List<UmsPermissionNode> permissionNodeList = permissionService.treeList();
        return Result.success(permissionNodeList);
    }

    @ApiOperation("获取所有权限列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Result<List<UmsPermission>> list() {
        List<UmsPermission> permissionList = permissionService.list();
        return Result.success(permissionList);
    }
}
