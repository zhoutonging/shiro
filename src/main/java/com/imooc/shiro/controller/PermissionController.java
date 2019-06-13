package com.imooc.shiro.controller;


import com.imooc.shiro.model.Permission;
import com.imooc.shiro.model.Role;
import com.imooc.shiro.service.PermissionService;
import com.imooc.shiro.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前端控制器
 *
 * @author CC
 * @since 2019-06-11
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("findAll")
    public LayuiResult findAll() {
        List<Permission> roleList = permissionService.findAll();
        return LayuiResult.success(roleList);
    }
}

