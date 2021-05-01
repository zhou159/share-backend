package com.share.service;

import com.share.mapper.GoodsMapper;
import com.share.ro.goodsRo.GoodsByUserIdRo;
import com.share.ro.goodsRo.GoodsAllRo;
import com.share.ro.goodsRo.GoodsIdRo;
import com.share.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    
    public List<GoodsAllRo> queryAllGoods(){
        return goodsMapper.queryAllGoods();
    }

    public List<GoodsByUserIdRo> queryGoodsByUserId(int userId){
        return goodsMapper.queryGoodsByUserId(userId);
    }

    public GoodsIdRo queryGoodsById(int id){
        return goodsMapper.queryGoodsById(id);
    }

    public int addGoods(GoodsVo goodsVo,int userId){
        int i = goodsMapper.addGoods(goodsVo,userId);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }

    //修改交易物品图片
    public int updatePicture(int id,GoodsVo goodsVo){
        int i = goodsMapper.updatePicture(id, goodsVo);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }

    //修改货物信息
    public int updateGoods(int id,GoodsVo goodsVo){
        int i = goodsMapper.updateGoods(id, goodsVo);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }

    public int updateGoodsStatus(int id,GoodsVo goodsVo){
        int i = goodsMapper.updateGoodsStatus(id, goodsVo);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }
}
