package com.paradise.core.app.controller;

import com.paradise.core.app.service.impl.AppMemberService;
import com.paradise.core.common.api.Result;
import com.paradise.core.model.UmsMember;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员登录注册管理Controller
 *
 * @author Paradise
 * @date 2018/8/3
 */
@Api(tags = "1.登录注册管理")
@RestController
@RequestMapping("/sso")
@Slf4j
public class YxxWorkerController {

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private final AppMemberService appMemberService;

    public YxxWorkerController(AppMemberService appMemberService) {
        this.appMemberService = appMemberService;
    }

    @ApiOperation("注册")
    @PostMapping(value = "/register")
    public Result register(@RequestParam(required = false) String password,
                           @RequestParam String telephone,
                           @RequestParam String authCode,
                           @RequestParam(required = false) String invitationCode) {
        appMemberService.register(password, telephone, authCode, invitationCode);
        return Result.success(null, "注册成功");
    }

    @ApiOperation("手机号登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Result login(@RequestParam String phone,
                        @RequestParam String password) {
        String token = appMemberService.login(phone, password);
        if (token == null) {
            return Result.validateFailed("手机号或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>(2);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return Result.success(tokenMap);
    }

    @ApiOperation("获取维修工信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public Result<UmsMember> info(@ApiIgnore Principal principal) {
        if (principal == null) {
            return Result.unauthorized(null);
        }
        UmsMember member = appMemberService.getCurrentMember();
        return Result.success(member);
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    public Result getAuthCode(@RequestParam String telephone) {
        String authCode = appMemberService.generateAuthCode(telephone);
        return Result.success(authCode, "获取验证码成功");
    }

    @ApiOperation("修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    public Result updatePassword(
            @ApiIgnore Principal principal,
            @RequestParam String password,
            @RequestParam String oldPass) {
        if (principal == null) {
            return Result.forbidden(null);
        }
        appMemberService.updatePassword(password, oldPass);
        return Result.success(null, "密码修改成功");
    }

    @ApiOperation("忘记密码")
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public Result resetPassword(@RequestParam String telephone,
                                @RequestParam String password,
                                @RequestParam String authCode) {
        appMemberService.resetPass(telephone, password, authCode);
        return Result.success(null, "密码修改成功");
    }


    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    public Result refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = appMemberService.refreshToken(token);
        if (refreshToken == null) {
            return Result.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>(2);
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return Result.success(tokenMap);
    }
}
