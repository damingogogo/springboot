package com.example.covid19.mapper;

import com.example.covid19.entity.School;
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
public interface SchoolMapper extends BaseMapper<School> {
    @Select("SELECT id,name,master,number_student,address,number_grade,number_class,detail FROM school where name like #{n} and address like #{a}")
    List<School> fand(@Param("n")  String name, @Param("a") String address);
}
