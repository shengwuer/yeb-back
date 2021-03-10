package com.homework.dao.impl;

import com.homework.dao.UserDao;
import com.homework.domain.SysUser;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository("mysqlDao")
public class UserDaoImpl implements UserDao {
    private SysUser sysUser;

    @Override
    public String toString() {
        return "UserDaoImpl{" +
                "sysUser=" + sysUser +
                '}';
    }

    @Override
    public void insertUser(SysUser user){
        System.out.println("user插入到mysql数据库");
    }
}
