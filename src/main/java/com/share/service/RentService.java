package com.share.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.entity.User;
import com.share.mapper.RentMapper;
import com.share.mapper.UserMapper;
import com.share.ro.rentRo.RentRo;
import com.share.ro.rentRo.RentUserIdRo;
import com.share.vo.RentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RentService {
    @Autowired
    private RentMapper rentMapper;

    @Autowired
    private UserMapper userMapper;

    //按id查询出租中的房屋
    public RentRo queryRentById(int id){
        return rentMapper.queryRentById(id);
    }

    //查询出租中的车位
    public List<RentRo> queryAllRentPark(){
        List<RentRo> rentParkRos = rentMapper.queryAllRentPark();
        List<User> users = userMapper.selectList(
                new QueryWrapper<User>().in(rentParkRos != null && !rentParkRos.isEmpty(), "id",
                        rentParkRos.stream()
                                .map(RentRo::getUserIdRent)
                                .distinct()
                                .collect(Collectors.toList())));

        Map<Integer, User> map = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        rentParkRos.stream().forEach(it -> {
            if (map.containsKey(it.getUserIdRent())) {
                it.setPicture(map.get(it.getUserIdRent()).getPicture());
                it.setNickname(map.get(it.getUserIdRent()).getNickname());
            }
        });

        return rentParkRos;
    }

    //查询出租中的房屋
    public List<RentRo> queryAllRentDepart(){
        List<RentRo> rentRos = rentMapper.queryAllRentDepart();
        List<User> users = userMapper.selectList(
                new QueryWrapper<User>().in(rentRos != null && !rentRos.isEmpty(), "id",
                        rentRos.stream()
                                .map(RentRo::getUserIdRent)
                                .distinct()
                                .collect(Collectors.toList())));

        Map<Integer, User> map = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        rentRos.stream().forEach(it -> {
            if (map.containsKey(it.getUserIdRent())) {
                it.setPicture(map.get(it.getUserIdRent()).getPicture());
                it.setNickname(map.get(it.getUserIdRent()).getNickname());
            }
        });

        return rentRos;
    }

    //查询车位出租(出租人id)
    public List<RentUserIdRo> queryRentParkByUserId(int userIdRent){
        return rentMapper.queryRentParkByUserId(userIdRent);
    }

    //查询车位出租(租赁人id)
    public List<RentUserIdRo> queryRentParkByUserIdRenter(int userIdRenter){
        return rentMapper.queryRentParkByUserIdRenter(userIdRenter);
    }

    //查询房屋出租(出租人id)
    public List<RentUserIdRo> queryRentDepartByUserId(int userIdRent){
        return rentMapper.queryRentDepartByUserId(userIdRent);
    }

    //查询房屋出租(租赁人id)
    public List<RentUserIdRo> queryRentDepartByUserIdRenter(int userIdRenter){
        return rentMapper.queryRentDepartByUserIdRenter(userIdRenter);
    }

    //添加出租
    public int addRent(RentVo rentVo){
        int i = rentMapper.addRent(rentVo);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }

    //修改出租(id)类型,地址,价格
    public int updateRent(int id,RentVo rentVo){
        int i = rentMapper.updateRent(id, rentVo);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }

    //修改租赁人
    public int updateRenter(int id,RentVo rentVo){
        int i = rentMapper.updateRenter(id, rentVo);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }

    //修改出租状态
    public int updateStatus(int id,String status){
        int i = rentMapper.updateStatus(id, status);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }

    //删除租赁(id)
    public int deleteRent(int id){
        int i = rentMapper.deleteRent(id);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }

}
