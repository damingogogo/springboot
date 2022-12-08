package com.example.covid19.mapper;

import com.example.covid19.entity.Parent;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.covid19.entity.Student;
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
public interface ParentMapper extends BaseMapper<Parent> {
    @Select("SELECT id,name,sex,idnumber,age,height,weight,nation,politic,address,phone,hobby,create_time FROM parent where name like #{n} and age like #{a} and phone like #{p}")
    List<Parent> fand(@Param("n") String name, @Param("a")  String age, @Param("p") String phone);
}
