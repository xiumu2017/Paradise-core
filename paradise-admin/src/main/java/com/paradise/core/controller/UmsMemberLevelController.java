package com.paradise.core.controller;

import com.paradise.core.common.api.Result;
import com.paradise.core.model.UmsMemberLevel;
import com.paradise.core.service.UmsMemberLevelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 会员等级管理Controller
 *
 * @author Paradise
 * @date 2018/4/26
 */
@ApiIgnore
@Controller
@Api(tags = "会员等级管理")
@RequestMapping("/memberLevel")
public class UmsMemberLevelController {
    private final UmsMemberLevelService memberLevelService;

    public UmsMemberLevelController(UmsMemberLevelService memberLevelService) {
        this.memberLevelService = memberLevelService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ApiOperation("查询所有会员等级")
    @ResponseBody
    public Result<List<UmsMemberLevel>> list(@RequestParam("defaultStatus") Integer defaultStatus) {
        List<UmsMemberLevel> memberLevelList = memberLevelService.list(defaultStatus);
        return Result.success(memberLevelList);
    }
}
