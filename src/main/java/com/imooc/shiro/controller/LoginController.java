package com.imooc.shiro.controller;

import com.imooc.shiro.model.User;
import com.imooc.shiro.service.UserService;
import org.apache.shiro.codec.CodecException;
import org.apache.shiro.crypto.UnknownAlgorithmException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

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

        User user1 = userService.findByName(user.getUserName());

        //运算次数
        int count = 2;
        //加密方式
        String algorithmName = "md5";

        //最终加密结果
        String password = new SimpleHash(algorithmName, user.getPassword(), user1.getSalt(), count).toString();

        //封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), password);

        // 3.执行登录方法

            // 只要没有异常就代表登录成功
            subject.login(token);

            session.setAttribute("userName", user1.getUserName());

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
    public Map<String, Object> register(User user) {
        Map<String, Object> modelMap = new HashMap<>(16);

        try {
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
