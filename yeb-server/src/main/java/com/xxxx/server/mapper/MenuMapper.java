package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Menu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2021-02-03
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * @Description: 根据用户id查询菜单列表
     * @Author     :
     * @Return     :
     */
    List<Menu> getMenusByAdminId(Integer id);

    /*
     * 根据我们的角色获取菜单列表
     *
     * */
    List<Menu> getMenusWithRole();
}
