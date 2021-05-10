package com.share.controller;

import com.share.annotation.UserLoginInfo;
import com.share.annotation.UserLoginToken;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.travelRo.TravelRo;
import com.share.service.TravelService;
import com.share.vo.TravelVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Api("出行模块")
@RestController
@RequestMapping("/travel")
public class TravelController {
    @Autowired
    TravelService travelService;

    @UserLoginToken
    @ApiOperation("查询全部出行")
    @GetMapping("/queryAllTravel")
    public RestObject<List<TravelRo>> queryAllTravel(){
        return RestResponse.makeOKRsp(travelService.queryAllTravel());
    }

    @UserLoginToken
    @ApiOperation("按用户id查询出行")
    @GetMapping("/queryTravelByUserId/{userId}")
    public RestObject<List<TravelRo>> queryTravelByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(travelService.queryTravelByUserId(userId));
    }

    @UserLoginToken
    @ApiOperation("按id查询出行")
    @GetMapping("/queryTravelById/{id}")
    public RestObject<TravelRo> queryTravelById(@PathVariable int id){
        return RestResponse.makeOKRsp(travelService.queryTravelById(id));
    }

    @UserLoginInfo
    @ApiOperation("添加出行")
    @PostMapping("/addTravel/{userId}")
    public RestObject<String> addTravel(@RequestBody TravelVo travelVo,@PathVariable int userId){
        travelVo.setCreateTime(LocalDateTime.now());
        travelVo.setUserId(userId);
        travelService.addTravel(travelVo);
        return RestResponse.makeOKRsp("添加成功!");
    }

    @UserLoginInfo
    @ApiOperation("修改出行")
    @PostMapping("/updateTravel/{id}/{userId}")
    public RestObject<String> updateTravel(@PathVariable int id,@PathVariable int userId,@RequestBody TravelVo travelVo){
        TravelRo travelRo = travelService.queryTravelById(id);
        if (travelRo.getUserId()!=userId){
            return RestResponse.UserErrRsp("你无权修改！");
        }else {
            travelVo.setUpdateTime(LocalDateTime.now());
            travelService.updateTravel(id, travelVo);
            return RestResponse.makeOKRsp("修改成功!");
        }
    }

    @UserLoginInfo
    @ApiOperation("修改出行状态")
    @PostMapping("/updateStatus/{id}/{userId}")
    public RestObject<String> updateStatus(@PathVariable int id,@PathVariable int userId,@RequestBody TravelVo travelVo){
        TravelRo travelRo = travelService.queryTravelById(id);
        if (travelRo.getUserId()!=userId){
            return RestResponse.UserErrRsp("你无权修改！");
        }else {
            travelService.updateStatus(id, travelVo);
            return RestResponse.makeOKRsp("修改成功!");
        }
    }

    @UserLoginInfo
    @ApiOperation("逻辑删除出行")
    @PostMapping("/deleteTravel/{id}/{userId}")
    public RestObject<String> deleteTravel(@PathVariable int id,@PathVariable int userId,@RequestBody TravelVo travelVo){
        TravelRo travelRo = travelService.queryTravelById(id);
        if (travelRo.getUserId()!=userId){
            return RestResponse.UserErrRsp("你无权修改！");
        }else {
            travelService.deleteTravel(id, travelVo);
            return RestResponse.makeOKRsp("删除成功!");
        }
    }
}
