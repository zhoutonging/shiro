package com.imooc.shiro.shiro;

import com.imooc.shiro.model.User;
import com.imooc.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm
 *
 * @author Lmr
 *
 */
public class UserRealm extends AuthorizingRealm {

	@Autowired
	private UserService userService;

	/**
	 * 执行授权逻辑
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		System.out.println("执行授权逻辑");

		//给资源进行授权
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		//添加在ShiroConfig类里写的授权字符串
//		info.addStringPermission("user:add");

		//获取当前登录用户
		Subject subject = SecurityUtils.getSubject();
		User user = (User) subject.getPrincipal();

		//到数据库查询当前登录用户的授权字符串
//		User dbUser = userService.findById(user.getId());
//
//		info.addStringPermission(dbUser.getPerms());

		return info;
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
