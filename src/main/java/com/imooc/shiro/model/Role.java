package com.imooc.shiro.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "desc_")
    private String desc;
}
