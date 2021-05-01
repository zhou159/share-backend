package com.share.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.entity.Authenticate;
import com.share.mapper.AuthenticateMapper;
import com.share.ro.authenticateRo.AuthenticateRo;
import com.share.vo.AuthenticateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticateService {
    @Autowired
    AuthenticateMapper authenticateMapper;

    //按管理员nickname查询审核
    public List<AuthenticateRo> findAuthenticateByAdminUserId(int adminNickname){
        return authenticateMapper.findAuthenticateByAdminUserId(adminNickname);
    }

    public Authenticate findAuthenticateById(int id){
        QueryWrapper<Authenticate> wrapper = new QueryWrapper<>();
        wrapper.eq("id",id);
        return authenticateMapper.selectOne(wrapper);
    }

    public int addAuthenticate(AuthenticateVo authenticateVo){
        authenticateMapper.addAuthenticate(authenticateVo);
        return 0;
    }

    public int updateStatus(int id,String status){
        authenticateMapper.updateStatus(id, status);
        return 0;
    }
}
