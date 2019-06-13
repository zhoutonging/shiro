package com.imooc.shiro.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.imooc.shiro.model.Role;
import com.imooc.shiro.mapper.RoleMapper;
import com.imooc.shiro.service.RoleService;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAll() {
        return roleMapper.selectList(new EntityWrapper<>());
    }

}
