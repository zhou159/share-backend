package com.share.controller;

import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.HelpRo;
import com.share.service.HelpService;
import com.share.vo.HelpVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Api("帮助模块")
@RestController
@RequestMapping("/help")
public class HelpController {
    @Autowired
    HelpService helpService;

    @ApiOperation("新增帮助")
    @PostMapping("/addHelp")
    public RestObject<String> addHelp(@RequestBody HelpVo helpVo){
        helpVo.setCreateTime(LocalDateTime.now());
        helpService.addHelp(helpVo);
        return RestResponse.makeOKRsp("新增成功");
    }

    @ApiOperation("修改帮助")
    @PostMapping("/updateHelp/{id}")
    public RestObject<String> updateHelp(@PathVariable int id,@RequestBody HelpVo helpVo){
        helpService.updateHelp(id,helpVo);
        return RestResponse.makeOKRsp("修改成功");
    }

    @ApiOperation("修改帮助人")
    @PostMapping("/updateHelper/{id}")
    public RestObject<String> updateHelper(@PathVariable int id,@RequestBody HelpVo helpVo){
        helpService.updateHelper(id,helpVo);
        return RestResponse.makeOKRsp("修改成功");
    }

    @ApiOperation("删除帮助")
    @PostMapping("/deleteHelp/{id}")
    public RestObject<String> deleteHelp(@PathVariable int id){
        helpService.deleteHelp(id);
        return RestResponse.makeOKRsp("删除功");
    }

    @ApiOperation("查询所有帮助")
    @PostMapping("/updateAllHelp")
    public RestObject<List<HelpRo>> deleteHelp(){
        return RestResponse.makeOKRsp(helpService.queryAllHelp());
    }

    @ApiOperation("按帮助者id查询帮助")
    @GetMapping("/queryHelperByUserId/{userIdHelper}")
    public RestObject<List<HelpRo>> queryHelperByUserId(@PathVariable int userIdHelper){
        return RestResponse.makeOKRsp(helpService.queryHelperByUserId(userIdHelper));
    }

    @ApiOperation("按求助者id查询帮助")
    @GetMapping("/queryHelpByUserId/{userIdHelp}")
    public RestObject<List<HelpRo>> queryHelpByUserId(@PathVariable int userIdHelp){
        return RestResponse.makeOKRsp(helpService.queryHelpByUserId(userIdHelp));
    }

    @ApiOperation("按id查询帮助")
    @GetMapping("/queryById/{id}")
    public RestObject<HelpRo> queryHelpById(@PathVariable int id){
        return RestResponse.makeOKRsp(helpService.queryHelpById(id));
    }

}
