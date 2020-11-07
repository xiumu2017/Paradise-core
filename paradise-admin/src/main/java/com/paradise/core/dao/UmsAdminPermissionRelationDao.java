package com.paradise.core.dao;

import com.paradise.core.model.UmsAdminPermissionRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 自定义用户权限关系管理Dao
 *
 * @author Paradise
 * @date 2018/10/8
 */
public interface UmsAdminPermissionRelationDao {
    /**
     * 批量创建
     *
     * @param list 批量插入内容
     * @return int
     */
    int insertList(@Param("list") List<UmsAdminPermissionRelation> list);
}
