package com.imooc.shiro.shiro;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

	/**
	 * 创建ShiroFilterFactoryBean
	 */
	@Bean
	public ShiroFilterFactoryBean getShiroFilterFactoryBean(
			@Qualifier("securityManager") DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

		// 设置安全管理器
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 添加Shiro内置过滤器

		/**
		 * Shiro内置过滤器,可以实现权限的拦截 常用的过滤器: 
		 * 		anon:无需认证(登录)可以访问 
		 *      anthc:必须认证才可以访问
		 * 		user:如果使用remember的功能可以访问 
		 *		perms:该资源必须得到资源权限才可以访问 
		 *		role:该资源必须得到角色权限才可以访问
		 */

		Map<String, String> filterMap = new HashMap<String, String>();

		// 过滤指定页面
//		filterMap.put("/add", "authc");
//		filterMap.put("/update", "authc");

		// 放行以下页面
		filterMap.put("/login", "anon");
		filterMap.put("/userLogin", "anon");
		filterMap.put("/register", "anon");
		filterMap.put("/reg", "anon");
		filterMap.put("/static/**", "anon");

		//退出登录
		filterMap.put("/logout", "logout");

		//授权过滤器
		//注意:当前授权拦截后,Shiro会自动跳转到未授权页面	
//		filterMap.put("/add", "perms[user:add]");	//授权字符串
//		filterMap.put("/delete", "perms[user:delete]");	//授权字符串
		
		// 过滤路径为根目录下的所有页面
		filterMap.put("/**", "authc");
		
		//修改未登录就访问的默认登录页
		shiroFilterFactoryBean.setLoginUrl("/login");
		
		//设置未授权提示页面
		shiroFilterFactoryBean.setUnauthorizedUrl("/noAuth");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);

		return shiroFilterFactoryBean;
	}

	/**
	 * 创建DefaultWebSecurityManager
	 */
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();

		// 关联Realm
		securityManager.setRealm(userRealm);
		return securityManager;
	}

	/**
	 * 创建Realm
	 */
	@Bean(name = "userRealm")
	public UserRealm getRealm() {
		
		return new UserRealm();
	}

}