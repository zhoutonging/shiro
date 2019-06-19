package com.imooc.shiro.shiro;

import com.imooc.shiro.dto.RoleDto;
import com.imooc.shiro.dto.UserDto;
import com.imooc.shiro.model.Permission;
import com.imooc.shiro.model.User;
import com.imooc.shiro.service.RoleService;
import com.imooc.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
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
     * 用户进行授权时调用
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("执行授权逻辑");
        User user = (User) principals.getPrimaryPrincipal();
        UserDto userDto = userService.findAllUserInfoByName(user.getName());

        //角色
        List<String> stringRoleList = new ArrayList<>();
        //权限
        List<String> stringPermissionList = new ArrayList<>();

        List<RoleDto> roleDtoList = userDto.getRoleList();
        for (RoleDto roleDto : roleDtoList) {
            stringRoleList.add(roleDto.getName());
            List<Permission> permissionList = roleDto.getPermissionList();

            for (Permission permission : permissionList) {
                if (permission != null) {
                    stringPermissionList.add(permission.getName());
                }
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRoles(stringRoleList);
        simpleAuthorizationInfo.addStringPermissions(stringPermissionList);

        return simpleAuthorizationInfo;
    }

    /**
     * 用户登录时会调用认证
     *
     * @param arg0
     * @return
     * @throws AuthenticationException
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
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }

}
