package com.share.service;

import com.share.entity.History;
import com.share.mapper.HistoryMapper;
import com.share.ro.historyRo.GoodsHistoryRo;
import com.share.ro.historyRo.HistoryRo;
import com.share.ro.historyRo.RentHsitoryRo;
import com.share.ro.historyRo.TravelHistoryRo;
import com.share.vo.HistoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryService {
    @Autowired
    HistoryMapper historyMapper;

    public int addHistory(HistoryVo historyVo){
        int i = historyMapper.addHistory(historyVo);
        if (i>0){
            return 0;
        }else {
            return -1;
        }

    }

    public int deleteHistory(int id){
        int i = historyMapper.deleteHistory(id);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }

    public List<GoodsHistoryRo> queryGoodsHistoryByUserId(int userId){
        return historyMapper.queryGoodsHistoryByUserId(userId);
    }

    public List<RentHsitoryRo> queryRentHistoryByUserId(int userId){
        return historyMapper.queryRentHistoryByUserId(userId);
    }

    public List<TravelHistoryRo> queryTravelHistoryByUserId(int userId){
        return historyMapper.queryTravelHistoryByUserId(userId);
    }

    public HistoryRo queryHistoryByObjId(HistoryVo historyVo,int userId){
        return historyMapper.queryHistoryByObjId(historyVo,userId);
    }

    public History queryHistoryById(int id){
        return historyMapper.selectById(id);
    }

    public int updateHistory(HistoryVo historyVo){
        int i = historyMapper.updateHistory(historyVo);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }

}
