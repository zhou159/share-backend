package com.share.controller;

import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.rentRo.RentRo;
import com.share.ro.rentRo.RentUserIdRo;
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

    @ApiOperation("按id查询查询出租中的房屋或车位")
    @GetMapping("/queryRentById/{id}")
    public RestObject<RentRo> queryRentById(@PathVariable int id){
        return RestResponse.makeOKRsp(rentService.queryRentById(id));
    }

    @ApiOperation("查询全部出租中的房屋")
    @GetMapping("/queryAllRentDepart")
    public RestObject<List<RentRo>> queryAllRentDepart(){
        return RestResponse.makeOKRsp(rentService.queryAllRentDepart());
    }

    @ApiOperation("查询全部出租中的车位")
    @GetMapping("/queryAllRentPark")
    public RestObject<List<RentRo>> queryAllRentPark(){
        return RestResponse.makeOKRsp(rentService.queryAllRentPark());
    }

    @ApiOperation("按出租人id查询所有出租的车位")
    @GetMapping("/queryRentParkByUserId/{userIdRent}")
    public RestObject<List<RentUserIdRo>> queryRentParkByUserId(@PathVariable int userIdRent){
        return RestResponse.makeOKRsp(rentService.queryRentParkByUserId(userIdRent));
    }

    @ApiOperation("按出租人id查询所有出租的房屋")
    @GetMapping("/queryRentDepartByUserId/{userIdRent}")
    public RestObject<List<RentUserIdRo>> queryRentDepartByUserId(@PathVariable int userIdRent){
        return RestResponse.makeOKRsp(rentService.queryRentDepartByUserId(userIdRent));
    }

    @ApiOperation("添加出租")
    @PostMapping("/addRent/{userIdRent}")
    public RestObject<String> addRent(@PathVariable int userIdRent,@RequestBody RentVo rentVo){
        rentVo.setCreateTime(LocalDateTime.now());
        rentVo.setUserIdRent(userIdRent);
        int i = rentService.addRent(rentVo);
        if (i==0){
            return RestResponse.makeOKRsp("添加成功!");
        }else {
            return RestResponse.makeOKRsp("添加失败!");
        }
    }

    //修改出租(id),地址,价格
    @ApiOperation("修改出租信息,地址,价格,描述")
    @PostMapping("/updateRent/{id}")
    public RestObject<String> updateRent(@PathVariable int id,@RequestBody RentVo rentVo){
        rentVo.setUpdateTime(LocalDateTime.now());
        int i = rentService.updateRent(id, rentVo);
        if (i==0){
            return RestResponse.makeOKRsp("修改成功!");
        }else {
            return RestResponse.makeOKRsp("修改成功!");
        }
    }

    @ApiOperation("修改租赁人")
    @PostMapping("/updateRenter/{id}")
    public RestObject<String> updateRenter(@PathVariable int id,@RequestBody RentVo rentVo){
        int i = rentService.updateRenter(id, rentVo);
        if (i==0){
            return RestResponse.makeOKRsp("修改成功!");
        }else {
            return RestResponse.makeOKRsp("修改成功!");
        }
    }

    @ApiOperation("修改出租状态")
    @PostMapping("/updateStatus/{id}")
    public RestObject<String> updateStatus(@PathVariable int id,@RequestBody RentVo rentVo){
        int i = rentService.updateStatus(id, rentVo);
        if (i==0){
            return RestResponse.makeOKRsp("修改成功!");
        }else {
            return RestResponse.makeOKRsp("修改成功!");
        }
    }

    //删除租赁(id)
    @ApiOperation("逻辑删除出租")
    @PostMapping("/deletedRent/{id}")
    public RestObject<String> deleteRent(@PathVariable int id,@RequestBody RentVo rentVo){
        int i = rentService.deleteRent(id, rentVo);
        if (i==0){
            return RestResponse.makeOKRsp("删除成功!");
        }else {
            return RestResponse.makeOKRsp("删除成功!");
        }
    }
}
