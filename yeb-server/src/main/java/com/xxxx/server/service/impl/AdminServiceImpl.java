package com.xxxx.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xxxx.server.AdminUtil;
import com.xxxx.server.config.security.component.JwtTokenUtil;
import com.xxxx.server.mapper.AdminMapper;
import com.xxxx.server.mapper.AdminRoleMapper;
import com.xxxx.server.mapper.RoleMapper;
import com.xxxx.server.pojo.Admin;
import com.xxxx.server.pojo.AdminRole;
import com.xxxx.server.pojo.RespBean;
import com.xxxx.server.pojo.Role;
import com.xxxx.server.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {
    @Autowired
    private AdminMapper adminMapper;
    // spring-boot-starter-security他主要通过UserDetails来实现的
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private AdminRoleMapper adminRoleMapper;
    /**(第1步).
     * @Description: 登录之后返回token
     * @Author     :
     * @Return     :
     */
    @Override
    public RespBean login(String username, String password, String code, HttpServletRequest request) {
        String captcha = (String) request.getSession().getAttribute("captcha");
        if (StringUtils.isEmpty(code) || !captcha.equalsIgnoreCase(code)){
            return RespBean.error("验证码输入错误, 请重新输入!");
        }
        // 登录
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (null == userDetails||!passwordEncoder.matches(password,userDetails.getPassword())){
            return RespBean.error("用户名或密码不正确");
        }
        if (!userDetails.isEnabled()){
            return RespBean.error("账号被禁用, 请联系管理员");
        }
        // 更新security登录用户对象
        log.info("userDetails.getAuthorities()", userDetails.getAuthorities());
        UsernamePasswordAuthenticationToken authenticationToken = new
                UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        // 生成token
        String token = jwtTokenUtil.generateToken(userDetails);
        Map<String,String> tokenMap = new HashMap<>();
        tokenMap.put("token",token);
        tokenMap.put("tokenHead",tokenHead);
        return RespBean.success("登录成功",tokenMap);
    }

    /**(第2步).
     * @Description: 获取用户
     * @Author     :
     * @Return     :
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",username).eq
                ("enabled",true));
    }


    /**
     * 根据用户id查询角色列表
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        // 把他放在roleMapper里面
        return roleMapper.getRoles(adminId);
    }

    /**
     * 获取操作员
     * @param keywords
     * @return
     */
    @Override
    public List<Admin> getAllAdmins(String keywords) {
        return  adminMapper.getAllAdmins(AdminUtil.getCurrentAdmin().getId(), keywords);
    }

    /**
     * 更新操作员角色
     * @param adminId
     * @param rids
     * @return
     */
    @Override
    @Transactional
    public RespBean updateAdminRole(Integer adminId, Integer[] rids) {
        // 输入的id不能是空的否则会失败
        for (Integer rid : rids) {
            if (rid == null) {
                return RespBean.error("输入角色错误!!");
            }
        }
        // 先删除用户所有角色
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));
        // 再添加角色

        Integer result = adminRoleMapper.addAdminRole(adminId, rids);
        if (rids.length == result) {
            return RespBean.success("更新成功!");
        }
        return RespBean.error("更新失败!");
    }


}
