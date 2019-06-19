package com.imooc.shiro.dto;

import com.imooc.shiro.model.Permission;
import lombok.Data;

import java.util.List;

@Data
public class RoleDto {
    private Long id;
    private String name;
    private String desc;

    /**
     * 权限集合
     */
    List<Permission> permissionList;
}
