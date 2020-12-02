package com.paradise.core.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Paradise
 */
@Getter
@Setter
@ApiModel("人员查询")
public class UmsMemberQuery extends BaseQuery {
    @ApiModelProperty("微信昵称")
    private String nickName;
}
