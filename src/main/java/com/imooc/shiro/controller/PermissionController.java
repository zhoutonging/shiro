package com.imooc.shiro.controller;


import com.imooc.shiro.model.Permission;
import com.imooc.shiro.model.Role;
import com.imooc.shiro.service.PermissionService;
import com.imooc.shiro.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@RequestMapping("/permission/")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * 添加父权限
     *
     * @param permission
     * @return
     */
    @GetMapping("save")
    public LayuiResult save(Permission permission) {
        try {
            permission.setDesc(permission.getName());
            permissionService.save(permission);
            return LayuiResult.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("添加成功");
        }
    }

    /**
     * 添加子权限
     *
     * @param permission
     * @return
     */
    @GetMapping("saveSon")
    public LayuiResult saveSon(Permission permission) {
        try {
            permission.setDesc(permission.getName());
            permissionService.saveSon(permission);
            return LayuiResult.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("添加成功");
        }
    }

    /**
     * 查询权限列表
     *
     * @return
     */
    @RequestMapping("findAll")
    public LayuiResult findAll() {
        List<Permission> roleList = permissionService.findAll();
        return LayuiResult.success(roleList);
    }
}

