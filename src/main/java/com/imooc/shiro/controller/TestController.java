package com.imooc.shiro.controller;

import com.imooc.shiro.dto.UserDto;
import com.imooc.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public Map<String, Object> test(String name) {

        Map<String, Object> modelMap = new HashMap<>(16);
        UserDto userDto = userService.findAllUserInfoByName(name);

        modelMap.put("roleDtoList", userDto);
        return modelMap;
    }
}
