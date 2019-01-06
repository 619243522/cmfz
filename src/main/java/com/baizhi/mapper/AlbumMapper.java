package com.baizhi.mapper;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AlbumMapper extends Mapper<Album> {
    public List<Album> queryAll(@Param("curPage") Integer curPage, @Param("pageSize") Integer pageSize);

    public List<Album> queryAllAlbum();
}
