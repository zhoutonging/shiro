package com.imooc.shiro.service.impl;

import com.imooc.shiro.model.UserRole;
import com.imooc.shiro.mapper.UserRoleMapper;
import com.imooc.shiro.service.UserRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 服务实现类
 *
 * @author CC
 * @since 2019-06-11
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
