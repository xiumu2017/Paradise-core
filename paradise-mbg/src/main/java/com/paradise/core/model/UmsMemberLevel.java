package com.paradise.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 *
 * @author Paradise
 */
@ApiModel(value="")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UmsMemberLevel implements Serializable {
    @ApiModelProperty(value="")
    private Long id;

    @ApiModelProperty(value="")
    private String name;

    @ApiModelProperty(value="")
    private Integer growthPoint;

    @ApiModelProperty(value="是否为默认等级：0->不是；1->是")
    private Integer defaultStatus;

    @ApiModelProperty(value="免运费标准")
    private BigDecimal freeFreightPoint;

    @ApiModelProperty(value="每次评价获取的成长值")
    private Integer commentGrowthPoint;

    @ApiModelProperty(value="是否有免邮特权")
    private Integer priviledgeFreeFreight;

    @ApiModelProperty(value="是否有签到特权")
    private Integer priviledgeSignIn;

    @ApiModelProperty(value="是否有评论获奖励特权")
    private Integer priviledgeComment;

    @ApiModelProperty(value="是否有专享活动特权")
    private Integer priviledgePromotion;

    @ApiModelProperty(value="是否有会员价格特权")
    private Integer priviledgeMemberPrice;

    @ApiModelProperty(value="是否有生日特权")
    private Integer priviledgeBirthday;

    @ApiModelProperty(value="")
    private String note;

    private static final long serialVersionUID = 1L;

    public enum Column {
        id("id", "id", "BIGINT", false),
        name("name", "name", "VARCHAR", true),
        growthPoint("growth_point", "growthPoint", "INTEGER", false),
        defaultStatus("default_status", "defaultStatus", "INTEGER", false),
        freeFreightPoint("free_freight_point", "freeFreightPoint", "DECIMAL", false),
        commentGrowthPoint("comment_growth_point", "commentGrowthPoint", "INTEGER", false),
        priviledgeFreeFreight("priviledge_free_freight", "priviledgeFreeFreight", "INTEGER", false),
        priviledgeSignIn("priviledge_sign_in", "priviledgeSignIn", "INTEGER", false),
        priviledgeComment("priviledge_comment", "priviledgeComment", "INTEGER", false),
        priviledgePromotion("priviledge_promotion", "priviledgePromotion", "INTEGER", false),
        priviledgeMemberPrice("priviledge_member_price", "priviledgeMemberPrice", "INTEGER", false),
        priviledgeBirthday("priviledge_birthday", "priviledgeBirthday", "INTEGER", false),
        note("note", "note", "VARCHAR", false);

        private static final String BEGINNING_DELIMITER = "`";

        private static final String ENDING_DELIMITER = "`";

        private final String column;

        private final boolean isColumnNameDelimited;

        private final String javaProperty;

        private final String jdbcType;

        public String value() {
            return this.column;
        }

        public String getValue() {
            return this.column;
        }

        public String getJavaProperty() {
            return this.javaProperty;
        }

        public String getJdbcType() {
            return this.jdbcType;
        }

        Column(String column, String javaProperty, String jdbcType, boolean isColumnNameDelimited) {
            this.column = column;
            this.javaProperty = javaProperty;
            this.jdbcType = jdbcType;
            this.isColumnNameDelimited = isColumnNameDelimited;
        }

        public String desc() {
            return this.getEscapedColumnName() + " DESC";
        }

        public String asc() {
            return this.getEscapedColumnName() + " ASC";
        }

        public static Column[] excludes(Column ... excludes) {
            ArrayList<Column> columns = new ArrayList<>(Arrays.asList(Column.values()));
            if (excludes != null && excludes.length > 0) {
                columns.removeAll(new ArrayList<>(Arrays.asList(excludes)));
            }
            return columns.toArray(new Column[]{});
        }

        public static Column[] all() {
            return Column.values();
        }

        public String getEscapedColumnName() {
            if (this.isColumnNameDelimited) {
                return new StringBuilder().append(BEGINNING_DELIMITER).append(this.column).append(ENDING_DELIMITER).toString();
            } else {
                return this.column;
            }
        }

        public String getAliasedEscapedColumnName() {
            return this.getEscapedColumnName();
        }
    }
}