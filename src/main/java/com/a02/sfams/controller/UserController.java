package com.a02.sfams.controller;

import com.a02.sfams.dto.Result;
import com.a02.sfams.entity.User;
import com.a02.sfams.entity.vo.QueryVo;
import com.a02.sfams.mapper.UserMapper;
import com.a02.sfams.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;
    @ApiOperation("用户分页查询")
    @GetMapping("/page/{page}/{limit}")
    public Result page(@PathVariable Long page, @PathVariable Integer limit, QueryVo queryVo){
        return userService.page(page,limit,queryVo);
    }

    @GetMapping ("/query")
    public Result queryUser(QueryVo queryVo){


        return userService.query(queryVo);

    }

    @PostMapping("/insert")
    public String save(User user){
        int i= userMapper.insert(user);
        if(i>=0){
            return "插入成功!";
        }else{
            return "插入失败!";
        }

    }

    @PostMapping("/update")
    public String update(User user){
        int i= userMapper.updateById(user);
        if(i>=0){
            return "更新成功!";
        }else{
            return "更新失败!";
        }

    }

    @PostMapping("/delete")
    public String delete(int id)
    {
        int i= userMapper.deleteById(id);
        if(i >0){
            return "删除成功！";
        }else {
            return "删除失败";
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user){
        return userService.login(user);
    }


    @PostMapping("/logout")
    public Result logout(){
        return userService.logout();
    }


    @GetMapping("/info")
    public Result getUserInfo(String id){
        return userService.getUserInfo(id);
    }



}
