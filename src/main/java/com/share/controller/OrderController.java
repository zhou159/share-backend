package com.share.controller;

import com.share.enums.Source;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.orderRo.OrderRo;
import com.share.ro.orderRo.UserOrderRo;
import com.share.service.OrderService;
import com.share.util.RandomUtil;
import com.share.vo.OrderVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Api("订单模块!")
@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @ApiOperation("按用户id查询订单")
    @GetMapping("/queryOrderByUserId/{userId}")
    public RestObject<List<UserOrderRo>> queryOrderByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(orderService.queryOrderByUserId(userId));
    }

    @ApiOperation("按id查询订单")
    @GetMapping("/queryOrderById/{id}")
    public RestObject<OrderRo> queryOrderById(@PathVariable int id){
        return RestResponse.makeOKRsp(orderService.queryOrderById(id));
    }

    @ApiOperation("新增订单")
    @PostMapping("/addOrder")
    public RestObject<String> addOrder(@RequestBody OrderVo orderVo){
        String orderNo = RandomUtil.createRandom(19, Source.num, Source.num.getSources().length());
        OrderRo order = orderService.queryOrderByOrederNo(orderNo);
        if (order!=null){
            while(order!=null){
                orderNo = RandomUtil.createRandom(19, Source.num, Source.num.getSources().length());
                order = orderService.queryOrderByOrederNo(orderNo);
            }
        }
        orderVo.setOrderNo(orderNo);
        orderVo.setCreateTime(LocalDateTime.now());
        orderService.addOrder(orderVo);
        return RestResponse.makeOKRsp("新增成功");
    }

    @ApiOperation("修改订单状态")
    @PostMapping("/updateOrder/{id}")
    public RestObject<String> updateOrder(@PathVariable int id,@RequestBody OrderVo orderVo){
        orderService.updateOrder(id,orderVo);
        return RestResponse.makeOKRsp("修改成功");
    }

    @ApiOperation("逻辑删除订单")
    @PostMapping("/deleteOrder/{id}")
    public RestObject<String> deleteOrder(@PathVariable int id,@RequestBody OrderVo orderVo){
        orderService.deleteOrder(id,orderVo);
        return RestResponse.makeOKRsp("删除成功");
    }

}
