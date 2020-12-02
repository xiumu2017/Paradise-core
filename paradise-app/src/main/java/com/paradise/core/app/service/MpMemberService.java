package com.paradise.core.app.service;

import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.bean.WxMaUserInfo;
import com.paradise.core.app.domain.MemberDetails;
import com.paradise.core.app.domain.MemberUpdateBody;
import com.paradise.core.common.exception.Asserts;
import com.paradise.core.common.utils.GeneratorUtil;
import com.paradise.core.example.UmsMemberExample;
import com.paradise.core.mapper.UmsMemberMapper;
import com.paradise.core.model.UmsMember;
import com.paradise.core.security.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * @author Paradise
 */
@Slf4j
@Service
@AllArgsConstructor
public class MpMemberService {
    private final UmsMemberMapper memberMapper;
    @Lazy
    private final JwtTokenUtil jwtTokenUtil;


    private UmsMember getByWxOpenId(String openId) {
        return memberMapper.selectOneByExample(new UmsMemberExample().createCriteria().andWxOpenIdEqualTo(openId).example());
    }

    private UmsMember getByInvitationCode(String invitationCode) {
        return memberMapper.selectOneByExample(new UmsMemberExample().createCriteria().andInvitationCodeEqualTo(invitationCode).example());
    }

    public List<UmsMember> listByParentId() {
        UmsMember member = getCurrentMember();
        return memberMapper.selectByExample(new UmsMemberExample().createCriteria().andParentIdEqualTo(member.getId())
                .example().orderBy(UmsMember.Column.createTime.desc()));
    }

    public UmsMember getCurrentMember() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        MemberDetails memberDetails;
        try {
            memberDetails = (MemberDetails) auth.getPrincipal();
            return memberDetails.getUmsMember();
        } catch (Exception e) {
            log.error("获取当前用户信息失败：{}", e.getLocalizedMessage());
        }
        return null;
    }

    public UserDetails loadUserByOpenId(String openId) {
        UmsMember member = getByWxOpenId(openId);
        if (member != null) {
            return new MemberDetails(member);
        }
        log.error("小程序登陆异常：{}", openId);
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    public String loginTest(String username, String password) {
        String token = null;
        //密码需要客户端加密后传递
        String defaultTestPass = "Paradise";
        Asserts.pvIsTrue(defaultTestPass.equals(password), "Password check fail~");
        try {
            UserDetails userDetails = loadUserByOpenId(username);
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }


    public String login(WxMaJscode2SessionResult session) {
        String token = null;
        // 判断用户是否存在
        UmsMember member = this.getByWxOpenId(session.getOpenid());
        if (member == null) {
            UmsMember member1 = UmsMember.builder()
                    .wxOpenId(session.getOpenid()).wxUnionId(session.getUnionid())
                    .username("").parentId(0L)
                    .status(1).createTime(new Date())
                    .build();
            memberMapper.insertSelective(member1);
            // 生成邀请码
            this.invitationCodeGenerator(member1.getId());
        }
        try {
            UserDetails userDetails = loadUserByOpenId(session.getOpenid());
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    private void invitationCodeGenerator(Long id) {
        if (id == null) {
            return;
        }
        UmsMember member = UmsMember.builder().id(id).invitationCode(GeneratorUtil.generatePromotionCode(id)).build();
        memberMapper.updateByPrimaryKeySelective(member, UmsMember.Column.invitationCode);
    }

    public void updateWxInfo(WxMaUserInfo userInfo) {
        log.info("微信用户信息：{}", userInfo.toString());
        UmsMember member = this.getByWxOpenId(userInfo.getOpenId());
        if (member != null) {
            member.setWxIcon(userInfo.getAvatarUrl());
            member.setNickname(userInfo.getNickName());
            member.setGender(Integer.valueOf(userInfo.getGender()));
            if (StringUtils.hasText(userInfo.getUnionId())) {
                member.setWxUnionId(userInfo.getUnionId());
            } else {
                member.setWxUnionId("");
            }
            memberMapper.updateByPrimaryKeySelective(member, UmsMember.Column.wxIcon, UmsMember.Column.nickname,
                    UmsMember.Column.gender, UmsMember.Column.wxUnionId);
        }
    }

    public void updateWxPhoneInfo(WxMaPhoneNumberInfo phoneNoInfo) {
        log.info(phoneNoInfo.toString());
        UmsMember member = this.getCurrentMember();
        if (member != null) {
            member.setPhone(phoneNoInfo.getPurePhoneNumber());
            memberMapper.updateByPrimaryKeySelective(member, UmsMember.Column.phone);
        }
    }

    public int modify(MemberUpdateBody body) {
        UmsMember currentMember = this.getCurrentMember();
        UmsMember member = body.toMember();
        member.setId(currentMember.getId());
        return memberMapper.updateByPrimaryKeySelective(member);

    }

}
