package com.example.covid19.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.covid19.common.lang.Result;
import com.example.covid19.entity.Class;
import com.example.covid19.entity.GradeClass;
import com.example.covid19.mapper.ClassMapper;
import com.example.covid19.mapper.GradeClassMapper;
import com.example.covid19.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-21
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/class")
public class ClassController {
    @Autowired
    private  ClassService classService;
    private  ClassMapper classMapper;
    private final GradeClassMapper gradeClassMapper;

    public ClassController(ClassMapper classMapper, GradeClassMapper gradeClassMapper) {
        this.classMapper = classMapper;
        this.gradeClassMapper = gradeClassMapper;
    }


    //查询全部教师
    @GetMapping("/select")
    public List<Class> list(){
        List<Class> classList = classService.list();
        System.out.println(classList);
        return classList;
    }


    //添加教师
    @PostMapping("/add")
    public Result add(@RequestBody Class c){
        c.setId(classService.count()+1);

        boolean save = classService.save(c);
        return Result.success(200,"添加成功",c);

    }

    //修改教师
    @PostMapping("/update")
    public  Result update(@RequestBody Class c){

        Integer id = c.getId();
        System.out.println(id);
        System.out.println("==================id");
        if(id!=null){
            classService.updateById(c);
            return Result.success(200,"修改成功",c);
        }else{
            return Result.fail(405,"修改失败",c);
        }
    }




    //删除教师
    @PostMapping("/delete{id}")
    public Result del(@PathVariable Integer id){
        return Result.success(classService.removeById(id));


    }

    @PostMapping("/search")
    public Result selectByCondition(@RequestParam("name") String name) {
        QueryWrapper<Class> classQueryWrapper = new QueryWrapper<>();
        name = "%"+name+"%";

        System.out.println("=================");
        List<Class> t = classMapper.fand(name);
        System.out.println("=================");
        return Result.fail(200,"修改失败",t);


    }



    @PostMapping ("/detail")

    public List<GradeClass> listGradeDetails(@RequestParam("id") Integer id){
        List<GradeClass> listGradeDetails = gradeClassMapper.fanddetail(id);
        System.out.println(listGradeDetails);
        return listGradeDetails;
    }


}
