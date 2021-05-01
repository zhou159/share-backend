package com.share.controller;

import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.helpRo.HelperRo;
import com.share.ro.helpRo.HelpRo;
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

    @ApiOperation("新增帮助,userId为当前用户id")
    @PostMapping("/addHelp/{userId}")
    public RestObject<String> addHelp(@PathVariable int userId,@RequestBody HelpVo helpVo){
        helpVo.setCreateTime(LocalDateTime.now());
        int i = helpService.addHelp(helpVo, userId);
        if (i==0){
            return RestResponse.makeOKRsp("新增成功");
        }else {
            return RestResponse.makeErrRsp("新增失败");
        }

    }

    @ApiOperation("修改帮助(id:修改的帮助的id；userId:当前用户id)")
    @PostMapping("/updateHelp/{id}/{userId}")
    public RestObject<String> updateHelp(@PathVariable int id,@PathVariable int userId,@RequestBody HelpVo helpVo){
        HelpRo helpRo = helpService.queryHelpById(id);
        Integer userIdHelp = helpRo.getUserIdHelp();
        if (userIdHelp == userId){
            helpVo.setUpdateTime(LocalDateTime.now());
            int i = helpService.updateHelp(id, helpVo);
            if (i==0){
                return RestResponse.makeOKRsp("修改成功");
            }else {
                return RestResponse.makeErrRsp("修改失败");
            }
        }else {
            return RestResponse.makeErrRsp("您无权修改!");
        }
    }

    @ApiOperation("修改帮助人,用户点击'帮助他'按钮就调用此接口;参数：userIdHelper,为当千登录用户id")
    @PostMapping("/updateHelper/{id}")
    public RestObject<String> updateHelper(@PathVariable int id,@RequestBody HelpVo helpVo){
        int i = helpService.updateHelper(id, helpVo);
        if (i==0){
            return RestResponse.makeOKRsp("修改成功");
        }else {
            return RestResponse.makeErrRsp("修改失败");
        }
    }

    @ApiOperation("删除帮助;id:帮助的id；userId:当前用户id")
    @PostMapping("/deleteHelp/{id}/{userId}")
    public RestObject<String> deleteHelp(@PathVariable int id,@PathVariable int userId){
        HelpRo helpRo = helpService.queryHelpById(id);
        if (helpRo.getUserIdHelp() == userId){
            int i = helpService.deleteHelp(id);
            if (i==0){
                return RestResponse.makeOKRsp("删除成功");
            }else {
                return RestResponse.makeErrRsp("删除失败");
            }
        }else {
            return RestResponse.makeErrRsp("您无权删除!");
        }
    }

    @ApiOperation("查询所有求助中的外卖")
    @GetMapping("/queryAllDeliveryHelp")
    public RestObject<List<HelpRo>> queryAllDeliveryHelp(){
        return RestResponse.makeOKRsp(helpService.queryAllDeliveryHelp());
    }

    @ApiOperation("查询所有求助中的快递")
    @GetMapping("/queryAllParcelHelp")
    public RestObject<List<HelpRo>> queryAllParcelHelp(){
        return RestResponse.makeOKRsp(helpService.queryAllParcelHelp());
    }

    @ApiOperation("按帮助者id查询帮助")
    @GetMapping("/queryHelperByUserId/{userIdHelper}")
    public RestObject<List<HelpRo>> queryHelperByUserId(@PathVariable int userIdHelper){
        return RestResponse.makeOKRsp(helpService.queryHelperByUserId(userIdHelper));
    }

    @ApiOperation("按求助者id查询帮助")
    @GetMapping("/queryHelpByUserId/{userIdHelp}")
    public RestObject<List<HelperRo>> queryHelpByUserId(@PathVariable int userIdHelp){
        return RestResponse.makeOKRsp(helpService.queryHelpByUserId(userIdHelp));
    }

    @ApiOperation("按id查询帮助")
    @GetMapping("/queryById/{id}")
    public RestObject<HelpRo> queryHelpById(@PathVariable int id){
        return RestResponse.makeOKRsp(helpService.queryHelpById(id));
    }

}
