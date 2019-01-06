package com.baizhi.service;

import com.baizhi.dto.BannerPageDto;
import com.baizhi.entity.Banner;
import com.baizhi.mapper.BannerMapper;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerMapper bannerMapper;

    @Override
    public BannerPageDto queryByPage(int curPage, int pageSize) {
        BannerPageDto dto = new BannerPageDto();
        //设置总行数
//        dto.setTotal(bannerMapper.selectTotalCount());
        dto.setTotal(bannerMapper.selectCount(null));
        //设置当前页的数据行
//        dto.setRows(bannerMapper.selectByPage(curPage, pageSize));
        PageHelper.startPage(curPage, pageSize);
        List<Banner> list = bannerMapper.selectAll();
        dto.setRows(list);
        return dto;
    }

    @Override
    public void addBanner(Banner banner) {
        bannerMapper.insert(banner);
    }

    @Override
    public void deleteBanner(Integer id) {
        bannerMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Banner banner) {
        bannerMapper.updateByPrimaryKey(banner);
    }
}
