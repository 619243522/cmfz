package com.baizhi.service;

import com.baizhi.dto.AlbumPageDto;
import com.baizhi.entity.Album;

import java.util.List;


public interface AlbumService {
    public AlbumPageDto queryAll(Integer curPage, Integer pageSize);

    public Album queryOne(Integer id);

    public void addAlbum(Album album);

    public List<Album> queryAllAlbum();
}
