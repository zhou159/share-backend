package com.share.controller;

import com.share.exceptions.ShareException;
import com.share.entity.User;
import com.share.result.RestObject;
import com.share.result.RestResponse;
import com.share.ro.IDCardRo;
import com.share.ro.UserRo;
import com.share.service.UserService;
import com.share.util.IDCardUtil;
import com.share.util.MinioUtil;
import com.share.util.RandomUtil;
import com.share.enums.Source;
import com.share.util.RedisUtil;
import com.share.vo.UserVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static com.share.util.RandomUtil.createImage;

@Api("用户模块")
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private MinioUtil minioUtil;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private IDCardUtil idCardUtil;


    @ApiOperation("按userRo格式返回查询出的所有user对象")
    @GetMapping("/alluser")
    public RestObject<List<UserRo>> queryUserAll(){
        return RestResponse.makeOKRsp(userService.queryUserAll());
    }

    @ApiOperation("按id查询用户,用户登陆后的部分信息。")
    @GetMapping("/users/{id}")
    public RestObject<UserRo> queryUser(@PathVariable int id){
        return RestResponse.makeOKRsp(userService.queryUserById(id));
    }

    @ApiOperation("通过账户方式登录")
    @PostMapping("/loginusername")
    public RestObject<String> loginByUsername(@RequestBody UserVo userVo,HttpSession session){
        Object code = session.getAttribute("VerifyCode");
        //Object code = redisUtil.get("verifyCode");
        String userCode = userVo.getCheckCode().toUpperCase();
        if(userVo.getUsername().equals("") || userVo.getPassword().equals("")){
            throw new ShareException("账号密码不能为空!");
        }else{
            if(userCode.equals(code)) {
                boolean login = userService.queryByUsername(userVo);
                if (login) {
                    session.setAttribute("userInfo",userService.queryUserByTA(userVo));
                    return RestResponse.makeOKRsp("登录成功!");
                }else {
                    throw new ShareException("请输入正确的账号以及密码!");
                }
            }else {
                throw new ShareException("验证码不正确，请重新输入!");
            }
        }
    }

    @ApiOperation("通过电话方式登录")
    @PostMapping("/logintel")
    public RestObject<String> loginByTel(@RequestBody UserVo userVo,HttpSession session){
        Object code = session.getAttribute("VerifyCode");
        String userCode = userVo.getCheckCode().toUpperCase();
        if(userCode.equals("") || userVo.getTel().equals("") || userVo.getCheckCode().equals("")){
            throw new ShareException("电话号、密码或验证码不能为空!");
        }else{
            if(userVo.getCheckCode().equals(code)) {
                boolean login = userService.queryByTel(userVo);
                if (login) {
                    session.setAttribute("userInfo",userService.queryUserByTA(userVo));
                    return RestResponse.makeOKRsp("登录成功!");
                } else {
                    throw new ShareException("请输入正确的电话号码以及密码!");
                }
            }else {
                throw new ShareException("验证码不正确，请重新输入!");
            }
        }
    }

    @ApiOperation("通过账户方式注册")
    @PostMapping("/registerUsername")
    public RestObject<String> registerUsername(@RequestBody UserVo userVo,HttpSession session){
        Object code = session.getAttribute("VerifyCode");
        System.out.println(session.getId());
        String userCode = userVo.getCheckCode().toUpperCase();
        if (userVo.getUsername().equals("") || userVo.getPassword().equals("") || userCode.equals("")){
            throw new ShareException("账户、密码或验证码不能为空!");
        }else {
            if(userCode.equals(code)){
                User user = userService.queryUserByTA(userVo);
                if (user != null){
                    throw new ShareException("用户名已被注册!");
                }else {
                    String nickName = RandomUtil.createRandom(16,Source.symbolNumLetter,Source.symbolNumLetter.getSources().length());
                    User user1 = userService.queryByNickName(nickName);
                    if (user1!=null){
                        while (user1!=null){
                            nickName = RandomUtil.createRandom(16,Source.symbolNumLetter,Source.symbolNumLetter.getSources().length());
                            user1 = userService.queryByNickName(nickName);
                        }
                    }
                    userVo.setNickname(nickName);
                    userService.registerUsername(userVo);
                    return RestResponse.makeOKRsp("注册成功!");
                }
            }else {
                throw new ShareException("验证码不正确，请重新输入!");
            }
        }
    }

    @ApiOperation("通过电话方式注册")
    @PostMapping("/registertel")
    public RestObject<String> registerTel(@RequestBody UserVo userVo,HttpSession session){
        Object code = session.getAttribute("VerifyCode");
        String userCode = userVo.getCheckCode().toUpperCase();
        if (userVo.getTel().equals("") || userVo.getPassword().equals("") || userCode.equals("")){
            throw new ShareException("电话号码、密码或验证码不能为空!");
        }else {
            if (userVo.getCheckCode().equals(code)){
                User user = userService.queryUserByTA(userVo);
                if (user != null){
                    throw new ShareException("该电话号码已注册!");
                }else {
                    String nickName = RandomUtil.createRandom(16,Source.symbolNumLetter,Source.symbolNumLetter.getSources().length());
                    User user1 = userService.queryByNickName(nickName);
                    if (user1!=null){
                        while (user1!=null){
                            nickName = RandomUtil.createRandom(16,Source.symbolNumLetter,Source.symbolNumLetter.getSources().length());
                            user1 = userService.queryByNickName(nickName);
                        }
                    }
                    userVo.setNickname(nickName);
                    userService.registerTel(userVo);
                    return RestResponse.makeOKRsp("注册成功!");
                }
            }else {
                throw new ShareException("验证码不正确，请重新输入!");
            }
        }
    }

    @ApiOperation("根据id修改用户部分信息")
    @PostMapping("/updateUser/{id}")
    public RestObject<String> updateUser(@PathVariable int id,@RequestBody UserVo userVo){
        userService.updateUser(id,userVo);
        return RestResponse.makeOKRsp("修改成功");
    }

    @ApiOperation("根据id修改手机号")
    @PostMapping("/updateTel/{id}")
    public RestObject<String> updateTel(@PathVariable int id,@RequestBody UserVo userVo){
        UserRo ro = userService.queryUserById(id);
        User user = userService.queryUserByTA(userVo);
        if(user!=null && user.getId()!=id){
            throw new ShareException("该手机号已被绑定!");
        }else {
            if(ro.getTel().equals(userVo.getTel())){
                throw new ShareException("重复绑定!");
            }else {
                userService.updateTP(id, userVo);
                return RestResponse.makeOKRsp("绑定成功");
            }
        }
    }

    @ApiOperation("根据id修改用户头像图片")
    @PostMapping("/updatePicture/{id}")
    public RestObject<String> updatePicture(@PathVariable int id,MultipartFile file){
        String s = minioUtil.upload(file,"user-");
        if(s.equals("文件为空") || s.equals("上传失败")){
            throw new ShareException("图片上传失败,请更换图片或重新尝试!");
        }else {
            userService.updatePicture(id, s);
            return RestResponse.makeOKRsp("修改成功!");
        }
    }

    @ApiOperation("根据id修改密码")
    @PostMapping("/updatePwd/{id}")
    public RestObject<String> updatePwd(@PathVariable int id, @RequestBody UserVo userVo,HttpSession session){
        Object code = session.getAttribute("VerifyCode");
        System.out.println(code);
        if(userVo.getCheckCode().equals("")){
            throw new ShareException("请输入验证码");
        }else {
            if (userVo.getCheckCode().equals(code)){
                userService.updateTP(id, userVo);
                return RestResponse.makeOKRsp("修改成功");
            }else {
                throw new ShareException("验证码不正确!请重新输入");
            }
        }

    }

    @ApiOperation(value = "验证码")
    @GetMapping("/verifyCode")
    public void verifyCode(HttpSession session, HttpServletResponse response) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            String code = RandomUtil.createRandom(4,Source.numLetter,Source.numLetter.getSources().length());
            //redisUtil.setex("verifyCode",code,1);
            createImage(code,baos);
            //将VerifyCode绑定session
            session.setAttribute("VerifyCode",code);
            System.out.println(session.getId());
            //设置响应头
            response.setHeader("Pragma", "no-cache");
            //设置响应头
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Access-Control-Allow-Origin","true");
            //在代理服务器端防止缓冲
            response.setDateHeader("Expires", 0);
            //设置响应内容类型
            response.setContentType("image/jpeg");

            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().flush();
            baos.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @ApiOperation("根据传入的身份证图片，识别出文字信息并将其返回")
    @PostMapping("/idCard")
    public RestObject<IDCardRo> idCard(MultipartFile file){
        return RestResponse.makeOKRsp(idCardUtil.idcard(file));
    }

    @ApiOperation("身份证号码校验")
    @PostMapping("/authenticate/{id}")
    public RestObject<String> authenticate(@PathVariable int id,@RequestBody UserVo userVo){
        boolean b = idCardUtil.idNumberCheck(userVo.getIdNumber());
        if(b){
            userVo.setStatus("1");
            userVo.setCreditScore(50);
            userService.updateUser(id,userVo);
            return RestResponse.makeOKRsp("身份验证成功!");
        }else {
            return RestResponse.makeErrRsp("认证失败!请仔细查看身份证号是否错误!");
        }
    }

    @ApiOperation("查询管理员用户")
    @GetMapping("/queryAdminUsers")
    public RestObject<List<UserRo>> queryAdminusers(){
        return RestResponse.makeOKRsp(userService.queryAdminUser());
    }
}
