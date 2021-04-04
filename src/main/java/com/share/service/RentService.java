package com.share.service;

import com.share.mapper.RentMapper;
import com.share.ro.RentRo;
import com.share.vo.RentVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {
    @Autowired
    RentMapper rentMapper;

    //查询全部租赁
    public List<RentRo> queryAllRent(){
        return rentMapper.queryAllRent();
    }

    //查询出租(出租人id)
    public List<RentRo> queryRentByUserId(int userIdRent){
        return rentMapper.queryRentByUserId(userIdRent);
    }

    //添加出租
    public int addRent(RentVo rentVo){
        rentMapper.addRent(rentVo);
        return 0;
    }

    //修改出租(id)类型,地址,价格
    public int updateRent(int id,RentVo rentVo){
        rentMapper.updateRent(id, rentVo);
        return 0;
    }

    //修改租赁人
    public int updateRenter(int id,RentVo rentVo){
        rentMapper.updateRenter(id, rentVo);
        return 0;
    }

    //修改出租状态
    public int updateStatus(int id,RentVo rentVo){
        rentMapper.updateStatus(id, rentVo);
        return 0;
    }

    //删除租赁(id)
    public int deleteRent(int id,RentVo rentVo){
        rentMapper.deleteRent(id, rentVo);
        return 0;
    }


}
