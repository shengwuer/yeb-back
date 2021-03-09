package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.mapper.MenuMapper;
import com.xxxx.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-02-03
 */
public interface IMenuService extends IService<Menu> {


    /**
     * @Description: 根据用户id查询菜单列表
     * @Author     :
     * @Return     :
     */
    List<Menu> getMenusByAdminId();

    /*
    * 根据我们的角色获取菜单列表
    * */
    List<Menu> getMenusWithRole();


}
