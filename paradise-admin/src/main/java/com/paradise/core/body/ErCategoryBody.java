package com.paradise.core.body;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * 文章分类
 *
 * @author Paradise
 */
@Data
@ApiModel("文章分类")
public class ErCategoryBody {
    @ApiModelProperty(value = "自增主键")
    private Long id;

    @ApiModelProperty(value = "类别名称")
    @NotEmpty
    private String name;

    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    @ApiModelProperty(value = "是否显示")
    private Integer enable;
}
