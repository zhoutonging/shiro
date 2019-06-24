package com.imooc.shiro.service;

import com.imooc.shiro.dto.RoleDto;
import com.imooc.shiro.model.Role;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 服务类
 *
 * @author CC
 * @since 2019-06-11
 */
public interface RoleService extends IService<Role> {

    /**
     * 查询角色列表
     *
     * @return
     */
    List<Role> findAll();

    /**
     * 根据userId查询角色集合
     *
     * @param userId
     * @return
     */
    List<RoleDto> findRoleListByUserId(Integer userId);

    /**
     * 根据userId查询角色集合
     *
     * @param userId
     * @return
     */
    List<Role> findByUserId(Integer userId);
}
