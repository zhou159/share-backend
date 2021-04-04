package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.Help;
import com.share.ro.HelpRo;
import com.share.vo.HelpVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface
HelpMapper extends BaseMapper<Help> {
    //添加帮助
    int addHelp(@Param("helpVo")HelpVo helpVo);

    //修改帮助(id)
    int updateHelp(@Param("id")int id,@Param("helpVo")HelpVo helpVo);

    //修改帮助人(id)
    int updateHelper(@Param("id")int id,@Param("helpVo")HelpVo helpVo);

    //删除帮助(id)
    int deleteHelp(@Param("id")int id);

    //查询全部帮助
    List<HelpRo> queryAllHelp();

    //查询帮助(用户id-帮助)
    List<HelpRo> queryHelperByUserId(@Param("userIdHelper")int userIdHelper);

    //查询帮助(用户id-求助)
    List<HelpRo> queryHelpByUserId(@Param("userIdHelp")int userIdHelp);

    //查询帮助(id)
    HelpRo queryHelpById(@Param("id")int id);
}
