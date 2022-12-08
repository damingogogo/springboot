package com.example.covid19.mapper;

import com.example.covid19.entity.SchoolGrade;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-21
 */
public interface SchoolGradeMapper extends BaseMapper<SchoolGrade> {
    @Select("SELECT * FROM school_grade where id = #{i}")
    List<SchoolGrade> fanddetail(@Param("i") int id);
}
