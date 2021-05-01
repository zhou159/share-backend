package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.Travel;
import com.share.ro.travelRo.TravelRo;
import com.share.vo.TravelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TravelMapper extends BaseMapper<Travel> {
    //查询全部出行
    List<TravelRo> queryAllTravel();

    //查询出行(用户id)
    List<TravelRo> queryTravelByUserId(@Param("userId")int userId);

    //添加出行
    int addTravel(@Param("travelVo")TravelVo travelVo);

    //修改出行(id)
    int updateTravel(@Param("id")int id,@Param("travelVo")TravelVo travelVo);

    //修改出行状态
    int updateStatus(@Param("id")int id,@Param("travelVo")TravelVo travelVo);

    //删除出行(id)
    int deleteTravel(@Param("id")int id,@Param("travelVo")TravelVo travelVo);
}
