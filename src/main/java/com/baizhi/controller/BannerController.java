package com.baizhi.controller;

import com.baizhi.dto.BannerPageDto;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    BannerService bannerService;

    @RequestMapping("/queryByPage")
    public BannerPageDto queryByPage(int page, int rows) {
        BannerPageDto dto = bannerService.queryByPage(page, rows);
        return dto;
    }

    @RequestMapping("/insertBanner")
    public void insertBanner(Banner banner, MultipartFile img, HttpServletRequest request) {
        //1.处理文件上传
        //根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/datagrid/img");
        //图片名称
        String filename = img.getOriginalFilename();
        //目标文件
        File file = new File(realPath + "/" + filename);
        //调用img工具类开始文件上传
        try {
            img.transferTo(file);
            //2.插入banner到数据库（img的名字存到banner）
            banner.setImgPath(filename);
            bannerService.addBanner(banner);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/deleteBanner")
    public void deleteBanner(Integer id) {
        bannerService.deleteBanner(id);
    }

    @RequestMapping("/updateBanner")
    public void updateBanner(Banner banner) {
        System.out.println(banner);
        bannerService.update(banner);
    }
}
