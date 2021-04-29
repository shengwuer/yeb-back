package com.xxxx.server.controller;

import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.AdminLogParam;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**(第1步).
 * @date ：Created in 2021/2/4 15:35
 * @description：登录
 * @author：
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "登陆之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLogParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername(),adminLoginParam.getPassword(),adminLoginParam.getCode(),request);
    }

    // (第3步).
    @ApiOperation(value = "获取当前登录用户的信息")
    @GetMapping("/admin/info" )
    public Admin getAdminInfo(Principal principal) {
        if (null == principal){
            return null;
        }
        String username =principal.getName();
       Admin admin = adminService.getAdminByUserName(username);
       // 设置密码
       admin.setPassword(null);
       // 设置密码时在设置一个authorities,setRoles()拿到一个adminId,这样返回用户信息就有我们的一个角色
        // 根据用户id获取角色
       admin.setRoles(adminService.getRoles(admin.getId()));
       return admin;
    }
    // (第2步).
    @ApiOperation(value = "退出登陆")
    @PostMapping("/logout")
    public RespBean login(){
        return RespBean.success("注销成功");
    }
}
