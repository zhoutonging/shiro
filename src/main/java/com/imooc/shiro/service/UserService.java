package com.imooc.shiro.service;

import com.imooc.shiro.model.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 服务类
 *
 * @author CC
 * @since 2019-06-11
 */
public interface UserService extends IService<User> {

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

    /**
     * 查询用户对应角色
     *
     * @return
     */
    List<Map<String, Object>> selectMaps();
}
