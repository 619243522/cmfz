package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public Admin queryOne(String name, String password) {
        Admin admin = adminMapper.selectOne(new Admin(null, name, password));
       /* if (admin==null){
            throw new RuntimeException("用户名或者密码错误！");
        }*/
        return admin;
    }

    @Override
    public Admin login(String name) {

        return null;
    }
}
