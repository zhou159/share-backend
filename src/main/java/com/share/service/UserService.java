package com.share.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.mapper.UserMapper;
import com.share.entity.User;
import com.share.vo.UserVo;
import com.share.ro.userRo.UserRo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

//    @Value("${privateKey}")
//    private String privateKey;

    //查询所有用户
    public List<UserRo> queryUserAll(){
        return userMapper.queryUserAll();
    }

    //按id查询用户
    public UserRo queryUserById(int id){
        return userMapper.queryUserById(id);
    }

    public UserRo queryOtherUserById(int id){
        return userMapper.queryUserById(id);
    }

    //按用户名、密码查询用户
    public User queryByUsername(UserVo vo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("useraccount",vo.getUsername());
        queryWrapper.eq("password",vo.getPassword());
        return userMapper.selectOne(queryWrapper);
    }

    //按电话号码查询用户
    public User queryByTel(UserVo vo){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel",vo.getTel());
        queryWrapper.eq("password",vo.getPassword());
        return userMapper.selectOne(queryWrapper);
    }


    //按用户名方式注册用户
    public void registerUsername(UserVo vo){
        userMapper.registerUsername(vo.getUsername(), vo.getPassword(), vo.getNickname());
    }

    //按电话号码方式注册用户
    public void registerTel(UserVo vo){
        userMapper.registerTel(vo.getTel(),vo.getPassword() ,vo.getNickname());
    }

    public Boolean checkAdmin(int userId){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("id",userId).eq("type","a");
        User user = userMapper.selectOne(userQueryWrapper);
        if (user!=null){
            return true;
        }else {
            return false;
        }
    }

    //新增管理员用户
    public void registerAdmin(UserVo vo){
        userMapper.registerAdmin(vo.getTel(),vo.getPassword() ,vo.getNickname());
    }

    //通过电话号、用户名查询用户
    public UserRo queryUserByTA(UserVo vo){
        return userMapper.queryUserByTA(vo.getTel(),vo.getUsername());
    }

    //更新用户信息
    public int updateUser(int id,UserVo vo){
        User user = new User();
        BeanUtils.copyProperties(vo,user);
        user.setId(id);
        userMapper.updateById(user);
        return 0;
    }

    //更改电话号码、密码
    public int updateTP(int id,UserVo vo){
        userMapper.updateTP(id,vo);
        return 0;
    }

    //忘记密码
    public int updatePByT(int id,UserVo vo){
        userMapper.updatePByT(id,vo);
        return 0;
    }

    //修改图片
    public void updatePicture(int id,String picture){
        User user = new User();
        user.setId(id);
        user.setPicture(picture);
        userMapper.updateById(user);
    }

    //通过nickname查询用户
    public User queryByNickName(String nickname){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("nickname",nickname);
        return userMapper.selectOne(wrapper);
    }

    //查询管理员用户
    public List<UserRo> queryAdminUser(){
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("roles","a");
        ArrayList<UserRo> userRos = new ArrayList<>();
        for (User user : userMapper.selectList(wrapper)) {
            UserRo ro = new UserRo();
            BeanUtils.copyProperties(user,ro);
            userRos.add(ro);
        }
        return userRos;
    }

    //修改用户信誉分
    public boolean updateCreditScore(int userId,int creditScore,String option){
        UserRo userRo = userMapper.queryUserById(userId);
        Integer creditScore1 = userRo.getCreditScore();
        User user = new User();
        user.setId(userId);
        if (option.equals("add")){
            user.setCreditScore(creditScore+creditScore1);
        }else if (option.equals("sub")){
            if (creditScore1-creditScore <= 0){
                user.setCreditScore(0);
            }else {
                user.setCreditScore(creditScore1-creditScore);
            }
        }
        int i = userMapper.updateById(user);
        if (i>0){
            return true;
        }else {
            return false;
        }
    }
}
