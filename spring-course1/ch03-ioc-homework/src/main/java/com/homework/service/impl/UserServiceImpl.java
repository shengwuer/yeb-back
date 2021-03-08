package com.homework.service.impl;

import com.homework.dao.UserDao;
import com.homework.domain.SysUser;
import com.homework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//业务层的加@Service
@Service("userService")
public class UserServiceImpl implements UserService {
    //引用类型,在xml文件给属性赋值,要求属性需要有set方法

    @Autowired
    @Qualifier("mysqlDao")
    private UserDao dao = null;


    public void setDao(UserDao user) {
        this.dao = dao;
    }

    @Override
    public void addUser(SysUser user) {
        //处理数据,调用Dao的方法
        //调用dao的方法

        dao.insertUser(user);
    }

}
