package com.example.covid19.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.covid19.common.lang.Result;
import com.example.covid19.entity.Parent;
import com.example.covid19.entity.Student;
import com.example.covid19.mapper.ParentMapper;
import com.example.covid19.mapper.StudentMapper;
import com.example.covid19.service.ParentService;
import com.example.covid19.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-19
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/parent")
public class ParentController {

    @Autowired
    private ParentService parentService;
    private ParentMapper parentMapper;
    public ParentController(ParentMapper parentMapper) {
        this.parentMapper = parentMapper;
    }
    //查询全部
    @GetMapping("/select")
    public List<Parent> list(){
        List<Parent> parents = parentService.list();
        System.out.println(parents);
        return  parents;
    }

    //添加用户
    @PostMapping("/add")
    public Parent save(@RequestBody Parent parent){
        parent.setId(parentService.count()+1);
        System.out.println(parent.getAddress()+"=="+parent.getPhone()+"==="+parent.getAge());
        boolean save = parentService.save(parent);
        return null;
    }


    //修改用户
    @PostMapping("/update")
    public Result update(@RequestBody Parent parent){
        Integer id = parent.getId();
        System.out.println(id);
        if(id !=null){
            parentService.updateById(parent);
            System.out.println(parent);
            System.out.println("============================");
            return Result.success(200,"修改成功",parent);
        }else{
            return Result.fail(405,"修改失败",parent);
        }
    }
    //删除用户
    @PostMapping("/delete{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(parentService.removeById(id));
    }

    //name,email,address多条件模糊查询
    @PostMapping("/search")
    public Result selectByUsername(@RequestParam("name") String name,
                                   @RequestParam("age") String age,
                                   @RequestParam("phone") String phone) {
        QueryWrapper<Parent> parentQueryWrapper = new QueryWrapper<>();
        name = "%"+name+"%";
        age = "%"+age+"%";
        phone = "%"+phone+"%";
        List<Parent> s =parentMapper.fand(name,age,phone);
        return Result.fail(200,"修改失败",s);
    }






}

