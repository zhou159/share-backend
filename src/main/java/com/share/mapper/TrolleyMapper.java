package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.Trolley;
import com.share.ro.trolleyRo.TrolleyRo;
import com.share.vo.TrolleyVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TrolleyMapper extends BaseMapper<Trolley> {
    //查询购物车(用户id)
    List<TrolleyRo> queryTrolleyByUserId(@Param("userId")int userId);

    //加入购物车
    int addTrolley(@Param("trolleyVo")TrolleyVo trolleyVo);

    //移出购物车(id)
    int removeTrolley(@Param("id")int id);

    //修改购物车数据(id)数量
    int updateTrolley(@Param("id")int id,@Param("trolleyVo") TrolleyVo trolleyVo);

}
