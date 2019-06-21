package com.imooc.shiro.dto;

import lombok.Data;

@Data
public class RolePermissionDto {

    //权限路径
    private String url;

    //可以访问权限的角色
    private String name;
}
