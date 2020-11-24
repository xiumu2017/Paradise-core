package com.paradise.core.body;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel("账户密码创建Body")
public class AccountPassCreateBody {
    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "类型")
    private Integer type;

    @ApiModelProperty(value = "1微信2支付宝3用户名4手机号5邮箱6其他")
    private Integer loginType;

    @ApiModelProperty(value = "网站地址")
    private String address;

    @ApiModelProperty(value = "所属公司e.BAT")
    private String company;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "手机号")
    private String telephone;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "重要性")
    private Integer importance;

    @ApiModelProperty(value = "明文密码")
    private String password;

    @ApiModelProperty(value = "加密密码")
    private String rsaPassword;

    @ApiModelProperty(value = "备注")
    private String remark;

}
