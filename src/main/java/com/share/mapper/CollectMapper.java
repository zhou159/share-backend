package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.Collect;
import com.share.ro.CollectRo;
import com.share.vo.CollectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CollectMapper extends BaseMapper<Collect> {
    //添加收藏(用户id)
    int addCollect(@Param("collectVo") CollectVo collectVo);

    //删除收藏(收藏物id)
    int deleteCollect(@Param("id")int id);

    //查询收藏(用户id<=>某一用户的全部查询)
    List<CollectRo> queryCollectByUserId(@Param("userId")int userId);

    //查询收藏(收藏物id)
    CollectRo queryCollectById(@Param("id")int id);


}
