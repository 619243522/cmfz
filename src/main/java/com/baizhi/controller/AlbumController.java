package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dto.AlbumPageDto;
import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("album")
public class AlbumController {
    @Autowired
    AlbumService albumService;

    @RequestMapping("queryAll")
    @ResponseBody
    public AlbumPageDto queryAll(Integer page, Integer rows) {
        AlbumPageDto dto = albumService.queryAll(page, rows);
        return dto;
    }

    @RequestMapping("queryOne")
    @ResponseBody
    public List<Album> queryOne(Integer id) {
        List<Album> albums = new ArrayList<>();
        Album album = albumService.queryOne(id);
        albums.add(album);
        return albums;
    }

    @RequestMapping("addAlbum")
    @ResponseBody
    public void addAlbum(Album album, MultipartFile img, HttpServletRequest request) {
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
            //2.插入album到数据库（img的名字存到album）
            album.setCoverImg(filename);
            albumService.addAlbum(album);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //文件的导出
    @RequestMapping("export")
    @ResponseBody
    public void export(HttpSession session, HttpServletResponse response) {
        String realPath = session.getServletContext().getRealPath("/datagrid/img");
        List<Album> albums = albumService.queryAllAlbum();
        for (Album a : albums) {
            a.setCoverImg(realPath + "/" + a.getCoverImg());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("持名法洲", "音频"), Album.class, albums);
        try {
            String encode = URLEncoder.encode("easypoi.xls", "UTF-8");
            response.setHeader("content-disposition", "attachment;filename=" + encode);
            response.setContentType("application/vnd.ms-excel");
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
