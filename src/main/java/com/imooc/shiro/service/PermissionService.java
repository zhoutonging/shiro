package com.imooc.shiro.service;

import com.imooc.shiro.dto.RolePermissionDto;
import com.imooc.shiro.model.Permission;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 服务类
 *
 * @author CC
 * @since 2019-06-11
 */
public interface PermissionService extends IService<Permission> {

    /**
     * 添加父级权限
     *
     * @param permission
     */
    void save(Permission permission);

    /**
     * 设置子级权限
     *
     * @param permission
     */
    void saveSon(Permission permission);

    /**
     * 查询权限列表
     *
     * @return
     */
    List<Permission> findAll();

    /**
     * 查询角色可以访问的权限
     *
     * @return
     */
    List<RolePermissionDto> findRolePermission();
}
