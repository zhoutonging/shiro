package com.imooc.shiro.mapper;

import com.imooc.shiro.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionMapper extends JpaRepository<Permission,Integer> {

}
