package com.imooc.shiro.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "permission")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "desc_")
    private String desc;

    @Column(name = "url")
    private String url;
}
