package com.imooc.shiro.controller;

import com.imooc.shiro.model.Permission;
import com.imooc.shiro.service.PermissionService;
import com.imooc.shiro.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("findAll")
    public LayuiResult findAll() {
        List<Permission> permissionList = permissionService.findAll();
        Long count = Long.valueOf(permissionList.size());

        return LayuiResult.success(count, permissionList);
    }
}
