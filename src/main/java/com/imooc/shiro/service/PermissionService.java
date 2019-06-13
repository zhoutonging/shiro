package com.imooc.shiro.service;

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

    List<Permission> findAll();
}
