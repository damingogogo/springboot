package com.example.covid19.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.covid19.common.lang.Result;
import com.example.covid19.entity.School;
import com.example.covid19.mapper.SchoolMapper;
import com.example.covid19.service.SchoolService;
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
@RequestMapping("/school")
public class SchoolController {

    @Autowired
    private final SchoolService schoolService;
    private final SchoolMapper schoolMapper;
    public SchoolController(SchoolService schoolService, SchoolMapper schoolMapper) {
        this.schoolService = schoolService;
        this.schoolMapper = schoolMapper;
    }
    //查询全部
    @GetMapping("/select")
    public List<School> list(){
        List<School> schools =schoolService.list();
        System.out.println(schools);
        return  schools;
    }

    //添加用户
    @PostMapping("/add")
    public School save(@RequestBody School school){
        school.setId(schoolService.count()+1);
//        System.out.println(school.getName()+"=="+school.getPhone()+"==="+school.getAge());
        boolean save = schoolService.save(school);
        return null;
    }


    //修改用户
    @PostMapping("/update")
    public Result update(@RequestBody School school){
        Integer id = school.getId();
        System.out.println(id);
        if(id !=null){
            schoolService.updateById(school);
            System.out.println(school);
            System.out.println("============================");
            return Result.success(200,"修改成功",school);
        }else{
            return Result.fail(405,"修改失败",school);
        }
    }
    //删除用户
    @PostMapping("/delete{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(schoolService.removeById(id));
    }

  //  name,email,address多条件模糊查询
    @PostMapping("/search")
    public Result selectByUsername(@RequestParam("name") String name,
                                   @RequestParam("address") String address) {
        QueryWrapper<School> schoolQueryWrapper = new QueryWrapper<>();
        name = "%"+name+"%";
        address = "%"+address+"%";
        List<School> s =schoolMapper.fand(name,address);
        return Result.fail(200,"修改失败",s);
    }






}
