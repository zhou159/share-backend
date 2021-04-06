package com.share.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.entity.Help;
import com.share.mapper.HelpMapper;
import com.share.ro.HelpRo;
import com.share.vo.HelpVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HelpService {
    @Autowired
    HelpMapper helpMapper;

    public int addHelp(HelpVo helpVo){
        helpMapper.addHelp(helpVo);
        return 0;
    }

    public int updateHelp(int id,HelpVo helpVo){
        helpMapper.updateHelp(id, helpVo);
        return 0;
    }

    public int updateHelper(int id,HelpVo helpVo){
        helpMapper.updateHelper(id, helpVo);
        return 0;
    }

    public int deleteHelp(int id){
        helpMapper.deleteHelp(id);
        return 0;
    }

    public List<HelpRo> queryAllHelp(){
        return helpMapper.queryAllHelp();
    }

    public List<HelpRo> queryHelperByUserId(int userIdHelper){
        QueryWrapper<Help> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id_helper",userIdHelper);
        List<Help> help = helpMapper.selectList(wrapper);
        List<HelpRo> helpRos = new ArrayList<>();
        for (Help help1 : help) {
            HelpRo helpRo = new HelpRo();
            BeanUtils.copyProperties(help1,helpRo);
            helpRos.add(helpRo);
        }
        return helpRos;
    }

    public List<HelpRo> queryHelpByUserId(int userIdHelp){
        QueryWrapper<Help> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id_help",userIdHelp);
        List<Help> help = helpMapper.selectList(wrapper);
        List<HelpRo> helpRos = new ArrayList<>();
        for (Help help1 : help) {
            HelpRo helpRo = new HelpRo();
            BeanUtils.copyProperties(help1,helpRo);
            helpRos.add(helpRo);
        }
        return helpRos;
    }

    public HelpRo queryHelpById(int id){
        Help help1 = helpMapper.selectById(id);
        HelpRo helpRo = new HelpRo();
        BeanUtils.copyProperties(help1,helpRo);
        return helpRo;
    }

}
