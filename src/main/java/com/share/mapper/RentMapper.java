package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.Rent;
import com.share.ro.RentRo;
import com.share.vo.RentVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RentMapper extends BaseMapper<Rent> {
    //查询全部租赁
    List<RentRo> queryAllRent();

    //查询出租(出租人id)
    List<RentRo> queryRentByUserId(@Param("userIdRent")int userIdRent);

    //添加出租
    int addRent(@Param("rentVo")RentVo rentVo);

    //修改出租(id)类型,地址,价格
    int updateRent(@Param("id")int id,@Param("rentVo")RentVo rentVo);

    //修改租赁人
    int updateRenter(@Param("id")int id,@Param("rentVo")RentVo rentVo);

    //修改出租状态
    int updateStatus(@Param("id")int id,@Param("rentVo")RentVo rentVo);

    //删除租赁(id)
    int deleteRent(@Param("id")int id,@Param("rentVo")RentVo rentVo);
}
