package com.imooc.shiro.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.imooc.shiro.model.User;
import com.imooc.shiro.service.RoleService;
import com.imooc.shiro.service.UserService;
import com.imooc.shiro.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
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
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 查询用户列表
     *
     * @return
     */
    @RequestMapping("findAll")
    public LayuiResult findAll() {
        List<User> userList = userService.selectList(new EntityWrapper<>());
        return LayuiResult.success(userList);
    }

    /**
     * 查询用户列表以及拥有角色
     *
     * @return
     */
    @RequestMapping("findByUserAndRole")
    public LayuiResult findByUserAndRole() {
        List<Map<String, Object>> mapList = userService.selectMaps();

        return LayuiResult.success(mapList);
    }

}

