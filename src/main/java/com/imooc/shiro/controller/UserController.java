package com.imooc.shiro.controller;

import com.imooc.shiro.model.User;
import com.imooc.shiro.service.UserService;
import com.imooc.shiro.utils.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("findAll")
    public LayuiResult findAll() {
        List<User> userList = userService.findAll();

        Long count = Long.valueOf(userList.size());

        return LayuiResult.success(count, userList);
    }
}
