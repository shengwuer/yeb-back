package com.xxxx.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xxxx.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhoubin
 * @since 2021-02-03
 */
public interface RoleMapper extends BaseMapper<Role> {

    /*
     * 根据用户id查询角色列表
     *
     * */
    List<Role> getRoles(Integer adminId);
}
