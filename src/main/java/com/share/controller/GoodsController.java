package com.share.controller;

import com.share.exceptions.ShareException;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.GoodsRo;
import com.share.service.GoodsService;
import com.share.vo.GoodsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Api("交易物品模块")
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @ApiOperation("查询全部交易物品")
    @GetMapping("/queryAllGoods")
    public RestObject<List<GoodsRo>> queryAllGoods(){
        return RestResponse.makeOKRsp(goodsService.queryAllGoods());
    }

    @ApiOperation("按用户id查询交易物品")
    @GetMapping("/queryByUserId/{userId}")
    public RestObject<List<GoodsRo>> queryGoodsByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(goodsService.queryGoodsByUserId(userId));
    }

    @ApiOperation("按id查询交易物品")
    @GetMapping("/queryById/{id}")
    public RestObject<GoodsRo> queryGoodsById(@PathVariable int id){
        return RestResponse.makeOKRsp(goodsService.queryGoodsById(id));
    }

    @ApiOperation("新增交易物品")
    @PostMapping("/addGoods")
    public RestObject<String> addGoods(@RequestBody GoodsVo goodsVo){
        goodsVo.setCreateTime(LocalDateTime.now());
        System.out.println(LocalDateTime.now());
        int i = goodsService.addGoods(goodsVo);
        if(i!=0) {
            throw new ShareException("新增失败，请重试!");
        }else {
            return RestResponse.makeOKRsp("新增成功!");
        }
    }

    @ApiOperation("修改交易物品信息(库存，描述。。)")
    @PostMapping("/updateGoods/{id}")
    public RestObject<String> updateGoods(@PathVariable int id,@RequestBody GoodsVo goodsVo){
        int i = goodsService.updateGoods(id,goodsVo);
        if(i!=0) {
            throw new ShareException("修改失败，请重试!");
        }else {
            return RestResponse.makeOKRsp("修改成功!");
        }
    }

    @ApiOperation("修改交易交易物品状态(删除)")
    @PostMapping("/updateGoodsStatus/{id}")
    public RestObject<String> updateGoodsStatus(@PathVariable int id,@RequestBody GoodsVo goodsVo){
        int i = goodsService.updateGoodsStatus(id,goodsVo);
        if(i!=0) {
            throw new ShareException("修改失败，请重试!");
        }else {
            return RestResponse.makeOKRsp("修改成功!");
        }
    }





}
