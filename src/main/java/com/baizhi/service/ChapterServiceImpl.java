package com.baizhi.service;

import com.baizhi.entity.Chapter;
import com.baizhi.mapper.ChapterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    ChapterMapper chapterMapper;

    @Override
    public void addChapter(Chapter chapter) {
        chapterMapper.insertSelective(chapter);
    }

    @Override
    public List<Chapter> queryAll() {
        List<Chapter> chapters = chapterMapper.selectAll();
        return chapters;
    }

}
