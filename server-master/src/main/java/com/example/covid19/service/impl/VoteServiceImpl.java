package com.example.covid19.service.impl;

import com.example.covid19.entity.Vote;
import com.example.covid19.mapper.VoteMapper;
import com.example.covid19.service.VoteService;
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
public class VoteServiceImpl extends ServiceImpl<VoteMapper, Vote> implements VoteService {

}
