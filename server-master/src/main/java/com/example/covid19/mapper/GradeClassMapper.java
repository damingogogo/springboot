package com.example.covid19.mapper;

import com.example.covid19.entity.GradeClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.covid19.entity.SchoolGrade;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * VIEW Mapper 接口
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-22
 */
public interface GradeClassMapper extends BaseMapper<GradeClass> {
    @Select("SELECT * FROM grade_class where id = #{i}")
    List<GradeClass> fanddetail(@Param("i") int id);
}
