package com.example.covid19.service.impl;

import com.example.covid19.entity.Student;
import com.example.covid19.mapper.StudentMapper;
import com.example.covid19.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-19
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
