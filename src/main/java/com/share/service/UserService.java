package com.share.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.mapper.UserMapper;
import com.share.entity.User;
import com.share.vo.UserVo;
import com.share.ro.UserRo;
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

    public List<UserRo> queryUserAll(){
        List<User> users = userMapper.queryUserAll();
        List<UserRo> list = new ArrayList<>();
        for (User user : users) {
            UserRo ro = new UserRo();
//            字段名相同，类型一样，就会自动拷贝
            BeanUtils.copyProperties(user,ro);
            list.add(ro);
        }
        return list;
    }

    public UserRo queryUserById(int id){
        return userMapper.queryUserById(id);
    }

//    public boolean queryByUsername(UserVo vo) {
//        if(vo.getPassword().equals("") || vo.getUsername().equals("")){
//            throw new UserException("账号或密码不能为空!");
//        }else{
//            RSA rsa = new RSA(privateKey,null);
//            String username = rsa.decryptStr(vo.getUsername(), KeyType.PrivateKey);
//            String password = rsa.decryptStr(vo.getPassword(),KeyType.PrivateKey);
//
//            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//            queryWrapper.eq("useraccount",username);
//            queryWrapper.eq("password",password);
//            User user = userMapper.selectOne(queryWrapper);
//            if (user != null) {
//                return true;
//            }
//            return false;
//        }
//
//    }

    public boolean queryByUsername(UserVo vo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("useraccount",vo.getUsername());
        queryWrapper.eq("password",vo.getPassword());
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            return true;
        }
        return false;
    }


    public boolean queryByTel(UserVo vo){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("tel",vo.getTel());
        queryWrapper.eq("password",vo.getPassword());
        User user = userMapper.selectOne(queryWrapper);
        if (user!=null){
            return  true;
        }
        return false;
    }

    public void registerUsername(UserVo vo){
        userMapper.registerUsername(vo.getUsername(), vo.getPassword());
    }

    public void registerTel(UserVo vo){
        userMapper.registerTel(vo.getTel(), vo.getPassword());
    }

    public User queryUserByTA(UserVo vo){
        return userMapper.queryUserByTA(vo.getTel(),vo.getUsername());
    }

//    public int updateUser(int id,UserRo ro){
//        User user = new User();
//        BeanUtils.copyProperties(ro,user);
//        user.setId(id);
//        userMapper.updateById(user);
//        return 0;
//    }
    public int updateUser(int id,UserVo vo){
        User user = new User();
        BeanUtils.copyProperties(vo,user);
        user.setId(id);
        userMapper.updateById(user);
        //userMapper.updateUserById(id,vo);
        return 0;
    }

//    public int updateTel(int id,UserVo vo){
//        userMapper.updateTel(id,vo);
//        return 0;
//    }

    public int updateTP(int id,UserVo vo){
        userMapper.updateTP(id,vo);
        return 0;
    }
}
