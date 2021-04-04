package com.share.controller;

import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.HistoryRo;
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
            historyService.addHistory(historyVo);
        }
    }

    @ApiOperation("删除历史记录")
    @PostMapping("/deleteHistory/{id}")
    public RestObject<String> deleteHistory(@PathVariable int id){
        historyService.deleteHistory(id);
        return RestResponse.makeOKRsp("删除成功!");
    }

    @ApiOperation("通过用户id查询历史记录")
    @GetMapping("/queryHistoryByUserId/{userId}")
    public RestObject<List<HistoryRo>> queryHistoryByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(historyService.queryHistoryByUserId(userId));
    }

//    @ApiOperation("通过对象id查询历史记录")
//    @GetMapping("/queryHistoryByObjId")
//    public RestObject<HistoryRo> queryHistoryByObjId(@RequestBody HistoryVo historyVo){
//        return RestResponse.makeOKRsp(historyService.queryHistoryByObjId(historyVo));
//    }
//
//    @ApiOperation("修改历史记录时间")
//    @GetMapping("/updateHistory")
//    public RestObject<String> updateHistory(@RequestBody HistoryVo historyVo){
//        historyVo.setLastTime(LocalDateTime.now());
//        historyService.updateHistory(historyVo);
//        return RestResponse.makeOKRsp("修改成功");
//    }
}
