package com.example.covid19.mapper;

import com.example.covid19.entity.Grade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.covid19.entity.SchoolGrade;
import com.example.covid19.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-21
 */
public interface GradeMapper extends BaseMapper<Grade> {
    @Select("SELECT id,name,master,number,detail FROM grade where name like #{n}")
    List<Grade> fand(@Param("n") String name);






}
