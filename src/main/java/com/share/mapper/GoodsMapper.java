package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.Goods;
import com.share.ro.GoodsRo;
import com.share.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {
    //查询全部交易物品
    List<GoodsRo> queryAllGoods();

    //查询交易物品(用户id)
    List<GoodsRo> queryGoodsByUserId(@Param("userId")int userId);

    //查询交易物品(id)
    GoodsRo queryGoodsById(@Param("id")int id);

    //添加交易物品
    int addGoods(@Param("goodsVo") GoodsVo goodsVo);

    //修改交易物品信息(id)名字，描述，价格，库存，状态，图片。。
    int updateGoods(@Param("id")int id,@Param("goodsVo")GoodsVo goodsVo);

    //修改交易交易物品状态(删除)
    int updateGoodsStatus(@Param("id")int id,@Param("goodsVo")GoodsVo goodsVo);
}
