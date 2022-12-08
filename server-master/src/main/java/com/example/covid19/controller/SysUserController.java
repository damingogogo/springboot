package com.example.covid19.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.covid19.common.lang.Result;
import com.example.covid19.entity.SysUser;
import com.example.covid19.mapper.SysUserMapper;
import com.example.covid19.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-15
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/user")
public class SysUserController {

    @Autowired
    private final SysUserService sysUserService;
    private final SysUserMapper sysUserMapper;

    public SysUserController(SysUserService sysUserService, SysUserMapper sysUserMapper) {
        this.sysUserService = sysUserService;
        this.sysUserMapper = sysUserMapper;
    }

    //查询全部
    @GetMapping("/select")
    public List<SysUser> list(){
      List<SysUser> sysUsers = sysUserService.list();
        System.out.println(sysUsers);
      return  sysUsers;
    }

//添加用户
    @PostMapping ("/add")
    public SysUser save(@RequestBody SysUser sysUser){
        sysUser.setId(sysUserService.count()+1);
        System.out.println(sysUser.getAddress()+"=="+sysUser.getUsername());
        boolean save = sysUserService.save(sysUser);
        return null;
    }


    //修改用户
    @PostMapping("/update")
    public Result update(@RequestBody SysUser sysUser){
        Integer id = sysUser.getId();
        System.out.println(id);
        if(id !=null){
            sysUserService.updateById(sysUser);
            System.out.println(sysUser);
            System.out.println("============================");
            return Result.success(200,"修改成功",sysUser);
        }else{
            return Result.fail(405,"修改失败",sysUser);
        }
    }
//删除用户
    @PostMapping("/delete{id}")
    public Result delete(@PathVariable Integer id){
     return Result.success(sysUserService.removeById(id));
    }

//username,email,address多条件模糊查询
    @PostMapping("/search")
    public Result selectByUsername(@RequestParam("username") String username,
                                   @RequestParam("email") String email,
                                   @RequestParam("address") String address) {
        QueryWrapper<SysUser> sysUserQueryWrapper = new QueryWrapper<>();
        System.out.println("===========================================");
        username = "%"+username+"%";
        email = "%"+email+"%";
        address = "%"+address+"%";

        List<SysUser> s =sysUserMapper.fand(username,email,address);
        return Result.fail(200,"修改失败",s);
    }






}
