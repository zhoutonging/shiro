package com.imooc.shiro.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.imooc.shiro.model.Permission;
import com.imooc.shiro.mapper.PermissionMapper;
import com.imooc.shiro.service.PermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 服务实现类
 *
 * @author CC
 * @since 2019-06-11
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<Permission> findAll() {
        return permissionMapper.selectList(new EntityWrapper<>());
    }
}
