package com.share.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.share.entity.Help;
import com.share.entity.User;
import com.share.mapper.HelpMapper;
import com.share.mapper.UserMapper;
import com.share.ro.helpRo.HelperRo;
import com.share.ro.helpRo.HelpRo;
import com.share.vo.HelpVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class HelpService {
    @Autowired
    HelpMapper helpMapper;

    @Autowired
    private UserMapper userMapper;

    public int addHelp(HelpVo helpVo,int userId){
        int i = helpMapper.addHelp(helpVo, userId);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }

    public int updateHelp(int id,HelpVo helpVo){
        int i = helpMapper.updateHelp(id, helpVo);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }

    public int updateHelper(int id,HelpVo helpVo){
        int i = helpMapper.updateHelper(id, helpVo);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }

    public int deleteHelp(int id){
        int i = helpMapper.deleteHelp(id);
        if (i>0){
            return 0;
        }else {
            return -1;
        }
    }

    //全部求助中的外卖
    public List<HelpRo> queryAllDeliveryHelp(){
        return helpMapper.queryAllDeliveryHelp();
    }

    //全部求助中的快递
    public List<HelpRo> queryAllParcelHelp(){
        return helpMapper.queryAllParcelHelp();
    }

//    按帮助者id查询帮助
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

        List<User> users = userMapper.selectList(
                new QueryWrapper<User>().in(helpRos != null && !helpRos.isEmpty(), "id",
                        helpRos.stream()
                                .map(HelpRo::getUserIdHelp)
                                .distinct()
                                .collect(Collectors.toList())));

        Map<Integer, User> map = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        helpRos.stream().forEach(it -> {
            if (map.containsKey(it.getUserIdHelp())) {
                it.setUpicture(map.get(it.getUserIdHelp()).getPicture());
                it.setNickname(map.get(it.getUserIdHelp()).getNickname());
            }
        });

        return helpRos;
    }

//    按求助者id查询帮助
    public List<HelperRo> queryHelpByUserId(int userIdHelp){
        QueryWrapper<Help> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id_help",userIdHelp);
        List<Help> help = helpMapper.selectList(wrapper);
        List<HelperRo> helpRos = new ArrayList<>();
        for (Help help1 : help) {
            HelperRo helpRo = new HelperRo();
            BeanUtils.copyProperties(help1,helpRo);
            helpRos.add(helpRo);
        }

        List<User> users = userMapper.selectList(
                new QueryWrapper<User>().in(helpRos != null && !helpRos.isEmpty(), "id",
                        helpRos.stream()
                                .map(HelperRo::getUserIdHelper)
                                .distinct()
                                .collect(Collectors.toList())));

        Map<Integer, User> map = users.stream().collect(Collectors.toMap(User::getId, Function.identity()));
        helpRos.stream().forEach(it -> {
            if (map.containsKey(it.getUserIdHelper())) {
                it.setUrPicture(map.get(it.getUserIdHelper()).getPicture());
                it.setUrNickname(map.get(it.getUserIdHelper()).getNickname());
            }
        });

        return helpRos;
    }

    public HelpRo queryHelpById(int id){
        Help help1 = helpMapper.selectById(id);
        HelpRo helpRo = new HelpRo();
        BeanUtils.copyProperties(help1,helpRo);

        User users = userMapper.selectById(helpRo.getUserIdHelp());
        helpRo.setUpicture(users.getPicture());
        helpRo.setNickname(users.getNickname());

        return helpRo;
    }

}
