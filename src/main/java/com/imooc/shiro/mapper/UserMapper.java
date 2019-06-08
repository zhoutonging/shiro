package com.imooc.shiro.mapper;

import com.imooc.shiro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMapper extends JpaRepository<User,Integer> {

    User findByUserName(String userName);
}
