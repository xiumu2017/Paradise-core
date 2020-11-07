package com.paradise.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
public class AccountPass implements Serializable {
    @ApiModelProperty(value="自增主键")
    private Long id;

    @ApiModelProperty(value="名称")
    private String name;

    @ApiModelProperty(value="类型")
    private Integer type;

    @ApiModelProperty(value="1微信2支付宝3用户名4手机号5邮箱6其他")
    private Integer loginType;

    @ApiModelProperty(value="网站地址")
    private String address;

    @ApiModelProperty(value="所属公司e.BAT")
    private String company;

    @ApiModelProperty(value="用户名")
    private String username;

    @ApiModelProperty(value="手机号")
    private String telephone;

    @ApiModelProperty(value="邮箱")
    private String email;

    @ApiModelProperty(value="重要性")
    private Integer importance;

    @ApiModelProperty(value="明文密码")
    private String password;

    @ApiModelProperty(value="加密密码")
    private String rsaPassword;

    @ApiModelProperty(value="备注")
    private String remark;

    @ApiModelProperty(value="创建时间")
    private Date createTime;

    @ApiModelProperty(value="更新时间")
    private Date updateTime;

    @ApiModelProperty(value="创建人")
    private Long createBy;

    @ApiModelProperty(value="更新人")
    private Long updateBy;

    private static final long serialVersionUID = 1L;

    public enum Column {
        id("id", "id", "BIGINT", false),
        name("name", "name", "VARCHAR", true),
        type("type", "type", "INTEGER", true),
        loginType("login_type", "loginType", "INTEGER", false),
        address("address", "address", "VARCHAR", false),
        company("company", "company", "VARCHAR", false),
        username("username", "username", "VARCHAR", false),
        telephone("telephone", "telephone", "VARCHAR", false),
        email("email", "email", "VARCHAR", false),
        importance("importance", "importance", "INTEGER", false),
        password("password", "password", "VARCHAR", true),
        rsaPassword("rsa_password", "rsaPassword", "VARCHAR", false),
        remark("remark", "remark", "VARCHAR", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        updateTime("update_time", "updateTime", "TIMESTAMP", false),
        createBy("create_by", "createBy", "BIGINT", false),
        updateBy("update_by", "updateBy", "BIGINT", false);

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