package com.paradise.core.app.service.impl;

import com.paradise.core.app.domain.WorkerDetails;
import com.paradise.core.common.exception.Asserts;
import com.paradise.core.common.utils.GeneratorUtil;
import com.paradise.core.example.UmsMemberExample;
import com.paradise.core.mapper.UmsMemberMapper;
import com.paradise.core.model.UmsMember;
import com.paradise.core.security.util.JwtTokenUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Paradise
 */
@Slf4j
@Service
@AllArgsConstructor
public class AppMemberService {
    private final UmsMemberMapper memberMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    private UmsMember getByPhone(String phone) {
        return memberMapper.selectOneByExample(new UmsMemberExample().createCriteria().andPhoneEqualTo(phone).example());
    }

    public void register(String password, String telephone, String authCode, String invitationCode) {
        //验证验证码
        if (!authCode.equals(telephone)) {
            Asserts.fail("验证码错误");
        }
        //查询是否已有该用户
        UmsMemberExample example = new UmsMemberExample();
        example.createCriteria().andPhoneEqualTo(telephone);
        List<UmsMember> umsWorkers = memberMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(umsWorkers)) {
            Asserts.fail("该用户已经存在");
        }
        Long parentId = null;
        if (!StringUtils.isEmpty(invitationCode)) {
            UmsMember worker = memberMapper.selectOneByExample(
                    new UmsMemberExample().createCriteria()
                            .andInvitationCodeEqualTo(invitationCode).example()
            );
            if (worker != null) {
                parentId = worker.getId();
            } else {
                Asserts.fail("邀请码不存在");
            }
        }
        //没有该用户进行添加操作
        UmsMember umsWorker = new UmsMember();
        umsWorker.setUsername(telephone);
        umsWorker.setPhone(telephone);
        if (!StringUtils.isEmpty(password)) {
            umsWorker.setPassword(passwordEncoder.encode(password));
        }
        umsWorker.setCreateTime(new Date());
        umsWorker.setParentId(parentId);
        umsWorker.setStatus(1);
        umsWorker.setStatus(0);
        memberMapper.insert(umsWorker);
        if (umsWorker.getId() != null) {
            // 生成邀请码
            umsWorker.setInvitationCode(GeneratorUtil.generatePromotionCode(umsWorker.getId()));
            memberMapper.updateByPrimaryKeySelective(umsWorker, UmsMember.Column.invitationCode);
        }
    }

    public String generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    public void updatePassword(String password, String oldPass) {
        UmsMember worker = this.getCurrentMember();
        if (worker == null) {
            Asserts.fail("该账号不存在");
        }
        if (!passwordEncoder.matches(oldPass, worker.getPassword())) {
            Asserts.fail("原始密码不正确");
        }
        worker.setPassword(passwordEncoder.encode(password));
        memberMapper.updateByPrimaryKeySelective(worker, UmsMember.Column.password);
    }

    public void resetPass(String telephone, String password, String authCode) {
        UmsMember worker = getByPhone(telephone);
        if (worker == null) {
            Asserts.fail("该账号不存在");
        }
        //验证验证码
        if (authCode.equals(telephone)) {
            Asserts.fail("验证码错误");
        }
        worker.setPassword(passwordEncoder.encode(password));
        memberMapper.updateByPrimaryKeySelective(worker, UmsMember.Column.password);
    }

    public UmsMember getCurrentMember() {
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        WorkerDetails workerDetails = (WorkerDetails) auth.getPrincipal();
        return workerDetails.getUmsMember();
    }

    public UserDetails loadUserByPhone(String phone) {
        UmsMember member = getByPhone(phone);
        if (member != null) {
            return new WorkerDetails(member);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    public String login(String phone, String password) {
        String token = null;
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByPhone(phone);
            if (!passwordEncoder.matches(password, userDetails.getPassword())) {
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
        } catch (AuthenticationException e) {
            log.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    public String refreshToken(String token) {
        return jwtTokenUtil.refreshHeadToken(token);
    }

}
