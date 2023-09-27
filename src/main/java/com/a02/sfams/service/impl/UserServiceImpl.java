package com.a02.sfams.service.impl;

import com.a02.sfams.dto.Result;

import com.a02.sfams.dto.Token;
import com.a02.sfams.entity.Asset;
import com.a02.sfams.entity.User;
import com.a02.sfams.entity.vo.AssetVo;
import com.a02.sfams.entity.vo.QueryVo;
import com.a02.sfams.mapper.AssetMapper;
import com.a02.sfams.mapper.UserMapper;
import com.a02.sfams.service.UserService;
import com.a02.sfams.utils.DateUtils;
import com.a02.sfams.utils.JwtHelper;
import com.a02.sfams.utils.UserHolder;
import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.mapping.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper, User> implements UserService {



    @Autowired
    private UserMapper userMapper;
    /**
     * 用户登录
     * @param user
     * @return 封装的result对象
     */
    @Override
    public Result login(User user) {
        if(StringUtils.isEmpty(user.getUserNumber())||StringUtils.isEmpty(user.getPassword())){

            return Result.fail("缺少必要的参数");
        }

        String userNumber=user.getUserNumber();
        String password=user.getPassword();
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("user_number",userNumber);
        User userObj=userMapper.selectOne(wrapper);
        if(userObj==null){
            return Result.fail("用户不存在");
        }

        if(!userObj.getPassword().equals(password)){
            return Result.fail("密码错误");
        }

        String token= JwtHelper.createToken(userObj.getId(),userObj.getUserNumber());
        String userId=JwtHelper.getUserId(token);


        System.out.println(userId+"用户登录成功");

        HashMap<String,Object>data=new HashMap<>();
        data.put("token",token);
        data.put("userNumber",userNumber);


        return Result.ok(data);
    }

    @Override
    public Result getUserInfo(String id) {

        String userId=UserHolder.getUser();
        if(!StringUtils.isEmpty(userId)&&StringUtils.isEmpty(id)){
            id=userId;
        }

        if(StringUtils.isEmpty(id)){
            return Result.fail("缺少参数或不存在对应token");
        }

        User user=userMapper.selectById(id);
        if(user==null){
            return Result.fail("未能查找到相应的信息");
        }
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("user",user);
        return Result.ok(resultMap);

    }

    @Override
    public Result logout() {
        String userId=UserHolder.getUser();
        if(StringUtils.isEmpty(userId)){
            return Result.ok("错误的Token，强制登出");
        }


        UserHolder.removeUser();

        return Result.ok("退出登录");


    }

    @Override
    public Result page(Long page, Integer limit, QueryVo queryVo) {

        Map<String,Object>resultMap=new HashMap<>();
        Page<User> pageParam=new Page<>(page,limit);
        QueryWrapper<User>wrapper=new QueryWrapper<>();
        if(!org.springframework.util.StringUtils.isEmpty(queryVo.getName())){
            wrapper.like("name",queryVo.getName());
        }
        Page<User>pageModel=baseMapper.selectPage(pageParam,wrapper);
        List<User> records=pageModel.getRecords();



        System.out.println(records);

        long pages=pageModel.getPages();
        long total=pageModel.getTotal();
        resultMap.put("records",records);
        resultMap.put("total",total);
        resultMap.put("pages",pages);
        resultMap.put("current",page);
        return Result.ok(resultMap);

    }

    @Override
    public Result query(QueryVo queryVo) {
        Map<String, Object> resultMap = new HashMap<>();
        User user=null;
        if(!StringUtils.isEmpty(queryVo.getNumber())){
            QueryWrapper<User>wrapper=new QueryWrapper<>();
            wrapper.eq("user_number",queryVo.getNumber());

            user=userMapper.selectOne(wrapper);
        }else if(!StringUtils.isEmpty((queryVo.getName()))){
            QueryWrapper<User>wrapper=new QueryWrapper<>();
            wrapper.eq("name",queryVo.getName());
            user=userMapper.selectOne(wrapper);
        }else{
            return Result.fail("缺少query的信息内容");
        }

        resultMap.put("user",user);
        return Result.ok(resultMap);
    }


    private String createUserNumber() {
        String dateString = DateUtils.createDateString();
        //查询账户
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.like("user_number",dateString+"%");
        wrapper.orderByDesc(" user_number ");
        wrapper.last(" limit 1");
        User user = userMapper.selectOne(wrapper);
        if(user==null){
            dateString+="0000";
        }else{
            String userNumber = user.getUserNumber();
            Integer tail = Integer.valueOf(userNumber.substring(dateString.length()));
            tail++;
            String space="";
            if(tail<10) space="000"+tail;
            else if(tail<100) space="00"+tail;
            else if(tail<1000) space="0"+tail;
            dateString+=space;
        }
        return dateString;
    }

}
