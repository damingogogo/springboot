package com.example.covid19.mapper;

import com.example.covid19.entity.SysUser;
import com.example.covid19.entity.User;
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
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT id,username,password,phone,create_time FROM user where username like #{u} and phone like #{p}")
    List<User> fand(@Param("u") String username,@Param("p") String phone);
}
