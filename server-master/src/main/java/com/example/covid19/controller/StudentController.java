package com.example.covid19.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.covid19.common.lang.Result;
import com.example.covid19.entity.Student;
import com.example.covid19.mapper.StudentMapper;
import com.example.covid19.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;
    private StudentMapper studentMapper;
    public StudentController(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }
    //查询全部
    @GetMapping("/select")
    public List<Student> list(){
        List<Student> students = studentService.list();
        System.out.println(students);
        return  students;
    }

    //添加用户
    @PostMapping("/add")
    public Student save(@RequestBody Student student){
        student.setId(studentService.count()+1);
        System.out.println(student.getAddress()+"=="+student.getPhone()+"==="+student.getAge());
        boolean save = studentService.save(student);
        return null;
    }


    //修改用户
    @PostMapping("/update")
    public Result update(@RequestBody Student student){
        Integer id = student.getId();
        System.out.println(id);
        if(id !=null){
            studentService.updateById(student);
            System.out.println(student);
            System.out.println("============================");
            return Result.success(200,"修改成功",student);
        }else{
            return Result.fail(405,"修改失败",student);
        }
    }
    //删除用户
    @PostMapping("/delete{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(studentService.removeById(id));
    }

    //批量删除
    @PostMapping("/delete/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids){
        System.out.println(ids);
        return Result.success(studentService.removeByIds(ids));

    }




    //分页查询
    @PostMapping("/page")
    public Map<String,Object> selectByPage(@RequestParam Integer pageNum,
                                   @RequestParam Integer pageSize,
                                   @RequestParam(defaultValue = "") String name,
                                   @RequestParam(defaultValue = "") String age,
                                   @RequestParam(defaultValue = "") String address) {
        System.out.println(pageSize+"========="+pageNum+"============"+name+"======="+age+"==========="+address);
        pageNum=(pageNum-1)*pageSize;
        name = "%"+name+"%";
        age = "%"+age+"%";
        address = "%"+address+"%";
        List<Student> data =studentMapper.selectPage(name,age,address,pageNum,pageSize);
        Student ts=new Student();

        Integer total=studentMapper.selectTotal(name,age,address);

        Map<String,Object> res=new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;


//        List<Student> s =studentMapper.fand(name,age,address,pageSize);
//        return Result.fail(200,"查询成功",s);
    }






}

