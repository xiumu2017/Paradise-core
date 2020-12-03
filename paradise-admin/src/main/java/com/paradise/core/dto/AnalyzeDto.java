package com.paradise.core.dto;

import com.paradise.core.model.ErArticle;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@ApiModel("统计分析")
public class AnalyzeDto {
    @ApiModelProperty("每天新增发布量")
    private Integer dailyArticleCount;
    @ApiModelProperty("每天新增用户量")
    private Integer dailyMemberCount;
    @ApiModelProperty("每天文章阅读量排行")
    private List<ErArticle> dailyArticleTopList;

}
