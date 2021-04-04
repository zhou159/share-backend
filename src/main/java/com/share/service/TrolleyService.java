package com.share.service;

import com.share.mapper.TrolleyMapper;
import com.share.ro.TrolleyRo;
import com.share.vo.TrolleyVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrolleyService {
    @Autowired
    TrolleyMapper trolleyMapper;

    //查询购物车(用户id)
    public List<TrolleyRo> queryTrolleyByUserId(int userId) {
        return trolleyMapper.queryTrolleyByUserId(userId);
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

    //修改购物车数据(id)数量
    public int updateTrolley(int id,TrolleyVo trolleyVo) {
        trolleyMapper.updateTrolley(id, trolleyVo);
        return 0;
    }


}
