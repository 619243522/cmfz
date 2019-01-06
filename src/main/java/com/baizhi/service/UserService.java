package com.baizhi.service;

import com.baizhi.entity.UserArea;

import java.util.List;

public interface UserService {
    public Integer queryUserWeek(int num);

    public List<UserArea> queryUserProvince();
}
