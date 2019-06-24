package com.imooc.shiro.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.imooc.shiro.dto.RoleDto;
import com.imooc.shiro.dto.UserDto;
import com.imooc.shiro.model.Role;
import com.imooc.shiro.model.User;
import com.imooc.shiro.mapper.UserMapper;
import com.imooc.shiro.model.UserRole;
import com.imooc.shiro.service.RoleService;
import com.imooc.shiro.service.UserRoleService;
import com.imooc.shiro.service.UserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.shiro.crypto.hash.SimpleHash;
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

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

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

    @Override
    public UserDto findAllUserInfoByName(String username) {
        UserDto userDto = userMapper.findUserDtoByName(username);

//        用户的角色集合
        List<RoleDto> roleList = roleService.findRoleListByUserId(userDto.getId());

        userDto.setRoleList(roleList);
        return userDto;
    }

    @Override
    public User findSimpleUserInfoById(Integer userId) {
        return userMapper.selectById(userId);
    }

    @Override
    public void removeById(Integer id) {
        userRoleService.delete(new EntityWrapper<UserRole>().eq("uid", id));
        this.deleteById(Long.valueOf(id));
    }

    @Override
    public void modifyByUserId(User user, String password) {


    }
}
