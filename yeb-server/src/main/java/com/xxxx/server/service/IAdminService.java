package com.xxxx.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Role;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.ListUI;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author zhoubin
 * @since 2021-02-03
 */
public interface IAdminService extends IService<Admin> {


    /**
     * @Description: 登陆之后返回token
     * @Author :
     * @Return :
     */
    RespBean login(String username, String password, String code, HttpServletRequest request);

    /**
     * @Description:根据用户获取用户
     * @Author :
     * @Return :
     */
    Admin getAdminByUserName(String username);


    /**
     * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);

    /**
     * 获取操作员
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmins(String keywords);

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    RespBean updateAdminRole(Integer adminId, Integer[] rids);
}
