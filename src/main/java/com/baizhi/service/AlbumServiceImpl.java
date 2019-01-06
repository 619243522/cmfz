package com.baizhi.service;

import com.baizhi.dto.AlbumPageDto;
import com.baizhi.entity.Album;
import com.baizhi.mapper.AlbumMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    AlbumMapper albumMapper;

    @Override
    public AlbumPageDto queryAll(Integer curPage, Integer pageSize) {
        AlbumPageDto dto = new AlbumPageDto();
        //设置总行数
        dto.setTotal(albumMapper.selectCount(null));
        //设置每行条数
        /*PageHelper.startPage(curPage,pageSize);*/
        List<Album> albums = albumMapper.queryAll(curPage, pageSize);
        dto.setRows(albums);
        return dto;
    }

    @Override
    public Album queryOne(Integer id) {
        Album album = albumMapper.selectByPrimaryKey(id);
        return album;
    }

    @Override
    public void addAlbum(Album album) {
        albumMapper.insertSelective(album);
    }

    @Override
    public List<Album> queryAllAlbum() {
        List<Album> albums = albumMapper.queryAllAlbum();
        return albums;
    }
}
