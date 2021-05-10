package com.share.controller;

import com.share.annotation.UserLoginInfo;
import com.share.annotation.UserLoginToken;
import com.share.entity.Authenticate;
import com.share.entity.User;
import com.share.exceptions.ShareException;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.authenticateRo.AuthenticateRo;
import com.share.ro.userRo.UserRo;
import com.share.service.AuthenticateService;
import com.share.service.UserService;
import com.share.util.MinioUtil;
import com.share.vo.AuthenticateVo;
import com.share.vo.UserVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@ApiModel("审核模块")
@RestController
@RequestMapping("/authenticate")
public class AuthenticateController {

    @Autowired
    private AuthenticateService authenticateService;

    @Autowired
    private UserService userService;

    @Autowired
    private MinioUtil minioUtil;

    @UserLoginInfo
    @ApiOperation("新增审核")
    @PostMapping("/addAuthenticate/{userId}/{adminNickname}")
    //authvo传入参数：user_id,createtime,picture,admin_nickname
    public RestObject<String> addAuthenticate(@PathVariable int userId,@PathVariable String adminNickname ,MultipartFile file){
        String s = minioUtil.upload(file, "authenticate");
        AuthenticateVo authenticateVo = new AuthenticateVo();
        User user = userService.queryByNickName(adminNickname);
        if(s.equals("文件为空") || s.equals("上传失败")){
            throw new ShareException("图片上传失败,请更换图片或重新尝试!");
        }else {
            authenticateVo.setUserId(userId);
            authenticateVo.setAdminUid(user.getId());
            authenticateVo.setCreateTime(LocalDateTime.now());
            authenticateVo.setAuthenticatePicture(s);
            authenticateService.addAuthenticate(authenticateVo);
            return RestResponse.makeOKRsp("提交成功!");
        }
    }

    @UserLoginInfo
    @ApiOperation("修改审核状态，并将用户表对应用户状态修改。url参数：id:审核id；userId：审核人id；body参数：success：失败为fasle,成功为true")
    @PostMapping("/updateStatus/{id}/{userId}")
    public RestObject<String> updateStatus(@PathVariable int id,@PathVariable int userId,@RequestBody AuthenticateVo authenticateVo){
        Authenticate authenticate= authenticateService.findAuthenticateById(id);
        Integer adminUid = authenticate.getAdminUid();
        if (userId!=adminUid){
            return RestResponse.UserErrRsp("你无权修改");
        }else {
            Integer userId_ = authenticate.getUserId();
            UserRo user = userService.queryUserById(userId_);
            UserVo vo = new UserVo();
            if(authenticateVo.isSuccess()){
                user.setStatus("2");
                user.setCreditScore(100);
                BeanUtils.copyProperties(user,vo);
                userService.updateUser(userId_,vo);
                authenticateService.updateStatus(id,"1");
                return RestResponse.makeOKRsp("操作成功!审核通过");
            }else {
                authenticateService.updateStatus(id,"2");
                return RestResponse.makeOKRsp("操作成功!审核失败");
            }
        }
    }

    @ApiOperation("按管理员nickname查询审核")
    @GetMapping("/findAuthenticateByAdminUserId/{userId}")
    public RestObject<List<AuthenticateRo>> findAuthenticateByAdminNickName(@PathVariable int userId){
        return RestResponse.makeOKRsp(authenticateService.findAuthenticateByAdminUserId(userId));
    }
}
