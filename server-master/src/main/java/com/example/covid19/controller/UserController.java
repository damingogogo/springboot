package com.example.covid19.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.covid19.common.lang.Result;
import com.example.covid19.entity.SysUser;
import com.example.covid19.entity.User;
import com.example.covid19.mapper.UserMapper;
import com.example.covid19.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-22
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user1")
public class UserController {

    @Autowired
    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }


    //查询全部
    @GetMapping("/select")
    public List<User> list(){
        List<User> users = userService.list();
        System.out.println(users);
        return  users;
    }

    //添加用户
    @PostMapping("/add")
    public SysUser save(@RequestBody User user){
        user.setId(userService.count()+1);
        System.out.println(user.getUsername()+"=="+user.getPassword());
        boolean save = userService.save(user);
        return null;
    }


    //修改用户
    @PostMapping("/update")
    public Result update(@RequestBody User user){
        Integer id = user.getId();
        System.out.println(id);
        if(id !=null){
            userService.updateById(user);
            System.out.println(user);
            System.out.println("============================");
            return Result.success(200,"修改成功",user);
        }else{
            return Result.fail(405,"修改失败",user);
        }
    }
    //删除用户
    @PostMapping("/delete{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(userService.removeById(id));
    }

    //username,email,address多条件模糊查询
    @PostMapping("/search")
    public Result selectByUsername(@RequestParam("username") String username,
                                   @RequestParam("phone") String phone) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();

        username = "%"+username+"%";
        phone = "%"+phone+"%";

        List<User> s =userMapper.fand(username,phone);
        return Result.fail(200,"修改失败",s);
    }






}
