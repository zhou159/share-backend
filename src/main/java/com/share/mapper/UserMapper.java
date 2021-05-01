package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.User;
import com.share.ro.userRo.UserRo;
import com.share.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    //查询全部用户
    List<UserRo> queryUserAll();

    //查询用户id
    UserRo queryUserById(@Param("id")Integer id);

    //查询用户tel,useraccount
    User queryUserByTA(@Param("tel")String tel,@Param("username") String username);

    //登录:账号方式
    User queryByAccount(@Param("username") String username, @Param("password") String password);

    //登录:电话号码
    User queryByTel(@Param("tel") String tel, @Param("password") String password);

    //注册:用户名(增加)
    int registerUsername(@Param("username") String username, @Param("password") String password,@Param("nickname")String nickname);

    //注册:电话号(增加)
    int registerTel(@Param("tel") String tel, @Param("password") String password,@Param("nickname")String nickname);

    //修改密码
    int updateTP(@Param("id")Integer id,@Param("UserVo")UserVo userVo);

}
