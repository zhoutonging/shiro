package com.imooc.shiro.service;

import com.imooc.shiro.model.Role;

import java.util.List;

public interface RoleService {

    /**
     * 查询角色列表
     * @return
     */
    List<Role> findAll();
}
