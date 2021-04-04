package com.share.service;

import com.share.mapper.TravelMapper;
import com.share.ro.TravelRo;
import com.share.vo.TravelVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelService {
    @Autowired
    TravelMapper travelMapper;

    //查询全部出行
    public List<TravelRo> queryAllTravel(){
        return travelMapper.queryAllTravel();
    }

    //查询出行(用户id)
    public List<TravelRo> queryTravelByUserId(int userId){
        return travelMapper.queryTravelByUserId(userId);
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
