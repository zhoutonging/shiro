package com.imooc.shiro.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.imooc.shiro.dto.UserDto;
import com.imooc.shiro.model.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author CC
 * @since 2019-06-11
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名称查询信息
     *
     * @param name
     * @return
     */
    User findByName(String name);

    /**
     * 查询用户对应角色
     *
     * @return
     */
    List<Map<String, Object>> selectMaps();

    UserDto findUserDtoByName(String name);
}
