package com.example.covid19.mapper;

import com.example.covid19.entity.Student;
import com.example.covid19.entity.Vote;
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
 * @since 2022-11-22
 */
public interface VoteMapper extends BaseMapper<Vote> {
    @Select("SELECT * FROM vote where title like #{t} ")
    List<Vote> fand(@Param("t") String title);
}
