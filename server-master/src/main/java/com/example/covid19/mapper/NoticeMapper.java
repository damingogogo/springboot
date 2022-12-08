package com.example.covid19.mapper;

import com.example.covid19.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.covid19.entity.School;
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
public interface NoticeMapper extends BaseMapper<Notice> {
    @Select("SELECT id,title,time,detail FROM notice where title like #{t} and detail like #{d}")
    List<Notice> fand(@Param("t")  String title, @Param("d") String detail);
}
