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
public class UmsMember implements Serializable {
    @ApiModelProperty(value="")
    private Long id;

    @ApiModelProperty(value="用户名")
    private String username;

    @ApiModelProperty(value="密码")
    private String password;

    @ApiModelProperty(value="微信昵称")
    private String nickname;

    @ApiModelProperty(value="手机号码")
    private String phone;

    @ApiModelProperty(value="帐号启用状态:0->禁用；1->启用")
    private Integer status;

    @ApiModelProperty(value="微信头像")
    private String wxIcon;

    @ApiModelProperty(value="性别：0->未知；1->男；2->女")
    private Integer gender;

    @ApiModelProperty(value="生日")
    private Date birthday;

    @ApiModelProperty(value="所在城市")
    private String city;

    @ApiModelProperty(value="所属系部")
    private String department;

    @ApiModelProperty(value="所属班级")
    private String clazz;

    @ApiModelProperty(value="微信openId")
    private String wxOpenId;

    @ApiModelProperty(value="微信unionId")
    private String wxUnionId;

    @ApiModelProperty(value="注册时间")
    private Date createTime;

    @ApiModelProperty(value="邀请码")
    private String invitationCode;

    @ApiModelProperty(value="邀请人ID")
    private Long parentId;

    @ApiModelProperty(value="")
    private String email;

    private static final long serialVersionUID = 1L;

    public enum Column {
        id("id", "id", "BIGINT", false),
        username("username", "username", "VARCHAR", false),
        password("password", "password", "VARCHAR", true),
        nickname("nickname", "nickname", "VARCHAR", false),
        phone("phone", "phone", "VARCHAR", false),
        status("status", "status", "INTEGER", true),
        wxIcon("wx_icon", "wxIcon", "VARCHAR", false),
        gender("gender", "gender", "INTEGER", false),
        birthday("birthday", "birthday", "DATE", false),
        city("city", "city", "VARCHAR", false),
        department("department", "department", "VARCHAR", false),
        clazz("clazz", "clazz", "VARCHAR", false),
        wxOpenId("wx_open_id", "wxOpenId", "VARCHAR", false),
        wxUnionId("wx_union_id", "wxUnionId", "VARCHAR", false),
        createTime("create_time", "createTime", "TIMESTAMP", false),
        invitationCode("invitation_code", "invitationCode", "VARCHAR", false),
        parentId("parent_id", "parentId", "BIGINT", false),
        email("email", "email", "VARCHAR", false);

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