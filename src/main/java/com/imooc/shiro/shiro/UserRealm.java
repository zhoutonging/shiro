package com.imooc.shiro.shiro;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.imooc.shiro.model.Role;
import com.imooc.shiro.model.User;
import com.imooc.shiro.service.RoleService;
import com.imooc.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义Realm
 *
 * @author Lmr
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    /**
     * 执行授权逻辑
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑");
        User user = (User) principals.getPrimaryPrincipal();

        List<String> permissions = new ArrayList<String>();
        List<String> roles = new ArrayList<>();

        if ("admin".equals(user.getName())) {
            //拥有所有权限
            permissions.add("*:*");

            //查询所有角色
            List<Role> roleList = roleService.selectList(new EntityWrapper<Role>());

            for (Role role : roleList) {
                roles.add(role.getDesc());
            }

        }

        return null;
    }

    /**
     * 执行认证逻辑
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
        System.out.println("执行认证逻辑");

        // 编写Shiro判断逻辑,判断用户名和密码
        UsernamePasswordToken token = (UsernamePasswordToken) arg0;

        // 数据库的用户名和密码
        User user = userService.findByName(token.getUsername());

        if (user == null) {
            // 用户名不存在
            return null;// Shiro底层会抛出UnknownAccountException
        }

//		2.判断密码
        return new SimpleAuthenticationInfo(user, user.getPassword(), "");
    }

}
