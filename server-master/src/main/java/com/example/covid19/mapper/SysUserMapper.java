package com.example.covid19.mapper;

import com.example.covid19.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.covid19.entity.Teacher;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-15
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    @Select("SELECT id,username,type,email,phone,address,create_time FROM sys_user where username like #{u} and email like #{e} and address like #{a}")
    List<SysUser> fand(@Param("u") String username, @Param("e") String email, @Param("a")  String address);
}
