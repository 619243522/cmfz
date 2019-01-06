package com.baizhi.dto;

import com.baizhi.entity.Album;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlbumPageDto implements Serializable {
    private Integer total;//总数量
    private List<Album> rows;//每页数据
}
