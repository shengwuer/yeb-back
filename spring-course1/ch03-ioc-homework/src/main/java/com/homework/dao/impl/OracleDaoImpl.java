package com.homework.dao.impl;

import com.homework.dao.UserDao;
import com.homework.domain.SysUser;
import org.springframework.stereotype.Repository;

@Repository("oracleDao")
public class OracleDaoImpl implements UserDao {
    @Override
    public void insertUser(SysUser user) {
        System.out.println("oracled的insertUser");
    }
}
