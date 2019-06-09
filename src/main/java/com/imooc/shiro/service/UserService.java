package com.imooc.shiro.service;

import com.imooc.shiro.model.User;

import java.util.List;

public interface UserService {

    /**
     * 根据用户名查询用户是否存在
     *
     * @param userName
     * @return
     */
    User findByName(String userName);

    /**
     * 注册用户
     *
     * @param user
     */
    void save(User user);

    /**
     * 查询用户列表
     *
     * @return
     */
    List<User> findAll();
}
