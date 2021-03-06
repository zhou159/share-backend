package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.Order;
import com.share.ro.orderRo.OrderRo;
import com.share.ro.orderRo.UserOrderRo;
import com.share.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface   OrderMapper extends BaseMapper<Order> {
    //查询订单(用户id(买家))
    List<UserOrderRo> queryOrderByUserId(@Param("userId")int userId);

    //查询订单(用户id(卖家))
    List<UserOrderRo> queryOrderByUserIdG(@Param("userId")int userId);

    //查询订单(id)
    OrderRo queryOrderById(@Param("id")int id);

    //查询订单(订单编号)
    OrderRo queryOrderByOrderNo(@Param("orderNo")String orderNo);

    //创建订单
    int addOrder(@Param("orderVo")OrderVo orderVo);

    //修改订单(id)状态
    int updateOrder(@Param("id")int id,@Param("orderVo")OrderVo orderVo);

    //删除订单(id)逻辑删除
    int deleteOrder(@Param("id")int id,@Param("orderVo")OrderVo orderVo);

}
