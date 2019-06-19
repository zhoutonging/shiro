package com.imooc.shiro.shiro;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        System.out.println("执行 ShiroFilterFactoryBean shiroFilter");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //必须设置securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //认证的接口如果没有登录则会跳到登录页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        //登录后无权限调用的接口
        shiroFilterFactoryBean.setUnauthorizedUrl("/errorRole");

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 放行以下页面
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/userLogin", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        filterChainDefinitionMap.put("/reg", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");

        filterChainDefinitionMap.put("/role/**", "roles[admin]");

        //退出登录
        filterChainDefinitionMap.put("/logout", "logout");
        // 过滤路径为根目录下的所有页面
        filterChainDefinitionMap.put("/**", "authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        return securityManager;
    }

    @Bean
    public UserRealm userRealm(){
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

}
