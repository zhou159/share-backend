package com.share.controller;

import com.share.annotation.UserLoginInfo;
import com.share.annotation.UserLoginToken;
import com.share.enums.Source;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.goodsRo.GoodsIdRo;
import com.share.ro.orderRo.OrderRo;
import com.share.ro.orderRo.UserOrderRo;
import com.share.service.GoodsService;
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
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @UserLoginInfo
    @ApiOperation("按用户id查询订单")
    @GetMapping("/queryOrderByUserId/{userId}")
    public RestObject<List<UserOrderRo>> queryOrderByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(orderService.queryOrderByUserId(userId));
    }

    @UserLoginToken
    @ApiOperation("按id查询订单")
    @GetMapping("/queryOrderById/{id}")
    public RestObject<OrderRo> queryOrderById(@PathVariable int id){
        return RestResponse.makeOKRsp(orderService.queryOrderById(id));
    }

    @UserLoginInfo
    @ApiOperation("新增订单;body参数：goods_id")
    @PostMapping("/addOrder/{userId}")
    public RestObject<String> addOrder(@RequestBody OrderVo orderVo,@PathVariable int userId){
        String orderNo = RandomUtil.createRandom(19, Source.num, Source.num.getSources().length());
        OrderRo order = orderService.queryOrderByOrederNo(orderNo);
        GoodsIdRo goodsIdRo = goodsService.queryGoodsById(orderVo.getGoodsId());
        while(order!=null){
            orderNo = RandomUtil.createRandom(19, Source.num, Source.num.getSources().length());
            order = orderService.queryOrderByOrederNo(orderNo);
        }
        orderVo.setUserIdG(goodsIdRo.getUserId());
        orderVo.setUserId(userId);
        orderVo.setOrderNo(orderNo);
        orderVo.setCreateTime(LocalDateTime.now());
        orderService.addOrder(orderVo);
        return RestResponse.makeOKRsp("新增成功");
    }

    @UserLoginToken
    @ApiOperation("修改订单状态")
    @PostMapping("/updateOrder/{id}/{userId}")
    public RestObject<String> updateOrder(@PathVariable int id,@PathVariable int userId,@RequestBody OrderVo orderVo){
        orderService.updateOrder(id,orderVo);
        return RestResponse.makeOKRsp("修改成功");
    }

    @UserLoginInfo
    @ApiOperation("逻辑删除订单")
    @PostMapping("/deleteOrder/{id}/{userId}")
    public RestObject<String> deleteOrder(@PathVariable int id,@PathVariable int userId,@RequestBody OrderVo orderVo){
        OrderRo orderRo = orderService.queryOrderById(id);
        if (userId != orderRo.getUserId()){
            return RestResponse.UserErrRsp("你无权删除!");
        }else {
            if (orderRo.getStatus() == "0"){
                return RestResponse.ErrRsp(401,"删除失败","你不能删除正在交易中的订单！");
            }else {
                orderService.deleteOrder(id,orderVo);
                return RestResponse.makeOKRsp("删除成功");
            }
        }
    }

}
