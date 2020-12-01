package com.paradise.core.app.domain;

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
    @ApiModelProperty("系部")
    private String department;
    @ApiModelProperty("班级")
    private String clazz;
    @ApiModelProperty("邮箱")
    private String email;
    @ApiModelProperty("性别：0位置1男2女")
    private Integer gender;

    public UmsMember toMember() {
        return UmsMember.builder()
                .department(department)
                .clazz(clazz)
                .email(email)
                .gender(gender)
                .build();
    }
}
