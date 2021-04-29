package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.AdminUtil;
import com.xxxx.server.mapper.MenuMapper;
import com.xxxx.server.mapper.MenuRoleMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.Menu;
import com.xxxx.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-02-03
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    /**
     * @Description: 根据用户查id询菜单列表
     * @Author :
     * @Return :
     */
    @Override
    public List<Menu> getMenusByAdminId() {
        // 获取用户id
        Integer adminId = AdminUtil.getCurrentAdmin().getId();
        // 从redis获取菜单数据
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        // 先去redis去取数据
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + adminId);
        // 判断数据为空吗? 如果为空就去数据库获取
        if (CollectionUtils.isEmpty(menus)) {
            menus = menuMapper.getMenusByAdminId(adminId);
            // 去数据库获取然后将数据设置存到Redis中
            valueOperations.set("menu_" + adminId, menus);
        }
        return menus;
    }

    /**
     *  根据我们的角色获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenusWithRole() {
        return menuMapper.getMenusWithRole();
    }

    /**
     * 查询所有菜单(包括子菜单)
     * @return
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }


}
