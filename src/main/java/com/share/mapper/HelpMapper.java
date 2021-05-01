package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.Help;
import com.share.ro.helpRo.HelpRo;
import com.share.vo.HelpVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface
HelpMapper extends BaseMapper<Help> {
    //添加帮助
    int addHelp(@Param("helpVo")HelpVo helpVo,@Param("userId")int userId);

    //修改帮助(id)
    int updateHelp(@Param("id")int id,@Param("helpVo")HelpVo helpVo);

    //修改帮助人(id)
    int updateHelper(@Param("id")int id,@Param("helpVo")HelpVo helpVo);

    //删除帮助(id)
    int deleteHelp(@Param("id")int id);

    //查询所有正在求助的外卖
    List<HelpRo> queryAllDeliveryHelp();

    //查询所有正在求助的快递
    List<HelpRo> queryAllParcelHelp();
}
