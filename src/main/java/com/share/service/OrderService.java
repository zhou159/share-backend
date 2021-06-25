package com.share.service;

import com.share.mapper.OrderMapper;
import com.share.ro.orderRo.OrderRo;
import com.share.ro.orderRo.UserOrderRo;
import com.share.vo.OrderVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    public List<UserOrderRo> queryOrderByUserId(int userId){
        return orderMapper.queryOrderByUserId(userId);
    }

    public List<UserOrderRo> queryOrderByUserIdG(int userId){
        return orderMapper.queryOrderByUserIdG(userId);
    }

    public OrderRo queryOrderById(int id){
        return orderMapper.queryOrderById(id);
    }

    public OrderRo queryOrderByOrederNo(String orderNo){
        return orderMapper.queryOrderByOrderNo(orderNo);
    }

    public boolean addOrder(OrderVo orderVo){
        int i = orderMapper.addOrder(orderVo);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

    public int updateOrder(int id,OrderVo orderVo){
        orderMapper.updateOrder(id, orderVo);
        return 0;
    }

    public boolean deleteOrder(int id,OrderVo orderVo){
        int i = orderMapper.deleteOrder(id, orderVo);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }

}
