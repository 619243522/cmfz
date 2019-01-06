package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.UUID;

@Controller
@RequestMapping("chapter")
public class ChapterController {

    @Autowired
    ChapterService chapterService;

    @RequestMapping("addChapter")
    @ResponseBody
    public void addChapter(Chapter chapter, MultipartFile music, HttpServletRequest request) {
        BigDecimal size = new BigDecimal(music.getSize());
        BigDecimal mod = new BigDecimal(1024);
        size = size.divide(mod).divide(mod).setScale(2, BigDecimal.ROUND_HALF_UP);
        //1.处理文件上传
        //根据相对路径获取绝对路径
        String realPath = request.getSession().getServletContext().getRealPath("/datagrid/music");
        //图片名称
        String filename = music.getOriginalFilename();
        //目标文件
        File file = new File(realPath + "/" + filename);
        //计算音频时长大小
        long length = 0l;
        Encoder encoder = new Encoder();
        //调用img工具类开始文件上传
        try {
            String id = UUID.randomUUID().toString().replace("-", "");
            chapter.setId(id);
            music.transferTo(file);
            length = encoder.getInfo(file).getDuration();
            //2.插入chapter到数据库（music的名字存到chapter）
            chapter.setUrl(filename);
            chapter.setSize(size + "MB");
            chapter.setDuration(length / 1000 / 60 + "分" + length / 1000 % 60 + "秒");
            chapterService.addChapter(chapter);
            System.out.println(chapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("down")
    public void down(String fileName, HttpSession session, HttpServletResponse response) {
        String realPath = session.getServletContext().getRealPath("/datagrid/music");
        File srcFile = new File(realPath + "/" + fileName);
        byte[] bs = null;
        ServletOutputStream out = null;
        try {
            bs = FileUtils.readFileToByteArray(srcFile);
            // 设置响应头信息，以附件的形式下载
            response.setHeader("content-disposition", "attchment;filename=" + URLEncoder.encode(fileName, "utf-8"));
            out = response.getOutputStream();
            //文件下载
            out.write(bs);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
