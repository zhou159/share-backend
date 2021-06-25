package com.share.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.entity.Collect;
import com.share.mapper.CollectMapper;
import com.share.ro.collectRo.CollectRo;
import com.share.vo.CollectVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectService {
    @Autowired
    CollectMapper collectMapper;

    public void addCollect(CollectVo collectVo){
        collectMapper.addCollect(collectVo);
    }

    public void deleteCollect(int id){
        collectMapper.deleteCollect(id);
    }

    public List<CollectRo> queryCollectByUserId(int userId){
        return collectMapper.queryCollectByUserId(userId);
    }

    public CollectRo queryCollectById(int id){
        return collectMapper.queryCollectById(id);
    }

    public boolean isExist(int userId,CollectVo collectVo){
        Collect collect = collectMapper.isExist(userId,collectVo);
        if (collect != null){
            return true;
        }else {
            return false;
        }
    }



}
