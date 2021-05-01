package com.share.controller;

import com.share.exceptions.ShareException;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.goodsRo.GoodsAllRo;
import com.share.ro.goodsRo.GoodsByUserIdRo;
import com.share.ro.goodsRo.GoodsIdRo;
import com.share.service.GoodsService;
import com.share.util.MinioUtil;
import com.share.vo.GoodsVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Api("交易物品模块")
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    GoodsService goodsService;

    @Autowired
    private MinioUtil minioUtil;

    @ApiOperation("查询全部交易物品")
    @GetMapping("/queryAllGoods")
    public RestObject<List<GoodsAllRo>> queryAllGoods(){
        return RestResponse.makeOKRsp(goodsService.queryAllGoods());
    }

    @ApiOperation("按用户id查询交易物品")
    @GetMapping("/queryByUserId/{userId}")
    public RestObject<List<GoodsByUserIdRo>> queryGoodsByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(goodsService.queryGoodsByUserId(userId));
    }

    @ApiOperation("按id查询交易物品")
    @GetMapping("/queryById/{id}")
    public RestObject<GoodsIdRo> queryGoodsById(@PathVariable int id){
        return RestResponse.makeOKRsp(goodsService.queryGoodsById(id));
    }

    @ApiOperation("修改交易物品图片")
    @PostMapping("/updatePicture/{id}/{userId}")
    public RestObject<String> updatePicture(@PathVariable int id,@PathVariable int userId,MultipartFile file){
        GoodsIdRo goodsAllRo = goodsService.queryGoodsById(id);
        Integer gUserId = goodsAllRo.getUserId();
        if (gUserId == userId){
            String s = minioUtil.upload(file, "goods");
            if(s.equals("文件为空") || s.equals("上传失败")){
                throw new ShareException("图片上传失败,请更换图片或重新尝试!");
            }else {
                GoodsVo goodsVo = new GoodsVo();
                goodsVo.setPicture(s);
                int i = goodsService.updatePicture(id, goodsVo);
                if (i==0){
                    return RestResponse.makeOKRsp("修改成功!");
                }else {
                    return RestResponse.makeErrRsp("修改失败!");
                }
            }
        }else {
            return RestResponse.makeErrRsp("您无权修改此商品!");
        }
    }

    @ApiOperation("新增交易物品:需要填写的属性值：goodsName；details；price；stock;userId为当前用户id")
    @PostMapping("/addGoods/{userId}")
    public RestObject<String> addGoods(@RequestBody GoodsVo goodsVo,@PathVariable int userId){
        goodsVo.setCreateTime(LocalDateTime.now());
        int i = goodsService.addGoods(goodsVo,userId);
        if(i==0) {
            return RestResponse.makeOKRsp("新增成功!");
        }else {
            return RestResponse.makeErrRsp("新增失败!");
        }

    }

    @ApiOperation("修改交易物品信息(名字，描述，价格，库存)")
    @PostMapping("/updateGoods/{id}/{userId}")
    public RestObject<String> updateGoods(@PathVariable int id,@PathVariable int userId,@RequestBody GoodsVo goodsVo){
        GoodsIdRo goodsAllRo = goodsService.queryGoodsById(id);
        Integer gUserId = goodsAllRo.getUserId();
        if (gUserId == userId){
            int i = goodsService.updateGoods(id,goodsVo);
            if(i==0) {
                return RestResponse.makeOKRsp("修改成功!");
            }else {
                return RestResponse.makeErrRsp("修改失败!");
            }
        }else {
            return RestResponse.makeErrRsp("您无权修改此商品!");
        }
    }

    @ApiOperation("修改交易交易物品状态(1:已售；2:下架),id:货物id;userId:当前用户id")
    @PostMapping("/updateGoodsStatus/{id}/{userId}")
    public RestObject<String> updateGoodsStatus(@PathVariable int id,@PathVariable int userId,@RequestBody GoodsVo goodsVo){
        GoodsIdRo goodsAllRo = goodsService.queryGoodsById(id);
        System.out.println(goodsAllRo);
        Integer gUserId = goodsAllRo.getUserId();
        if (gUserId == userId){
            int i = goodsService.updateGoodsStatus(id,goodsVo);
            if(i==0) {
                return RestResponse.makeOKRsp("修改成功!");
            }else {
                return RestResponse.makeErrRsp("修改失败!");
            }
        }else {
            return RestResponse.makeErrRsp("您无权修改此商品!");
        }

    }

}
