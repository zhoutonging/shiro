package com.imooc.shiro.controller;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.imooc.shiro.model.User;
import com.imooc.shiro.model.UserRole;
import com.imooc.shiro.service.RoleService;
import com.imooc.shiro.service.UserRoleService;
import com.imooc.shiro.service.UserService;
import com.imooc.shiro.utils.LayuiResult;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.ArrayList;
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
    private UserRoleService userRoleService;

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

    /**
     * 删除用户及角色关联
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById")
    public LayuiResult deleteById(Integer id) {

        try {
            userService.removeById(id);
            return LayuiResult.success("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return LayuiResult.fail("删除失败");

        }
    }

    /**
     * 更新用户信息及角色信息
     *
     * @param user
     * @param oldPassword
     * @param roles
     * @return
     */
        @PostMapping("modifyUserById")
    public LayuiResult modifyUserById(User user, String oldPassword, String roles) {

        if (oldPassword != null && oldPassword != "") {
            //运算次数
            int count = 2;
            //加密方式
            String algorithmName = "md5";
            //最终加密结果
            String password = new SimpleHash(algorithmName, oldPassword, user.getSalt(), count).toString();

            User user1 = userService.selectById(user.getId());
            if (!user1.getPassword().equals(password)) {
                return LayuiResult.fail("旧密码输入不正确,无法修改");
            }
        }

        //判断用户是否进行更改密码操作
        if (user.getPassword() != null && user.getPassword() != "") {

            //加密盐值
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            //运算次数
            int count = 2;
            //加密方式
            String algorithmName = "md5";
            //最终加密结果
            String password = new SimpleHash(algorithmName, user.getPassword(), salt, count).toString();

            user.setSalt(salt);
            user.setPassword(password);
        }

        userService.modifyByUserId(user);

        //为用户添加角色
        List<String> list = new ArrayList<>();

        if (roles != null) {
            JSONArray jsonArray = (JSONArray) JSONArray.parse(roles);
            jsonArray.forEach(role -> list.add(role.toString()));

            UserRole userRole = new UserRole();
            userRole.setUid(user.getId());

            for (int i = 0; i < list.size(); i++) {
                userRole.setRid(Long.valueOf(list.get(i)));
                userRoleService.insert(userRole);
            }
        }
        return LayuiResult.success("更新成功");
    }

}

