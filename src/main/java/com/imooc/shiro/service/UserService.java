package com.imooc.shiro.service;

import com.imooc.shiro.dto.UserDto;
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
     * 注册用户
     *
     * @param user
     */
    void save(User user);

    /**
     * 删除用户及关联角色
     *
     * @param id
     */
    void removeById(Integer id);

    /**
     * 根据用户id修改信息及角色
     *
     * @param user
     * @param password 修改前密码
     */

    void modifyByUserId(User user, String password);

    /**
     * 根据用户名查询用户是否存在
     *
     * @param userName
     * @return
     */
    User findByName(String userName);


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

    /**
     * 获取用户全部信息,包括角色权限
     *
     * @param username
     * @return
     */
    UserDto findAllUserInfoByName(String username);

    /**
     * 根据用户Id获取用户基本信息
     *
     * @param userId
     * @return
     */
    User findSimpleUserInfoById(Integer userId);

}
