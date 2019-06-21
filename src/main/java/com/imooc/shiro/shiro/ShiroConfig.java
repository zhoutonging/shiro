package com.imooc.shiro.shiro;

import com.imooc.shiro.dto.RolePermissionDto;
import com.imooc.shiro.model.Permission;
import com.imooc.shiro.service.PermissionService;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Autowired
    private PermissionService permissionService;

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

        List<RolePermissionDto> rolePermissionDtoList = permissionService.findRolePermission();


        /**
         * 设置自定义filter
         */
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("roleOrFilter", new UserRolesOrAuthorizationFilter());

        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        // 放行以下页面
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/userLogin", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        filterChainDefinitionMap.put("/reg", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");

        //查询角色可以访问的权限
        for (RolePermissionDto rolePermissionDto : rolePermissionDtoList) {
            filterChainDefinitionMap.put(rolePermissionDto.getUrl(), "roleOrFilter[" + rolePermissionDto.getName() + "]");
        }


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
        //使用自定义的cacheManager
        securityManager.setCacheManager(redisCacheManager());
        return securityManager;
    }

    @Bean
    public UserRealm userRealm() {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }

    /**
     * 配置redisManager
     */
    public RedisManager getRedisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost("localhost");
        redisManager.setPort(6379);
        return redisManager;
    }

    /**
     * 配置具体的cache实现类
     *
     * @return
     */
    public RedisCacheManager redisCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(getRedisManager());
        //设置过期时间,单位S
        redisCacheManager.setExpire(20);
        return redisCacheManager;
    }

}
