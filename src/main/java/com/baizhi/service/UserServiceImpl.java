package com.baizhi.service;

import com.baizhi.entity.UserArea;
import com.baizhi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public Integer queryUserWeek(int num) {
        Integer integer = userMapper.queryUserWeek(num);
        return integer;
    }

    @Override
    public List<UserArea> queryUserProvince() {
        List<UserArea> userAreas = userMapper.queryUserProvince();
        return userAreas;
    }
}
