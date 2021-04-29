package com.xxxx.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xxxx.server.pojo.*;
import com.xxxx.server.service.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Wrapper;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO : 权限组
 *
 * @Created : by 湖南爱豆
 * @Date ： 2021/4/25 17:03
 * @Author : 谢迪
 */
@RestController
@RequestMapping("/system/basic/permission")
public class PermissionController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private IMenuRoleService menuRoleService;

    @Autowired
    private IEmployeeEcService employeeEcService;

    @ApiOperation("查询所有角色")
    @GetMapping("/")
    public List<Role> getAllRoles() {
        return roleService.list();
    }

    @ApiOperation("添加角色")
    @PostMapping("/role")
    public RespBean addRole(@RequestBody Role role) {
         if(role.getName().length() == 0 || role.getNameZh().length() == 0) {
             return RespBean.error("角色不能为空!");
        }
        // springsecurity它的规定: 角色都是以ROLE_开头的不然就不能添加, 所以先判断有没有ROLE_
        if (!role.getName().startsWith("ROLE_")) {
            // 如果不是的话给它设置一个ROLE_
            role.setName("ROLE_" + role.getName());
        }
        if (roleService.save(role)) {
            return RespBean.success("添加成功!");

        }
        return RespBean.error("添加失败!");
    }

    @ApiOperation("删除角色")
    @DeleteMapping("/role/{rid}")
    public RespBean deleteRoleByid(Long rid) {
        if (roleService.removeById(rid)) {
            return RespBean.success("删除成功!");
        }
        return RespBean.error("删除失败!");
    }

    @ApiOperation(value = "查询所有菜单")
    @GetMapping("/menus")
    public List<Menu> getAllMenus() {
        return menuService.getAllMenus();
    }

    @ApiOperation(value = "根据角色id查询查单id")
    @GetMapping("/mid/{rid})")
    public List<Integer> getMidsByRid(@PathVariable Integer rid) {

        // list本质上查的是MenuRole得对象List<MenuRole> 但是最后返回一个Integer菜单id所以用stream流转了一下拿到菜单id(mid)然后转成list
        // list()条件放rid
        return menuRoleService.list(new QueryWrapper<MenuRole>()
                // 所需的字段
                .eq("rid", rid))
                // list本质上查的是MenuRole得对象List<MenuRole> 但是最后返回一个Integer菜单id所以用stream流转了一下拿到菜单id(mid)
                .stream().map(MenuRole::getMid)
                // 有了菜单id在把它转成list
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "更新角色菜单")
    @PutMapping("/")
    public RespBean updateMenuRole(Integer rid, Integer[] mids) {
        return menuRoleService.updateMenuRole(rid, mids);
    }

}