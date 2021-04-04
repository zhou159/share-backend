package com.share.controller;

import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.TravelRo;
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

    @ApiOperation("查询全部出行")
    @GetMapping("/queryAllTravel")
    public RestObject<List<TravelRo>> queryAllTravel(){
        return RestResponse.makeOKRsp(travelService.queryAllTravel());
    }

    @ApiOperation("按用户id查询出行")
    @GetMapping("/queryTravelByUserId/{userId}")
    public RestObject<List<TravelRo>> queryTravelByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(travelService.queryTravelByUserId(userId));
    }

    @ApiOperation("添加出行")
    @PostMapping("/addTravel")
    public RestObject<String> addTravel(@RequestBody TravelVo travelVo){
        travelVo.setCreateTime(LocalDateTime.now());
        travelService.addTravel(travelVo);
        return RestResponse.makeOKRsp("添加成功!");
    }

    @ApiOperation("修改出行")
    @PostMapping("/updateTravel/{id}")
    public RestObject<String> updateTravel(@PathVariable int id,@RequestBody TravelVo travelVo){
        travelVo.setUpdateTime(LocalDateTime.now());
        travelService.updateTravel(id, travelVo);
        return RestResponse.makeOKRsp("修改成功!");
    }

    @ApiOperation("修改出行状态")
    @PostMapping("/updateStatus/{id}")
    public RestObject<String> updateStatus(@PathVariable int id,@RequestBody TravelVo travelVo){
        travelService.updateStatus(id, travelVo);
        return RestResponse.makeOKRsp("修改成功!");
    }

    @ApiOperation("逻辑删除出行")
    @PostMapping("/deleteTravel/{id}")
    public RestObject<String> deleteTravel(@PathVariable int id,@RequestBody TravelVo travelVo){
        travelService.deleteTravel(id, travelVo);
        return RestResponse.makeOKRsp("删除成功!");
    }
}
