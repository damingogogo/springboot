package com.example.covid19.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.covid19.common.lang.Result;
import com.example.covid19.entity.Grade;
import com.example.covid19.entity.SchoolGrade;
import com.example.covid19.mapper.GradeMapper;
import com.example.covid19.mapper.SchoolGradeMapper;
import com.example.covid19.service.GradeService;
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
@RequestMapping("/grade")
public class GradeController {
    @Autowired
    private final GradeService gradeService;
    private final GradeMapper gradeMapper;
    private final SchoolGradeMapper schoolGradeMapper;

    public GradeController(GradeService gradeService, GradeMapper gradeMapper, SchoolGradeMapper schoolGradeMapper) {
        this.gradeService = gradeService;
        this.gradeMapper = gradeMapper;
        this.schoolGradeMapper = schoolGradeMapper;
    }

    //查询全部
    @GetMapping("/select")
    public List<Grade> list(){
        List<Grade> gradeList = gradeService.list();
        System.out.println(gradeList);
        return gradeList;
    }


    //添加
    @PostMapping("/add")
    public Result add(@RequestBody Grade grade){
        grade.setId(gradeService.count()+1);
        System.out.println(grade.getName()+"=="+grade.getDetail()+"==="+grade.getMaster());
        boolean save = gradeService.save(grade);
        return Result.success(200,"添加成功",grade);

    }

    //修改教师
    @PostMapping("/update")
    public  Result update(@RequestBody Grade grade){

        Integer id = grade.getId();
        System.out.println(id);
        System.out.println("==================id");
        if(id!=null){
            gradeService.updateById(grade);
            return Result.success(200,"修改成功",grade);
        }else{
            return Result.fail(405,"修改失败",grade);
        }
    }




    //删除教师
    @PostMapping("/delete{id}")
    public Result del(@PathVariable Integer id){
        return Result.success(gradeService.removeById(id));


    }



    @PostMapping("/search")
    public Result selectByUsername(@RequestParam("name") String name) {
        QueryWrapper<Grade> gradeQueryWrapper = new QueryWrapper<>();
        name = "%"+name+"%";
        List<Grade> s =gradeMapper.fand(name);
        return Result.fail(200,"修改失败",s);
    }


     //查看年级详细信息
    @PostMapping ("/detail")
//       private SchoolGradeMapper schoolGradeMapper=null;
    public List<SchoolGrade> listGradeDetails(@RequestParam("id") Integer id){
        List<SchoolGrade> listGradeDetails = schoolGradeMapper.fanddetail(id);
            System.out.println(listGradeDetails);
            return listGradeDetails;
        }


    }



