package com.imooc.shiro.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @RequestMapping("index")
    public Model index(Model model) {
        model.addAttribute("index");
        return model;
    }

    @RequestMapping("login")
    public Model login(Model model) {
        model.addAttribute("login");
        return model;
    }

    @RequestMapping("reg")
    public Model reg(Model model) {
        model.addAttribute("reg");
        return model;
    }

    @RequestMapping("userManage")
    public Model userManage(Model model) {
        model.addAttribute("user/userManage");
        return model;
    }
}
