package com.imooc.shiro.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * 自定义拦截器
 *
 * @author ZHOUTONG
 */
public class UserRolesOrAuthorizationFilter extends AuthorizationFilter {

    @SuppressWarnings({"unchecked"})
    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {

        Subject subject = getSubject(request, response);

        //获取当前访问路径所需要的角色集合
        String[] rolesArray = (String[]) mappedValue;

        //没有角色限制,可以直接访问
        if (rolesArray == null || rolesArray.length == 0) {
            return true;
        }

        //当前subject是roles中任意一个则有权限访问
        Set<String> roles = CollectionUtils.asSet(rolesArray);
        for (String role : roles) {
            if (subject.hasRole(role)) {
                return true;
            }

        }

        return false;

    }
}
