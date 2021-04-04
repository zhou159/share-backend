package com.share.service;

import com.share.mapper.HistoryMapper;
import com.share.ro.HistoryRo;
import com.share.vo.HistoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    HistoryMapper historyMapper;

    public int addHistory(HistoryVo historyVo){
        historyMapper.addHistory(historyVo);
        return 0;
    }

    public int deleteHistory(int id){
        historyMapper.deleteHistory(id);
        return 0;
    }

    public List<HistoryRo> queryHistoryByUserId(int userId){
        return historyMapper.queryHistoryByUserId(userId);
    }

    public HistoryRo queryHistoryByObjId(HistoryVo historyVo){
        return historyMapper.queryHistoryByObjId(historyVo);
    }

    public int updateHistory(HistoryVo historyVo){
        return historyMapper.updateHistory(historyVo);
    }


}
