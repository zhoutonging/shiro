package com.imooc.shiro.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "role_permission")
public class RolePermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "rid")
    private Integer rid;

    @Column(name = "pid")
    private Integer pid;
}
