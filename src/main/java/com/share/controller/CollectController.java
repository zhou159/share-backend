package com.share.controller;

import com.share.exceptions.ShareException;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.collectRo.CollectRo;
import com.share.service.CollectService;
import com.share.vo.CollectVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Api("收藏模块")
@RestController
@RequestMapping("/collect")
public class CollectController {
    @Autowired
    CollectService collectService;

    @ApiOperation("添加收藏,userId,goodsId")
    @PostMapping("/addCollect")
    public RestObject<String> addCollect(@RequestBody CollectVo collectVo){
        if (collectVo.getUserId().equals("") || collectVo.getUserId() == null){
            throw new ShareException("请先登录!");
        }else{
            boolean b = collectService.isExist(collectVo);
            if (b){
                return RestResponse.makeErrRsp("该商品您已收藏过，请勿重复收藏!");
            }else {
                collectVo.setCreateTime(LocalDateTime.now());
                collectService.addCollect(collectVo);
                return RestResponse.makeOKRsp("添加成功!");
            }
        }
    }

    @ApiOperation("按收藏id取消收藏")
    @PostMapping("/deleteCollect/{id}")
    public RestObject<String> deleteCollect(@PathVariable int id){
        collectService.deleteCollect(id);
        return RestResponse.makeOKRsp("取消收藏成功!");
    }

    @ApiOperation("按收藏id查询")
    @GetMapping("/queryById/{id}")
    public RestObject<CollectRo> queryCollectById(@PathVariable int id){
        return RestResponse.makeOKRsp(collectService.queryCollectById(id));
    }

    @ApiOperation("按用户id查询收藏")
    @GetMapping("/queryByUserId/{userId}")
    public RestObject<List<CollectRo>> queryCollectByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(collectService.queryCollectByUserId(userId));
    }



}
