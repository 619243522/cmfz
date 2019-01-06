package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.List;

public interface ChapterService {
    public void addChapter(Chapter chapter);

    public List<Chapter> queryAll();
}
