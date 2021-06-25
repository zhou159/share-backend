package com.share.controller;

import com.share.annotation.UserLoginInfo;
import com.share.annotation.UserLoginToken;
import com.share.entity.Trolley;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.goodsRo.GoodsIdRo;
import com.share.ro.trolleyRo.TrolleyRo;
import com.share.service.GoodsService;
import com.share.service.TrolleyService;
import com.share.vo.TrolleyVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api("购物车模块")
@RequestMapping("/trolley")
@RestController
public class TrolleyController {
    @Autowired
    private TrolleyService trolleyService;

    @Autowired
    private GoodsService goodsService;

    @UserLoginInfo
    @ApiOperation("按用户id查询购物车")
    @GetMapping("/queryAllTrolley/{userId}")
    public RestObject<List<TrolleyRo>> queryTrolleyByUserId(@PathVariable int userId) {
        return RestResponse.makeOKRsp(trolleyService.queryTrolleyByUserId(userId));
    }

    @UserLoginInfo
    @ApiOperation("加入购物车")
    @PostMapping("/addTrolley/{userId}")
    public RestObject<String> addTrolley(@RequestBody TrolleyVo trolleyVo,@PathVariable int userId) {
        boolean b = trolleyService.queryByUGId(userId, trolleyVo);
        if (b){
            trolleyService.addTrolley(trolleyVo);
            return RestResponse.makeOKRsp("加入成功!");
        }else {
            return RestResponse.makeErrRsp("该物品已加入购物车，请勿重复添加！+");
        }
    }

    @UserLoginInfo
    @ApiOperation("将物品移除出购物车")
    @PostMapping("/removeTrolley/{id}/{userId}")
    public RestObject<String> removeTrolley(@PathVariable int id,@PathVariable int userId) {
        Trolley trolley = trolleyService.queryById(id);
        if (userId != trolley.getUserId()){
            return RestResponse.UserErrRsp("你无权移除!");
        }else {
            trolleyService.removeTrolley(id);
            return RestResponse.makeOKRsp("移除成功!");
        }
    }

//    @UserLoginInfo
//    @ApiOperation("修改购物车中商品数量。参数：number,goods_id")
//    @PostMapping("/updateTrolley/{id}/{userId}")
//    public RestObject<String> updateTrolley(@PathVariable int id,@PathVariable int userId,@RequestBody TrolleyVo trolleyVo) {
//        Trolley trolley = trolleyService.queryById(id);
//        GoodsIdRo goodsIdRo = goodsService.queryGoodsById(trolleyVo.getGoodsId());
//        if (userId != trolley.getUserId()){
//            return RestResponse.UserErrRsp("你无权修改!");
//        }else {
//            if (trolley.getNumber() > goodsIdRo.getStock()){
//                return RestResponse.ErrRsp(401,"货物数量不足","货物数量不足");
//            }else {
//                trolleyService.updateTrolley(id, trolleyVo);
//                return RestResponse.makeOKRsp("修改成功!");
//            }
//        }
//    }

}
