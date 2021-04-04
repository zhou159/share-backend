package com.share.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.mapper.GoodsMapper;
import com.share.ro.GoodsRo;
import com.share.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    
    public List<GoodsRo> queryAllGoods(){
        return goodsMapper.queryAllGoods();
    }

    public List<GoodsRo> queryGoodsByUserId(int userId){
        return goodsMapper.queryGoodsByUserId(userId);
    }

    public GoodsRo queryGoodsById(int id){
        return goodsMapper.queryGoodsById(id);
    }

    public int addGoods(GoodsVo goodsVo){
        goodsMapper.addGoods(goodsVo);
        return 0;
    }

    public int updateGoods(int id,GoodsVo goodsVo){
        goodsMapper.updateGoods(id,goodsVo);
        return 0;
    }

    public int updateGoodsStatus(int id,GoodsVo goodsVo){
        goodsMapper.updateGoodsStatus(id,goodsVo);
        return 0;
    }
}
