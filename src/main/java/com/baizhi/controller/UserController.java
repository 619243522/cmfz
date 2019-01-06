package com.baizhi.controller;


import com.baizhi.entity.UserArea;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("queryUserOneWeek")
    public Map<String, Object> queryUserOneWeek() {
        Map<String, Object> map = new HashMap<>();
        Integer userWeek = userService.queryUserWeek(7);
        Integer userWeek1 = userService.queryUserWeek(14);
        Integer userWeek2 = userService.queryUserWeek(21);
        map.put("counts", new Integer[]{userWeek, userWeek1, userWeek2});
        map.put("intervals", new String[]{"一周", "两周", "三周"});
        return map;
    }

    @ResponseBody
    @RequestMapping("queryUserProvince")
    public List<UserArea> queryUserProvince() {
        List<UserArea> userAreas = userService.queryUserProvince();
        System.out.println(userAreas);
        return userAreas;
    }
}
