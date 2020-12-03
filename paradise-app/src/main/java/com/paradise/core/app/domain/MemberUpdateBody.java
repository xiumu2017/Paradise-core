package com.paradise.core.app.domain;

import com.paradise.core.common.utils.DateUtil;
import com.paradise.core.model.UmsMember;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Paradise
 */
@Getter
@Setter
@ApiModel("用户信息修改")
public class MemberUpdateBody {
    @ApiModelProperty("姓名")
    private String username;
    @ApiModelProperty("系部")
    private String department;
    @ApiModelProperty("班级")
    private String clazz;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("生日yyyy-MM-dd")
    private String birthday;
    @ApiModelProperty("手机号")
    private String phone;

    public UmsMember toMember() {
        return UmsMember.builder()
                .department(department)
                .clazz(clazz)
                .email(email)
                .phone(phone)
                .username(username)
                .birthday(DateUtil.format(birthday, "yyyy-MM-dd"))
                .build();
    }
}
