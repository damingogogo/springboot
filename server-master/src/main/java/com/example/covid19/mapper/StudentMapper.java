package com.example.covid19.mapper;

import com.example.covid19.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.covid19.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-19
 */
public interface StudentMapper extends BaseMapper<Student> {
    @Select("select * from student where name like #{n} and age like #{a} and address like #{s} limit #{pn},#{ps}")
    List<Student> selectPage(@Param("n") String name,@Param("a") String age, @Param("s")  String address,@Param("pn")  Integer pageNum,@Param("ps")  Integer pageSize);

    @Select("select count(*) from student where name like #{n} and age like #{a} and address like #{s}")
    Integer selectTotal(@Param("n") String name,@Param("a") String age, @Param("s")  String address);
}
