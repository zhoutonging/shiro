package com.imooc.shiro.mapper;

import com.imooc.shiro.model.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author CC
 * @since 2019-06-11
 */
public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 查询某个用户所拥有的角色
     *
     * @param userId
     * @return
     */
    List<Map<String, Object>> findByUserId(Integer userId);
}
