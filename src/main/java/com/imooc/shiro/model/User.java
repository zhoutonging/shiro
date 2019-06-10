package com.imooc.shiro.model;

import lombok.Data;

import javax.persistence.*;

/**
 * 用户模型
 *
 * @author ZHOUTONG
 */
@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "salt")
    private String salt;
}
