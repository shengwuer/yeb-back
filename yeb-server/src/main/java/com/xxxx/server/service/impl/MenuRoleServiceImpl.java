package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.mapper.MenuRoleMapper;
import com.xxxx.server.pojo.MenuRole;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IMenuRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhoubin
 * @since 2021-02-03
 */
@Slf4j
@Service
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {

    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 更新角色菜单
     * @param rid
     * @param mids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        for (Integer mid : mids) {
            if(mid == null) {
                return RespBean.error("更新失败! ");
            }

        }
          // 先删除全部菜单权限(
        // 因为是根据角色id(rid)去删除的, 而不是根据menuRole表的主键id删除)
        // 所以用Wrapper<>()方法传入rid
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid", rid));
        // 删除了需要删除的菜单权限然后更新
        if (mids == null || mids.length == 0) {
            return RespBean.success("更新成功! ");
        }
        // 如果菜单id(mids)不为null和长度不为于0, 就表示创了一些菜单id(mids)过来就要去更新
        // 然后在批量添加角色的菜单权限
        Integer result = menuRoleMapper.insertRecord(rid, mids);
        log.info("g更新角色菜单权限= ", result);
        // 判断更新成功了吗
        if (result == mids.length) {
            return RespBean.success("更新成功!");
        }
        // 否则更新失败
        return RespBean.success("更新失败!");
    }
}
