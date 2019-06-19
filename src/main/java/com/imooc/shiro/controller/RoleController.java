package com.imooc.shiro.controller;


import com.imooc.shiro.model.Role;
import com.imooc.shiro.service.RoleService;
import com.imooc.shiro.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 前端控制器
 *
 * @author CC
 * @since 2019-06-11
 */
@RestController
@RequestMapping("/role/")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 查询某个用户所拥有的角色
     *
     * @return
     */
    @RequestMapping("findAll")
    public LayuiResult findAll() {
        List<Role> roleList = roleService.findAll();
        return LayuiResult.success(roleList);
    }

}

