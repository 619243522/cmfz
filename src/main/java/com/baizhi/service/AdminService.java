package com.baizhi.service;

import com.baizhi.entity.Admin;

public interface AdminService {
    public Admin queryOne(String name, String password);


    Admin login(String name);
}
