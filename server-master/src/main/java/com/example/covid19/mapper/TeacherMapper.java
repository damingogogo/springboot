package com.example.covid19.mapper;

import com.example.covid19.entity.Student;
import com.example.covid19.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-18
 */
public interface TeacherMapper extends BaseMapper<Teacher> {
    @Select("SELECT id,name,job_number,sex,age,phone,marry,vaccine,address,hobby FROM teacher where name like #{n} and sex like #{s} and phone like #{p}")
    List<Teacher> fand(@Param("n") String name, @Param("s") String sex, @Param("p")  String phone);
}
