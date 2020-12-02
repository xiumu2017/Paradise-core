package com.paradise.core.dto.detail;

import com.paradise.core.model.ErArticle;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@ApiModel("文章详情")
public class ErArticleDetail extends ErArticle {
    List<Long> categoryIds;
}
