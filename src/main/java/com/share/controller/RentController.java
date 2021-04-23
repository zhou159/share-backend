package com.share.controller;

import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.RentRo;
import com.share.service.RentService;
import com.share.vo.RentVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Api("出租模块")
@RequestMapping("/rent")
@RestController
public class RentController {
    @Autowired
    RentService rentService;

    @ApiOperation("查询全部出租")
    @GetMapping("/queryAllRent")
    public RestObject<List<RentRo>> queryAllRent(){
        return RestResponse.makeOKRsp(rentService.queryAllRent());
    }

    @ApiOperation("按出租人id查询出租")
    @GetMapping("/queryRentByUserId/{userIdRent}")
    public RestObject<List<RentRo>> queryRentByUserId(@PathVariable int userIdRent){
        return RestResponse.makeOKRsp(rentService.queryRentByUserId(userIdRent));
    }

    @ApiOperation("添加出租")
    @PostMapping("/addRent")
    public RestObject<String> addRent(@RequestBody RentVo rentVo){
        rentVo.setCreateTime(LocalDateTime.now());
        rentService.addRent(rentVo);
        return RestResponse.makeOKRsp("添加成功!");
    }

    //修改出租(id)类型,地址,价格
    @ApiOperation("修改出租信息")
    @PostMapping("/updateRent/{id}")
    public RestObject<String> updateRent(@PathVariable int id,@RequestBody RentVo rentVo){
        rentVo.setUpdateTime(LocalDateTime.now());
        rentService.updateRent(id,rentVo);
        return RestResponse.makeOKRsp("修改成功!");
    }

    @ApiOperation("修改租赁人")
    @PostMapping("/updateRenter/{id}")
    public RestObject<String> updateRenter(@PathVariable int id,@RequestBody RentVo rentVo){
        rentService.updateRenter(id, rentVo);
        return RestResponse.makeOKRsp("修改成功!");
    }

    @ApiOperation("修改出租状态")
    @PostMapping("/updateStatus/{id}")
    public RestObject<String> updateStatus(@PathVariable int id,@RequestBody RentVo rentVo){
        rentService.updateStatus(id, rentVo);
        return RestResponse.makeOKRsp("修改成功!");
    }

    //删除租赁(id)
    @ApiOperation("逻辑删除出租")
    @PostMapping("/deletedRent/{id}")
    public RestObject<String> deleteRent(@PathVariable int id,@RequestBody RentVo rentVo){
        rentService.deleteRent(id, rentVo);
        return RestResponse.makeOKRsp("删除成功!");
    }
}
