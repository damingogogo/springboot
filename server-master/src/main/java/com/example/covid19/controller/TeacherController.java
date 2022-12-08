package com.example.covid19.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.covid19.common.lang.Result;
import com.example.covid19.entity.Teacher;
import com.example.covid19.mapper.TeacherMapper;
import com.example.covid19.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-18
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    public TeacherController(TeacherService teacherService, TeacherMapper teacherMapper) {
        this.teacherService = teacherService;
        this.teacherMapper = teacherMapper;
    }

    //查询全部教师
    @GetMapping("/select")
    public List<Teacher> list(){
        List<Teacher> teacherList = teacherService.list();
        System.out.println(teacherList);
        return teacherList;
    }


    //添加教师
    @PostMapping("/add")
    public Result add(@RequestBody Teacher teacher){
        teacher.setId(teacherService.count()+1);
        System.out.println(teacher.getAddress()+"=="+teacher.getName()+"==="+teacher.getSex());
        boolean save = teacherService.save(teacher);
        return Result.success(200,"添加成功",teacher);

    }

//修改教师
    @PostMapping("/update")
    public  Result update(@RequestBody Teacher teacher){

        Integer id = teacher.getId();
        System.out.println(id);
        System.out.println("==================id");
        if(id!=null){
            teacherService.updateById(teacher);
            return Result.success(200,"修改成功",teacher);
        }else{
            return Result.fail(405,"修改失败",teacher);
        }
    }




    //删除教师
  @PostMapping("/delete{id}")
    public Result del(@PathVariable Integer id){
        return Result.success(teacherService.removeById(id));


  }

    @PostMapping("/search")
    public Result selectByCondition(@RequestParam("name") String name,
                                   @RequestParam("sex") String sex,
                                   @RequestParam("phone") String phone) {
        QueryWrapper<Teacher> teacherQueryWrapper = new QueryWrapper<>();
        name = "%"+name+"%";
        sex = "%"+sex+"%";
        phone = "%"+phone+"%";
        System.out.println("=================");
        List<Teacher> t = teacherMapper.fand(name,sex,phone);
        System.out.println("=================");
            return Result.fail(200,"修改失败",t);


    }


}
