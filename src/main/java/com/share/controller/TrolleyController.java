package com.share.controller;

import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.TrolleyRo;
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
    TrolleyService trolleyService;

    @ApiOperation("按用户id查询购物车")
    @GetMapping("/queryAllTrolley/{userId}")
    public RestObject<List<TrolleyRo>> queryTrolleyByUserId(@PathVariable int userId) {
        return RestResponse.makeOKRsp(trolleyService.queryTrolleyByUserId(userId));
    }

    @ApiOperation("加入购物车")
    @PostMapping("/addTrolley")
    public RestObject<String> addTrolley(@RequestBody TrolleyVo trolleyVo) {
        trolleyService.addTrolley(trolleyVo);
        return RestResponse.makeOKRsp("加入成功!");
    }

    @ApiOperation("将物品移除出购物车")
    @PostMapping("/removeTrolley/{id}")
    public RestObject<String> removeTrolley(@PathVariable int id) {
        trolleyService.removeTrolley(id);
        return RestResponse.makeOKRsp("移除成功!");
    }

    @ApiOperation("修改购物车中商品数量")
    @PostMapping("/updateTrolley/{id}")
    public RestObject<String> updateTrolley(@PathVariable int id,@RequestBody TrolleyVo trolleyVo) {
        trolleyService.updateTrolley(id, trolleyVo);
        return RestResponse.makeOKRsp("修改成功!");
    }

}
