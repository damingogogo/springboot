package com.example.covid19.mapper;

import com.example.covid19.entity.Class;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.covid19.entity.Grade;
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
public interface ClassMapper extends BaseMapper<Class> {
    @Select("SELECT id,name,master,number,detail FROM class where name like #{n}")
    List<Class> fand(@Param("n") String name);
}
