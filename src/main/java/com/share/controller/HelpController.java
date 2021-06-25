package com.share.controller;

import com.share.annotation.UserLoginInfo;
import com.share.annotation.UserLoginToken;
import com.share.exceptions.PermissionException;
import com.share.exceptions.ShareException;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.helpRo.HelperRo;
import com.share.ro.helpRo.HelpRo;
import com.share.ro.userRo.UserRo;
import com.share.service.HelpService;
import com.share.service.UserService;
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
    private HelpService helpService;
    @Autowired
    private UserService userService;

    @UserLoginInfo
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

    @UserLoginInfo
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

    @UserLoginToken
    @ApiOperation("修改帮助人,用户点击'帮助他'按钮就调用此接口;参数：userIdHelper,为当前登录用户id")
    @PostMapping("/updateHelper/{id}")
    public RestObject<String> updateHelper(@PathVariable int id,@RequestBody HelpVo helpVo){
        UserRo userRo = userService.queryUserById(helpVo.getUserIdHelper());
        Integer creditScore = userRo.getCreditScore();
        if (creditScore<100){
            return RestResponse.makeErrRsp("您的信誉分不满足要求！需至少100分才能帮助别人");
        }else {
            HelpRo helpRo = helpService.queryHelpById(id);
            if (helpRo.getUserIdHelp() == helpVo.getUserIdHelper()){
                return RestResponse.makeErrRsp("自己无法选择自己的帮助！");
            }else {
                helpService.updateStatus(id,"1");
                int i = helpService.updateHelper(id, helpVo);
                if (i==0){
                    return RestResponse.makeOKRsp("修改成功");
                }else {
                    return RestResponse.makeErrRsp("修改失败");
                }
            }
        }
    }

    @UserLoginInfo
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

    @UserLoginToken
    @ApiOperation("查询所有求助中的外卖")
    @GetMapping("/queryAllDeliveryHelp")
    public RestObject<List<HelpRo>> queryAllDeliveryHelp(){
        return RestResponse.makeOKRsp(helpService.queryAllDeliveryHelp());
    }

    @UserLoginToken
    @ApiOperation("查询所有求助中的快递")
    @GetMapping("/queryAllParcelHelp")
    public RestObject<List<HelpRo>> queryAllParcelHelp(){
        return RestResponse.makeOKRsp(helpService.queryAllParcelHelp());
    }

    @UserLoginToken
    @ApiOperation("按帮助者id查询帮助,url参数:帮助者")
    @GetMapping("/queryHelperByUserId/{userIdHelper}")
    public RestObject<List<HelpRo>> queryHelperByUserId(@PathVariable int userIdHelper){
        return RestResponse.makeOKRsp(helpService.queryHelperByUserId(userIdHelper));
    }

    @UserLoginToken
    @ApiOperation("按求助者id查询帮助,url参数:求助者")
    @GetMapping("/queryHelpByUserId/{userId}")
    public RestObject<List<HelperRo>> queryHelpByUserId(@PathVariable int userId){
        return RestResponse.makeOKRsp(helpService.queryHelpByUserId(userId));
    }

    @UserLoginToken
    @ApiOperation("按id查询帮助")
    @GetMapping("/queryById/{id}")
    public RestObject<HelpRo> queryHelpById(@PathVariable int id){
        return RestResponse.makeOKRsp(helpService.queryHelpById(id));
    }

    @UserLoginInfo
    @ApiOperation("修改帮助状态")
    @PostMapping("/updateStatus/{id}/{userId}")
    public RestObject<String> updateStatus(@PathVariable int id,@PathVariable int userId,@RequestBody HelpVo helpVo){
        HelpRo helpRo = helpService.queryHelpById(id);
        if(helpRo.getUserIdHelp() == userId || helpRo.getUserIdHelper() == userId) {
            if (helpVo.getStatus().equals("2") && userId == helpRo.getUserIdHelp() && helpRo.getStatus().equals("1")){
                userService.updateCreditScore(helpRo.getUserIdHelp(),5,"add");
                userService.updateCreditScore(helpRo.getUserIdHelper(),5,"add");
                helpService.updateStatus(id,"2");
                return RestResponse.makeOKRsp("帮助已完成！");
            }else if (helpVo.getStatus().equals("3")){
                if (helpRo.getStatus().equals("1")){
                    return RestResponse.makeErrRsp("已有人正在帮助您，无法撤销！");
                }else {
                    helpService.updateStatus(id,"3");
                    return RestResponse.makeOKRsp("帮助撤销成功！");
                }
            }else if (helpVo.getStatus().equals("4")){
                userService.updateCreditScore(helpRo.getUserIdHelper(),5,"sub");
                helpService.updateStatus(id,"0");
                helpVo.setUserIdHelper(null);
                helpService.updateHelper(id,helpVo);
                return RestResponse.makeOKRsp("帮助已取消！TA的帮助已重新发布");
            }else if (helpVo.getStatus().equals("5")){
                userService.updateCreditScore(helpRo.getUserIdHelp(),5,"sub");
                helpService.updateStatus(id,"5");
                return RestResponse.makeOKRsp("帮助已取消！取消自己的帮助系统无法帮你再次发布！");
            }else {
                return RestResponse.makeErrRsp("错误！");
            }
        }else {
            throw new PermissionException("你无权修改！");
        }

    }

}
