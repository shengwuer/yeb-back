package com.xxxx.server.config.security.component;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @date ：Created in 2021/2/28 10:59
 * @description：权限控制
 * 判断用户角色
 * @author：
 */
@Component
public class CustomUrlDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute>
            // configAttributes是一个集合
            configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        // 把configAttributes循环一下
        for (ConfigAttribute configAttribute : configAttributes) {
            // 当前url所需角色. (configAttributes其实是当前url所需要的一个角色)
            String needRole = configAttribute.getAttribute();
            // 判断角色是否登录即可访问角色 , 此角色在CustomFilter中设置. (之前准备了一个"ROLE_LOGIN")
            if ("ROLE_LOGIN".equals(needRole)){
                // 如果是一致, 再去判断是否登录 , 判断是不是AnonymousAuthenticationToken
                if (authentication instanceof AnonymousAuthenticationToken){
                    // 如果是AnonymousAuthenticationToken这个就表示还没有登录, 所以就抛个异常(属于这就是没登录)
                    throw new AccessDeniedException("尚未登录,请登录!");
                }else{
                    // 否则直接返回
                    return;
                }
            }
            // 判断用户角色是否为url所需角色(通过authentication.getAuthorities()拿到一个集合)
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            // 循环authorities这个集合
            for (GrantedAuthority authority : authorities) {
                // 判断authority.getAuthority()里面的额一个角色是否一致
                if (authority.getAuthority().equals(needRole)){
                    // 如果一致就返回
                    return;
                }
            }
        }
        // 如果还是不行就抛异常(因为不可能是没登录的了,所有肯定是权限问题)
        throw new AccessDeniedException("权限不足, 请联系管理员!");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
