package com.share.service;

import com.share.mapper.OrderMapper;
import com.share.ro.OrderRo;
import com.share.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    public List<OrderRo> queryOrderByUserId(int userId){
        return orderMapper.queryOrderByUserId(userId);
    }

    public OrderRo queryOrderById(int id){
        return orderMapper.queryOrderById(id);
    }

    public OrderRo queryOrderByOrederNo(Long orderNo){
        return orderMapper.queryOrderByOrderNo(orderNo);
    }

    public int addOrder(OrderVo orderVo){
        orderMapper.addOrder(orderVo);
        return 0;
    }

    public int updateOrder(int id,OrderVo orderVo){
        orderMapper.updateOrder(id, orderVo);
        return 0;
    }

    public int deleteOrder(int id,OrderVo orderVo){
        orderMapper.deleteOrder(id, orderVo);
        return 0;
    }

}
