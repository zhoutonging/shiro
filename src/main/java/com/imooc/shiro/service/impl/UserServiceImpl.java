package com.imooc.shiro.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.imooc.shiro.model.User;
import com.imooc.shiro.mapper.UserMapper;
import com.imooc.shiro.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 服务实现类
 *
 * @author CC
 * @since 2019-06-11
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByName(String userName) {

        return userMapper.findByName(userName);
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public List<User> findAll() {
        return userMapper.selectList(new EntityWrapper<>());
    }

    @Override
    public List<Map<String, Object>> selectMaps() {
        return userMapper.selectMaps();
    }
}
