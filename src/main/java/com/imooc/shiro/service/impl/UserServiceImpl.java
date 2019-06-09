package com.imooc.shiro.service.impl;

import com.imooc.shiro.mapper.UserMapper;
import com.imooc.shiro.model.User;
import com.imooc.shiro.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String userName) {
        return userMapper.findByUserName(userName);
    }

    @Override
    public void save(User user) {
        userMapper.save(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }
}
