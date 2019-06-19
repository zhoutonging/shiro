package com.imooc.shiro.dto;

import lombok.Data;

import java.util.List;


@Data
public class UserDto {


    private Integer id;
    private String name;
    private String password;
    private String salt;

    /**
     * 角色集合
     */
    private List<RoleDto> roleList;
}
