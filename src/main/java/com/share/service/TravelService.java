package com.share.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.entity.Help;
import com.share.entity.Travel;
import com.share.entity.User;
import com.share.mapper.TravelMapper;
import com.share.mapper.UserMapper;
import com.share.ro.helpRo.HelpRo;
import com.share.ro.rentRo.RentRo;
import com.share.ro.travelRo.TravelRo;
import com.share.vo.TravelVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class TravelService {
    @Autowired
    TravelMapper travelMapper;

    @Autowired
    private UserMapper userMapper;

    //查询全部出行
    public List<TravelRo> queryAllTravel(){
        List<TravelRo> travelRos = travelMapper.queryAllTravel();
        List<User> users = userMapper.selectList(
                new QueryWrapper<User>().in(travelRos != null && !travelRos.isEmpty(), "id",
                        travelRos.stream()
                                .map(TravelRo::getUserId)
                                .distinct()
                                .collect(Collectors.toList())));

        Map<Integer, User> map = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        travelRos.stream().forEach(it -> {
            if (map.containsKey(it.getUserId())) {
                it.setPicture(map.get(it.getUserId()).getPicture());
                it.setNickname(map.get(it.getUserId()).getNickname());
            }
        });

        return travelRos;
    }

    //查询出行(用户id)
    public List<TravelRo> queryTravelByUserId(int userId){
        return travelMapper.queryTravelByUserId(userId);
    }

    //查询出行(用户id)
    public TravelRo queryTravelById(int id){
        Travel travel = travelMapper.selectById(id);
        TravelRo travelRo = new TravelRo();
        BeanUtils.copyProperties(travel,travelRo);

        User users = userMapper.selectById(travelRo.getUserId());
        travelRo.setPicture(users.getPicture());
        travelRo.setNickname(users.getNickname());

        return travelRo;
    }

    //添加出行
    public int addTravel(TravelVo travelVo){
        travelMapper.addTravel(travelVo);
        return 0;
    }

    //修改出行(id)
    public int updateTravel(int id,TravelVo travelVo){
        travelMapper.updateTravel(id, travelVo);
        return 0;
    }

    //修改出行状态
    public int updateStatus(int id,TravelVo travelVo){
        travelMapper.updateStatus(id, travelVo);
        return 0;
    }

    //删除出行(id)
    public int deleteTravel(int id,TravelVo travelVo){
        travelMapper.deleteTravel(id, travelVo);
        return 0;
    }

}
