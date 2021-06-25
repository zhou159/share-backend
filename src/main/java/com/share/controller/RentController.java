package com.share.controller;

import com.share.annotation.UserLoginInfo;
import com.share.annotation.UserLoginToken;
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

    @UserLoginToken
    @ApiOperation("按id查询查询出租中的房屋或车位")
    @GetMapping("/queryRentById/{id}")
    public RestObject<RentRo> queryRentById(@PathVariable int id){
        return RestResponse.makeOKRsp(rentService.queryRentById(id));
    }

    @UserLoginToken
    @ApiOperation("查询全部出租中的房屋")
    @GetMapping("/queryAllRentDepart")
    public RestObject<List<RentRo>> queryAllRentDepart(){
        return RestResponse.makeOKRsp(rentService.queryAllRentDepart());
    }

    @UserLoginToken
    @ApiOperation("查询全部出租中的车位")
    @GetMapping("/queryAllRentPark")
    public RestObject<List<RentRo>> queryAllRentPark(){
        return RestResponse.makeOKRsp(rentService.queryAllRentPark());
    }

    @UserLoginToken
    @ApiOperation("按出租人id查询所有出租的车位")
    @GetMapping("/queryRentParkByUserId/{userId}")
    public RestObject<List<RentUserIdRo>> queryRentParkByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(rentService.queryRentParkByUserId(userId));
    }

    @UserLoginToken
    @ApiOperation("按租赁人id查询所有出租的车位")
    @GetMapping("/queryRentParkByUserIdRenter/{userId}")
    public RestObject<List<RentUserIdRo>> queryRentParkByUserIdRenter(@PathVariable int userId){
        return RestResponse.makeOKRsp(rentService.queryRentParkByUserIdRenter(userId));
    }

    @UserLoginToken
    @ApiOperation("按出租人id查询所有出租的房屋")
    @GetMapping("/queryRentDepartByUserId/{userId}")
    public RestObject<List<RentUserIdRo>> queryRentDepartByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(rentService.queryRentDepartByUserId(userId));
    }

    @UserLoginToken
    @ApiOperation("按租赁人id查询所有出租的房屋")
    @GetMapping("/queryRentDepartByUserIdRenter/{userId}")
    public RestObject<List<RentUserIdRo>> queryRentDepartByUserIdRenter(@PathVariable int userId){
        return RestResponse.makeOKRsp(rentService.queryRentDepartByUserIdRenter(userId));
    }

    @UserLoginInfo
    @ApiOperation("添加出租,url参数，userIdRent")
    @PostMapping("/addRent/{userId}")
    public RestObject<String> addRent(@PathVariable int userId,@RequestBody RentVo rentVo){
        rentVo.setCreateTime(LocalDateTime.now());
        rentVo.setUserIdRent(userId);
        int i = rentService.addRent(rentVo);
        if (i==0){
            return RestResponse.makeOKRsp("添加成功!");
        }else {
            return RestResponse.makeErrRsp("添加失败!");
        }
    }

    //修改出租(id),地址,价格
    @UserLoginInfo
    @ApiOperation("修改出租信息,地址,价格,描述")
    @PostMapping("/updateRent/{id}/{userId}")
    public RestObject<String> updateRent(@PathVariable int id,@PathVariable int userId,@RequestBody RentVo rentVo){
        RentRo rentRo = rentService.queryRentById(id);
        if (userId != rentRo.getUserIdRent()){
            return RestResponse.UserErrRsp("你无权修改!");
        }else {
            rentVo.setUpdateTime(LocalDateTime.now());
            int i = rentService.updateRent(id, rentVo);
            if (i==0){
                return RestResponse.makeOKRsp("修改成功!");
            }else {
                return RestResponse.makeErrRsp("修改失败!");
            }
        }
    }

    @UserLoginToken
    @ApiOperation("修改租赁人")
    @PostMapping("/updateRenter/{id}/{userId}")
    public RestObject<String> updateRenter(@PathVariable int id,@PathVariable int userId,@RequestBody RentVo rentVo){
        RentRo rentRo = rentService.queryRentById(id);
        if (rentVo.getUserIdRenter() == rentRo.getUserIdRent()){
            return RestResponse.makeErrRsp("您无法租用自己的房子! ");
        }else if (rentRo.getStatus().equals("被租用中")){
            if (rentRo.getUserIdRenter() == userId){
                rentVo.setUserIdRenter(null);
                rentService.updateStatus(id,"出租中");
                rentService.updateRenter(id, rentVo);
                return RestResponse.makeOKRsp("取消租房成功！");
            }else {
                return RestResponse.makeErrRsp("该房子被租用中！");
            }
        }else if (rentRo.getStatus().equals("暂不对外出租")){
            return RestResponse.makeErrRsp("该房子无法租用！");
        }else {
            rentService.updateStatus(id,"被租用中");
            int i = rentService.updateRenter(id, rentVo);
            if (i==0){
                return RestResponse.makeOKRsp("修改成功!");
            }else {
                return RestResponse.makeErrRsp("修改失败!");
            }
        }
    }

    @UserLoginInfo
    @ApiOperation("修改出租状态")
    @PostMapping("/updateStatus/{id}/{userId}")
    public RestObject<String> updateStatus(@PathVariable int id,@PathVariable int userId,@RequestBody RentVo rentVo){
        RentRo rentRo = rentService.queryRentById(id);
        if (userId != rentRo.getUserIdRent()){
            return RestResponse.UserErrRsp("你无权修改!");
        }else if (rentVo.getStatus().equals("出租中") && !rentRo.getStatus().equals("出租中")){
            rentVo.setUserIdRenter(null);
            rentService.updateRenter(id,rentVo);
            int i = rentService.updateStatus(id, rentVo.getStatus());
            if (i==0){
                return RestResponse.makeOKRsp("修改成功!");
            }else {
                return RestResponse.makeErrRsp("修改失败!");
            }
        }else if (rentVo.getStatus().equals("暂不对外出租") && !rentRo.getStatus().equals("出租中")){
            rentVo.setUserIdRenter(null);
            rentService.updateRenter(id,rentVo);
            int i = rentService.updateStatus(id, rentVo.getStatus());
            if (i==0){
                return RestResponse.makeOKRsp("修改成功!");
            }else {
                return RestResponse.makeErrRsp("修改失败!");
            }
        }else {
            int i = rentService.updateStatus(id, rentVo.getStatus());
            if (i==0){
                return RestResponse.makeOKRsp("修改成功!");
            }else {
                return RestResponse.makeErrRsp("修改失败!");
            }
        }
    }

    //删除租赁(id)
    @UserLoginInfo
    @ApiOperation("逻辑删除出租")
    @PostMapping("/deletedRent/{id}/{userId}")
    public RestObject<String> deleteRent(@PathVariable int id,@PathVariable int userId){
        RentRo rentRo = rentService.queryRentById(id);
        if (userId != rentRo.getUserIdRent()){
            return RestResponse.UserErrRsp("你无权删除!");
        }else {
            int i = rentService.deleteRent(id);
            if (i==0){
                return RestResponse.makeOKRsp("删除成功!");
            }else {
                return RestResponse.makeErrRsp("删除失败!");
            }
        }
    }
}
