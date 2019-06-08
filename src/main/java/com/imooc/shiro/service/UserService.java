package com.imooc.shiro.service;

import com.imooc.shiro.model.User;

public interface UserService {

    /**
     * 根据用户名查询用户是否存在
     * @param userName
     * @return
     */
    User findByName(String userName);

    void save(User user);
}
