package com.baizhi.controller;

import com.baizhi.conf.CreateValidateCode;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @RequestMapping("/login")
    public String login(String enCode, String name, String password, HttpSession session) {
        Object code = session.getAttribute("code");
        if (!enCode.equals(code)) {
            throw new RuntimeException("验证码错误！");
        } else {
            Admin admin = adminService.queryOne(name, password);
            session.setAttribute("ok", admin);
            return "redirect:/main/main.jsp";
        }
    }

    @RequestMapping("/createImg")
    public void createImg(HttpSession session, HttpServletResponse response) throws IOException {
        // 调用工具类，生成图片上的随机字符
        CreateValidateCode cvc = new CreateValidateCode();
        String code = cvc.getCode();

        // 存 随机字符到  --- session
        session.setAttribute("code", code);

        // 调用工具类，把生成的随机字符，使用输出流往client输出成图片
        cvc.write(response.getOutputStream());
    }

    @RequestMapping("login1")
    @ResponseBody
    public Map<String, Object> login1(String name, String password) {
        Map<String, Object> map = new HashMap<>();
        System.out.println(name);
        System.out.println(password);
        Admin admin = adminService.queryOne(name, password);
        if (admin == null) {
            map.put("status", false);
            map.put("message", "账号或密码错误");
        } else {
            map.put("status", true);
        }
        return map;

    }
}
