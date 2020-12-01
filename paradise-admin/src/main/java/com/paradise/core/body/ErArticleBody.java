package com.paradise.core.body;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Paradise
 */
@ApiModel("文章")
@Getter
@Setter
public class ErArticleBody {
    @ApiModelProperty(value = "自增主键")
    private Long id;

    @ApiModelProperty(value = "文章标题")
    private String title;

    @ApiModelProperty(value = "缩略图")
    private String thumbnail;

    @ApiModelProperty(value = "是否显示（0草稿1发布-1禁用）")
    private Integer enable;

    @ApiModelProperty(value = "文章内容")
    private String content;

    @ApiModelProperty("类别列表")
    private List<Long> categoryIds;
}
