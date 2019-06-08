package com.imooc.shiro.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "user_role")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "rid")
    private Integer rid;
}
