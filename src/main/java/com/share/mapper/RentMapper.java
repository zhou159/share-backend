package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.Rent;
import com.share.ro.rentRo.RentRo;
import com.share.ro.rentRo.RentUserIdRo;
import com.share.vo.RentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RentMapper extends BaseMapper<Rent> {
    //按id查询出租中的东西
    RentRo queryRentById(@Param("id")int id);

    //查询全部出租中的车位
    List<RentRo> queryAllRentPark();

    //查询全部出租中的房屋
    List<RentRo> queryAllRentDepart();

    //查询房屋出租(出租人id)
    List<RentUserIdRo> queryRentDepartByUserId(@Param("userIdRent")int userIdRent);

    //查询房屋出租(出租人id)
    List<RentUserIdRo> queryRentDepartByUserIdRenter(@Param("userIdRenter")int userIdRenter);

    //查询车位出租(出租人id)
    List<RentUserIdRo> queryRentParkByUserId(@Param("userIdRent")int userIdRent);

    //查询车位出租(出租人id)
    List<RentUserIdRo> queryRentParkByUserIdRenter(@Param("userIdRenter")int userIdRenter);

    //添加出租
    int addRent(@Param("rentVo")RentVo rentVo);

    //修改出租(id)类型,地址,价格
    int updateRent(@Param("id")int id,@Param("rentVo")RentVo rentVo);

    //修改租赁人
    int updateRenter(@Param("id")int id,@Param("rentVo")RentVo rentVo);

    //修改出租状态
    int updateStatus(@Param("id")int id,@Param("status")String status);

    //删除租赁(id)
    int deleteRent(@Param("id")int id);
}
