package com.baizhi.mapper;

import com.baizhi.entity.User;
import com.baizhi.entity.UserArea;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {
    public Integer queryUserWeek(int num);

    public List<UserArea> queryUserProvince();
}
