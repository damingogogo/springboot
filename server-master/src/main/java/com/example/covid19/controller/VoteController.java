package com.example.covid19.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.covid19.common.lang.Result;
import com.example.covid19.entity.Teacher;
import com.example.covid19.entity.Vote;
import com.example.covid19.mapper.VoteMapper;
import com.example.covid19.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-22
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/vote")
public class VoteController {
    @Autowired
    private final VoteService voteService;
    private final VoteMapper voteMapper;

    public VoteController(VoteService voteService, VoteMapper voteMapper) {
        this.voteService = voteService;
        this.voteMapper = voteMapper;
    }


    //查询全部教师
    @GetMapping("/select")
    public List<Vote> list(){
        List<Vote> voteList = voteService.list();
        System.out.println(voteList);
        return voteList;
    }


    //添加教师
    @PostMapping("/add")
    public Result add(@RequestBody Vote vote){
        vote.setId(voteService.count()+1);
        System.out.println(vote.getCreateBy()+"=="+vote.getAnnoymous()+"==="+vote.getTitle());
        boolean save = voteService.save(vote);
        return Result.success(200,"添加成功",vote);

    }

    //修改教师
    @PostMapping("/update")
    public  Result update(@RequestBody Vote vote){

        Integer id = vote.getId();
        System.out.println(id);
        System.out.println("==================id");
        if(id!=null){
            voteService.updateById(vote);
            return Result.success(200,"修改成功",vote);
        }else{
            return Result.fail(405,"修改失败",vote);
        }
    }




    //删除教师
    @PostMapping("/delete{id}")
    public Result del(@PathVariable Integer id){
        return Result.success(voteService.removeById(id));


    }

    @PostMapping("/search")
    public Result selectByCondition(@RequestParam("title") String title) {
        QueryWrapper<Vote> voteQueryWrapper = new QueryWrapper<>();
        title = "%"+title+"%";

        List<Vote> t = voteMapper.fand(title);
        System.out.println("=================");
        return Result.fail(200,"修改失败",t);


    }


}
