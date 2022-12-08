package com.example.covid19.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.covid19.common.lang.Result;
import com.example.covid19.entity.Notice;
import com.example.covid19.mapper.NoticeMapper;
import com.example.covid19.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author DengZhouMing
 * @since 2022-11-19
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private final NoticeService noticeService;
    private final NoticeMapper noticeMapper;

    public NoticeController(NoticeService noticeService, NoticeMapper noticeMapper) {
        this.noticeService = noticeService;
        this.noticeMapper = noticeMapper;
    }

    //查询全部
    @GetMapping("/select")
    public List<Notice> list(){
        List<Notice> noticeList = noticeService.list();
        System.out.println(noticeList);
        return noticeList;
    }


    //添加教师
    @PostMapping("/add")
    public Result add(@RequestBody Notice notice){
        notice.setId(noticeService.count()+1);
        System.out.println(notice.getTitle()+"=="+notice.getDetail()+"==="+notice.getTime());
        boolean save = noticeService.save(notice);
        return Result.success(200,"添加成功",notice);

    }

    //修改教师
    @PostMapping("/update")
    public  Result update(@RequestBody Notice notice){

        Integer id = notice.getId();
        System.out.println(id);
        System.out.println("==================id");
        if(id!=null){
            noticeService.updateById(notice);
            return Result.success(200,"修改成功",notice);
        }else{
            return Result.fail(405,"修改失败",notice);
        }
    }




    //删除
    @PostMapping("/delete{id}")
    public Result del(@PathVariable Integer id){
        return Result.success(noticeService.removeById(id));


    }



    //  条件模糊查询
    @PostMapping("/search")
    public Result selectByUsername(@RequestParam("title") String title,
                                   @RequestParam("detail") String detail) {
        QueryWrapper<Notice> noticeQueryWrapper = new QueryWrapper<>();
        title = "%"+title+"%";
        detail = "%"+detail+"%";
        List<Notice> s =noticeMapper.fand(title,detail);
        return Result.fail(200,"修改失败",s);
    }



}
