package com.imooc.shiro.controller;

import com.alibaba.fastjson.JSONArray;
import com.imooc.shiro.model.User;
import com.imooc.shiro.model.UserRole;
import com.imooc.shiro.service.UserRoleService;
import com.imooc.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 用户登录
     *
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/userLogin")
    public Map<String, Object> userLogin(User user, HttpSession session) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            //1.获取用户信息
            Subject subject = SecurityUtils.getSubject();

            User user1 = userService.findByName(user.getName());

            if (user1 == null) {
                modelMap.put("success", false);
                modelMap.put("msg", "帐号或密码错误");
                return modelMap;
            }

            //运算次数
            int count = 2;
            //加密方式
            String algorithmName = "md5";

            //最终加密结果
            String password = new SimpleHash(algorithmName, user.getPassword(), user1.getSalt(), count).toString();

            //封装用户数据
            UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), password);

            // 3.执行登录方法

            // 只要没有异常就代表登录成功
            subject.login(token);

            session.setAttribute("userName", user1.getName());

            modelMap.put("success", true);
            modelMap.put("msg", "登录成功");
            return modelMap;
        } catch (UnknownAccountException e) {
            modelMap.put("success", false);
            modelMap.put("msg", "帐号或密码错误");
            return modelMap;
        } catch (IncorrectCredentialsException e) {
            modelMap.put("success", false);
            modelMap.put("msg", "帐号或密码错误");
            return modelMap;
        }

    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @RequestMapping("/register")
    public Map<String, Object> register(User user, String roles) {

        Map<String, Object> modelMap = new HashMap<>(16);

        try {
            User user1 = userService.findByName(user.getName());

            if (user1 != null) {
                modelMap.put("success", false);
                modelMap.put("msg", "用户名已存在");
                return modelMap;
            }

            //加密盐值
            String salt = new SecureRandomNumberGenerator().nextBytes().toString();
            //运算次数
            int count = 2;
            //加密方式
            String algorithmName = "md5";
            //最终加密结果
            String password = new SimpleHash(algorithmName, user.getPassword(), salt, count).toString();

            user.setPassword(password);
            user.setSalt(salt);
            userService.save(user);

            //为用户添加角色
            List<String> list = new ArrayList<>();

           User user2 = userService.findByName(user.getName());

            if (roles != null) {
                JSONArray jsonArray = (JSONArray) JSONArray.parse(roles);
                jsonArray.forEach(t -> list.add((String) t));

                UserRole userRole = new UserRole();
                userRole.setUid(user2.getId());

                for (int i = 0; i < list.size(); i++) {
                    userRole.setRid(Long.valueOf(list.get(i)));
                    userRoleService.insert(userRole);
                }
            }

            modelMap.put("success", true);
            modelMap.put("msg", "注册成功");
            return modelMap;

        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("success", false);
            modelMap.put("msg", "注册失败");
            return modelMap;
        }
    }
}
