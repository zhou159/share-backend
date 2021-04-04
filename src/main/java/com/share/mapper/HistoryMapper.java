package com.share.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.share.entity.History;
import com.share.ro.HistoryRo;
import com.share.vo.HistoryVo;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface HistoryMapper extends BaseMapper<History> {
    //添加历史记录
    int addHistory(@Param("historyVo") HistoryVo historyVo);

    //删除历史记录(id)
    int deleteHistory(@Param("id")int id);

    //查询历史记录(用户id)
    List<HistoryRo> queryHistoryByUserId(@Param("userId")int userId);

    //查询历史记录(对象id),新增历史记录时查看,不单独使用
    HistoryRo queryHistoryByObjId(@Param("historyVo")HistoryVo historyVo);

    //修改时间
    int updateHistory(@Param("historyVo")HistoryVo historyVo);

}
