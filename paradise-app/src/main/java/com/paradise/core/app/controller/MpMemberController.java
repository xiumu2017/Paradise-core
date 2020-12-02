package com.paradise.core.app.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.paradise.core.app.config.WxMaConfiguration;
import com.paradise.core.app.domain.MemberUpdateBody;
import com.paradise.core.app.service.MpMemberService;
import com.paradise.core.common.api.Result;
import com.paradise.core.model.UmsMember;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 会员登录注册管理Controller
 *
 * @author macro
 * @date 2018/8/3
 */
@Slf4j
@Api(tags = "1. 登录注册相关")
@RestController
@RequestMapping("/sso")
public class MpMemberController {
    private final static String APP_ID = "wxa7a9d5fc03f52d83";

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    private final MpMemberService memberService;

    public MpMemberController(MpMemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 登陆接口
     */
    @ApiOperation("微信授权登录")
    @GetMapping("/login")
    public Result<Object> login(String code) {
        if (StringUtils.isBlank(code)) {
            return Result.failed("Code 不能为空");
        }
        log.info("code:{}", code);
        final WxMaService wxService = WxMaConfiguration.getMaService(APP_ID);
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            log.info(session.getSessionKey());
            log.info(session.getOpenid());

            String token = memberService.login(session);
            Map<String, Object> tokenMap = new HashMap<>(4);
            tokenMap.put("token", token);
            tokenMap.put("tokenHead", tokenHead);
            tokenMap.put("wxSession", session);
            return Result.success(tokenMap);
        } catch (WxErrorException e) {
            log.error(e.getMessage(), e);
            return Result.failed(e.getLocalizedMessage());
        }
    }

    @ApiOperation("接口调试用：登录")
    @PostMapping(value = "/login-test")
    public Result<Object> loginTest(@RequestParam String username,
                                    @RequestParam String password) {
        String token = memberService.loginTest(username, password);
        if (token == null) {
            return Result.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>(2);
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return Result.success(tokenMap);
    }

    /**
     * <pre>
     * 获取用户信息接口
     * </pre>
     */
    @ApiOperation("获取用户信息接口")
    @GetMapping("/wx-info")
    public Result<WxMaUserInfo> wxInfo(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(APP_ID);
        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return Result.failed("微信参数校验失败");
        }
        // 解密用户信息
        WxMaUserInfo userInfo = wxService.getUserService().getUserInfo(sessionKey, encryptedData, iv);
        memberService.updateWxInfo(userInfo);
        return Result.success(userInfo);
    }

    /**
     * <pre>
     * 获取用户绑定手机号信息
     * </pre>
     */
    @ApiIgnore
    @ApiOperation("获取用户绑定手机号信息")
    @GetMapping("/phone")
    public Result<WxMaPhoneNumberInfo> phone(String sessionKey, String signature, String rawData, String encryptedData, String iv) {
        final WxMaService wxService = WxMaConfiguration.getMaService(APP_ID);
        // 用户信息校验
        if (!wxService.getUserService().checkUserInfo(sessionKey, rawData, signature)) {
            return Result.failed("微信参数校验失败");
        }
        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        memberService.updateWxPhoneInfo(phoneNoInfo);
        return Result.success(phoneNoInfo);
    }

    @ApiOperation("个人中心-获取用户信息")
    @GetMapping(value = "/info")
    public Result<UmsMember> info(@ApiIgnore Principal principal) {
        if (principal == null) {
            return Result.unauthorized(null);
        }
        UmsMember member = memberService.getCurrentMember();
        return Result.success(member);
    }

    @ApiOperation("修改个人资料")
    @PostMapping("/modify")
    public Result<Integer> modify(@RequestBody MemberUpdateBody body) {
        int x = memberService.modify(body);
        if (x == 1) {
            return Result.success(x);
        }
        return Result.failed();
    }

    @ApiIgnore
    @ApiOperation("根据父级ID-分页查询")
    @GetMapping("/promotion/list")
    public Result<List<UmsMember>> listByParentId(@ApiIgnore Principal principal) {
        if (principal == null) {
            return Result.unauthorized(null);
        }
        return Result.success(memberService.listByParentId());
    }

}
