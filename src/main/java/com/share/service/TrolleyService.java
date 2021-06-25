package com.share.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.entity.Goods;
import com.share.entity.Trolley;
import com.share.mapper.GoodsMapper;
import com.share.mapper.TrolleyMapper;
import com.share.ro.trolleyRo.TrolleyRo;
import com.share.vo.TrolleyVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TrolleyService {
    @Autowired
    TrolleyMapper trolleyMapper;

    @Autowired
    private GoodsMapper goodsMapper;

    //查询购物车(用户id)
    public List<TrolleyRo> queryTrolleyByUserId(int userId) {
        List<TrolleyRo> trolleyRos = trolleyMapper.queryTrolleyByUserId(userId);
        List<Goods> goods = goodsMapper.selectList(
                new QueryWrapper<Goods>().in(trolleyRos != null && !trolleyRos.isEmpty(), "id",
                        trolleyRos.stream()
                                .map(TrolleyRo::getGoodsId)
                                .distinct()
                                .collect(Collectors.toList())));

        Map<Integer, Goods> map = goods.stream().collect(Collectors.toMap(Goods::getId, Function.identity()));
        trolleyRos.stream().forEach(it -> {
            if (map.containsKey(it.getGoodsId())) {
                it.setGoodsName(map.get(it.getGoodsId()).getGoodsName());
                it.setPicture(map.get(it.getGoodsId()).getPicture());
                it.setGoodsPrice(map.get(it.getGoodsId()).getPrice());
                it.setStock(map.get(it.getGoodsId()).getStock());
            }
        });

        return trolleyRos;
    }

    //加入购物车
    public int addTrolley(TrolleyVo trolleyVo) {
        trolleyMapper.addTrolley(trolleyVo);
        return 0;
    }

    //移出购物车(id)
    public int removeTrolley(int id) {
        trolleyMapper.removeTrolley(id);
        return 0;
    }

//    //修改购物车数据(id)数量
//    public int updateTrolley(int id,TrolleyVo trolleyVo) {
//        trolleyMapper.updateTrolley(id, trolleyVo);
//        return 0;
//    }

    public Trolley queryById(int id){
        return trolleyMapper.selectById(id);
    }

    public boolean queryByUGId(int userId,TrolleyVo trolleyVo){
        QueryWrapper<Trolley> wrapper = new QueryWrapper<>();
        wrapper.eq("goods_id",trolleyVo.getGoodsId())
                .eq("user_id",userId);
        Trolley trolley = trolleyMapper.selectOne(wrapper);
        if (trolley!=null){
            return false;
        }else {
            return true;
        }
    }

    public void updateStatus(int id,String status){
        Trolley trolley = new Trolley();
        trolley.setId(id);
        trolley.setStatus(status);
        trolleyMapper.updateById(trolley);
    }


}
