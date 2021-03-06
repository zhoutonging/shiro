package com.imooc.shiro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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
    public String userManage() {
        return "user/userManage";
    }

    @RequestMapping("roleManage")
    public String roleManage() {
        return "role/roleManage";
    }

    @RequestMapping("console")
    public String console() {
        return "console";
    }

    @RequestMapping("permissionManage")
    public String permissionManage() {
        return "permission/permissionManage";
    }

    @RequestMapping("errorRole")
    public String errorRole() {
        return "errorRole";
    }

    @RequestMapping("userAdd")
    public String userAdd() {
        return "user/userAdd";
    }

    @RequestMapping("userUpdate")
    public String userUpdate() {
        return "user/userUpdate";
    }
}
