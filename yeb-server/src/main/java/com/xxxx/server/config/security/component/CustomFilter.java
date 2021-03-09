package com.xxxx.server.config.security.component;

import com.xxxx.server.pojo.Menu;
import com.xxxx.server.pojo.Role;
import com.xxxx.server.service.IAdminService;
import com.xxxx.server.service.IMenuService;
import com.xxxx.server.service.impl.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

/**
 * @date ：Created in 2021/2/27 15:01
 * @description：权限控制
 *  根据请求url分析请求所需的角色
 *
 * @author：
 */
@Component
public class CustomFilter implements FilterInvocationSecurityMetadataSource {
    // 根据角色去查菜单列表所有肯定要要用到Menus
    @Autowired
    private IMenuService menuService;
    // 创建一个AntPathMatcher类的对象antPathMatcher
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 首先获取请求的url用((FilterInvocation) object).getFullRequestUrl()获取请求url
        String requestUrl = ((FilterInvocation) object).getFullRequestUrl();
        // 然后获取请求到url就可以用MenuService
        List<Menu> menus = menuService.getMenusWithRole();
        // 把 menus循环判断一下
        for (Menu menu : menus){
            // 判断请求url与Menu里面的url的菜单角色是否匹配
            // 通过antPathMatcher对象调用match()
            if (antPathMatcher.match(menu.getUrl(),requestUrl)){
                // 需要通过 menu.getRoles()拿到他的角色然后我们用.stream().map(Role::getName).toArray(String[]::new)拿到一个数组
                String[] str = menu.getRoles().stream().map(Role::getName).toArray(String[]::new);
                // 直接返回SecurityConfig.createList()把数组str放进去
                return SecurityConfig.createList(str);
            }
        }
        // 没有匹配的url,默认登录即可访问 (匹配不上就分配一个ROLE_LOGIN角色，ROLE_LOGIN相当于登陆之后的一个角色)
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
