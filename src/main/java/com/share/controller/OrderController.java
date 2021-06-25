package com.share.controller;

import com.share.annotation.UserLoginInfo;
import com.share.annotation.UserLoginToken;
import com.share.enums.Source;
import com.share.exceptions.PermissionException;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.goodsRo.GoodsIdRo;
import com.share.ro.orderRo.OrderRo;
import com.share.ro.orderRo.UserOrderRo;
import com.share.service.GoodsService;
import com.share.service.OrderService;
import com.share.service.TrolleyService;
import com.share.service.UserService;
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

    @Autowired
    private TrolleyService trolleyService;

    @Autowired
    private UserService userService;

    @UserLoginInfo
    @ApiOperation("按用户id查询订单(买家)")
    @GetMapping("/queryOrderByUserId/{userId}")
    public RestObject<List<UserOrderRo>> queryOrderByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(orderService.queryOrderByUserId(userId));
    }

    @UserLoginInfo
    @ApiOperation("按用户id查询订单(卖家)")
    @GetMapping("/queryOrderByUserIdG/{userId}")
    public RestObject<List<UserOrderRo>> queryOrderByUserIdG(@PathVariable int userId){
        return RestResponse.makeOKRsp(orderService.queryOrderByUserIdG(userId));
    }

    @UserLoginToken
    @ApiOperation("按id查询订单")
    @GetMapping("/queryOrderById/{id}")
    public RestObject<OrderRo> queryOrderById(@PathVariable int id){
        return RestResponse.makeOKRsp(orderService.queryOrderById(id));
    }

    @UserLoginInfo
    @ApiOperation("新增订单;body参数：goods_id")
    @PostMapping("/addOrder/{id}/{userId}")
    public RestObject<String> addOrder(@RequestBody OrderVo orderVo,@PathVariable int id,@PathVariable int userId){
        GoodsIdRo goodsIdRo = goodsService.queryGoodsById(orderVo.getGoodsId());
        String status = goodsIdRo.getStatus();
        if (status.equals("出售中")){
            goodsService.updateGoodsStatus(orderVo.getGoodsId(),"交易中");
            String orderNo = RandomUtil.createRandom(19, Source.num, Source.num.getSources().length());
            OrderRo order = orderService.queryOrderByOrederNo(orderNo);
            System.out.println(orderVo.getGoodsId());
            while(order!=null){
                orderNo = RandomUtil.createRandom(19, Source.num, Source.num.getSources().length());
                order = orderService.queryOrderByOrederNo(orderNo);
            }
            orderVo.setUserIdG(goodsIdRo.getUserId());
            orderVo.setUserId(userId);
            orderVo.setOrderNo(orderNo);
            orderVo.setCreateTime(LocalDateTime.now());
            boolean i = orderService.addOrder(orderVo);
            if (i){
                trolleyService.updateStatus(id,"已生成订单");
                return RestResponse.makeOKRsp("新增成功");
            }else {
                return RestResponse.makeErrRsp("订单生成失败！");
            }
        }else if (status.equals("已下架")){
            return RestResponse.makeErrRsp("非常抱歉，该商品已下架！");
        }else if (status.equals("交易中")){
            return RestResponse.makeErrRsp("非常抱歉，该商品正在交易！");
        }else {
            return RestResponse.makeErrRsp("非常抱歉，该商品已出售！");
        }
    }

    @UserLoginToken
    @ApiOperation("修改订单状态")
    @PostMapping("/updateOrder/{id}/{userId}")
    public RestObject<String> updateOrder(@PathVariable int id,@PathVariable int userId,@RequestBody OrderVo orderVo){
        System.out.println("用户id:"+userId);
        System.out.println("订单id:"+id);
        OrderRo orderRo = orderService.queryOrderById(id);
        if (orderRo.getUserId()==userId || orderRo.getUserIdG()==userId){
            String status = orderRo.getStatus();
            System.out.println("查询出来的订单状态:"+status);
            System.out.println(orderVo.getStatus());
            if (!status.equals("0")){
                throw new PermissionException("订单已经完成，无法继续进行修改");
            }else {
                System.out.println("未完成");
//                买方收到货物，交易完成
                if (orderVo.getStatus().equals("1")){
                    userService.updateCreditScore(orderRo.getUserId(),10,"add");
                    userService.updateCreditScore(orderRo.getUserIdG(),10,"add");
                    orderService.updateOrder(id,orderVo);
                    goodsService.updateGoodsStatus(orderRo.getGoodsId(),"已出售");
                    return RestResponse.makeOKRsp("修改成功");
//                买家取消订单
                }else if (orderVo.getStatus().equals("2")){
                    System.out.println("买家取消");
                    userService.updateCreditScore(orderRo.getUserId(),10,"sub");
                    orderService.updateOrder(id,orderVo);
                    goodsService.updateGoodsStatus(orderRo.getGoodsId(),"交易中");
                    return RestResponse.makeOKRsp("修改成功");
                }else{
//                卖家取消订单
                    System.out.println("卖家取消");
                    userService.updateCreditScore(orderRo.getUserIdG(),10,"sub");
                    orderService.updateOrder(id,orderVo);
                    goodsService.updateGoodsStatus(orderRo.getGoodsId(),"交易中");
                    return RestResponse.makeOKRsp("修改成功");
                }
            }
        }else {
            return RestResponse.ErrRsp(402,"fail","您不是该物品的购买者或拥有者！");
        }
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
                boolean b = orderService.deleteOrder(id, orderVo);
                if (b){
                    return RestResponse.makeOKRsp("删除成功");
                }else {
                    return RestResponse.makeErrRsp("删除失败");
                }
            }
        }
    }

}
