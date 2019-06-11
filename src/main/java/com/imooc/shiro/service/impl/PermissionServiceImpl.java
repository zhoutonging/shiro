package com.imooc.shiro.service.impl;

import com.imooc.shiro.mapper.PermissionMapper;
import com.imooc.shiro.model.Permission;
import com.imooc.shiro.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findAll() {
        return permissionMapper.findAll();
    }
}
