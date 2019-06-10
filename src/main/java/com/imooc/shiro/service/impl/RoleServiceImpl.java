package com.imooc.shiro.service.impl;

import com.imooc.shiro.mapper.RoleMapper;
import com.imooc.shiro.model.Role;
import com.imooc.shiro.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }
}
