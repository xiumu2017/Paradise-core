package com.paradise.core.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 基础查询参数
 *
 * @author Paradise
 */
@Getter
@Setter
public class BaseQuery {
    @ApiModelProperty("分页参数")
    private Integer pageNum;
    @ApiModelProperty("分页参数")
    private Integer pageSize;
    @ApiModelProperty("是否启用")
    private Integer enable;
}
