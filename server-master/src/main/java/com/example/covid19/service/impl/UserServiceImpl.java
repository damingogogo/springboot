package com.example.covid19.service.impl;

import com.example.covid19.entity.User;
import com.example.covid19.mapper.UserMapper;
import com.example.covid19.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
