package com.imooc.shiro.mapper;

import com.imooc.shiro.dto.RoleDto;
import com.imooc.shiro.model.Role;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;
import java.util.Map;

public interface RoleMapper extends BaseMapper<Role> {

    /**
     * 根据userId查询角色集合
     *
     * @param userId
     * @return
     */
    @Select("SELECT  a.id id,b.name name,b.`desc` `desc`" +
            " FROM user_role a"                           +
            " LEFT JOIN role b ON a.rid=b.id"             +
            " WHERE a.uid=#{userId}")
    @Results(
            value = {
                @Result(id=true,property = "id",column = "id"),
                @Result(property = "name",column = "name"),
                @Result(property = "desc",column = "desc"),
                @Result(property = "permissionList",column = "id",
                        many =@Many(select = "com.imooc.shiro.mapper.PermissionMapper.findPermissionListByRoleId",
                                fetchType = FetchType.DEFAULT)
                )
            }
    )
    List<RoleDto> findRoleListByUserId(Integer userId);
}
