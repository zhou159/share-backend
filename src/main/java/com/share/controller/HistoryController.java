package com.share.controller;

import com.share.exceptions.ShareException;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.historyRo.GoodsHistoryRo;
import com.share.ro.historyRo.HistoryRo;
import com.share.ro.historyRo.RentHsitoryRo;
import com.share.ro.historyRo.TravelHistoryRo;
import com.share.service.HistoryService;
import com.share.vo.HistoryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Api("历史记录模块")
@RestController
@RequestMapping("/history")
public class HistoryController {
    @Autowired
    HistoryService historyService;

    @ApiOperation("新增历史记录")
    @PostMapping("/addHistory")
    public void addHistory(@RequestBody HistoryVo historyVo){
        HistoryRo history = historyService.queryHistoryByObjId(historyVo);
        historyVo.setLastTime(LocalDateTime.now());
        if (history!=null){
            historyService.updateHistory(historyVo);
        }else {
            int i = historyService.addHistory(historyVo);
            if (i==0){}else {throw new ShareException("添加历史记录功能异常");
            }
        }
    }

    @ApiOperation("删除历史记录")
    @PostMapping("/deleteHistory/{id}")
    public RestObject<String> deleteHistory(@PathVariable int id){
        int i = historyService.deleteHistory(id);
        if (i==0){
            return RestResponse.makeOKRsp("删除成功!");
        }else {
            return RestResponse.makeErrRsp("删除失败!");
        }

    }

    @ApiOperation("通过用户id查询交易物品历史浏览记录")
    @GetMapping("/queryGoodsHistoryByUserId/{userId}")
    public RestObject<List<GoodsHistoryRo>> queryGoodsHistoryByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(historyService.queryGoodsHistoryByUserId(userId));
    }

    @ApiOperation("通过用户id查询出租历史浏览记录")
    @GetMapping("/queryRentHistoryByUserId/{userId}")
    public RestObject<List<RentHsitoryRo>> queryRentHistoryByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(historyService.queryRentHistoryByUserId(userId));
    }

    @ApiOperation("通过用户id查询出行历史浏览记录")
    @GetMapping("/queryTravelHistoryByUserId/{userId}")
    public RestObject<List<TravelHistoryRo>> queryTravelHistoryByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(historyService.queryTravelHistoryByUserId(userId));
    }

}
