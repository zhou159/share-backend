package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.Authenticate;
import com.share.ro.AuthenticateRo;
import com.share.vo.AuthenticateVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthenticateMapper extends BaseMapper<Authenticate> {
    List<AuthenticateRo> findAuthenticateByAdminNickName(@Param("adminName")String adminName);

    int addAuthenticate(@Param("authenticateVo")AuthenticateVo authenticateVo);

    int updateStatus(@Param("id")int id,@Param("status")String status);
}
