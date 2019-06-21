package com.imooc.shiro.mapper;

import com.imooc.shiro.dto.RolePermissionDto;
import com.imooc.shiro.model.Permission;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author CC
 * @since 2019-06-11
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 根据roleId查询权限列表
     *
     * @param roleId
     * @return
     */
    List<Permission> findPermissionListByRoleId(String roleId);

    /**
     * 查询角色可以访问的权限
     *
     * @return
     */
    List<RolePermissionDto> findRolePermission();
}
