package org.example.tails.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tails.pojo.stu.StuQueryParam;
import org.example.tails.pojo.stu.Student;
import org.example.tails.pojo.utils.Result;
import org.example.tails.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StuController {
    @Autowired
    private StuService stuService;

    //学员分页查询
    @GetMapping
    public Result page(StuQueryParam stuQueryParam) {
        log.info("分页查询: {}", stuQueryParam);
        return Result.success(stuService.page(stuQueryParam));

    }

    //批量删除学员
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除学员信息: {}", ids);
        stuService.deleteByIds(ids);
        return Result.success();
    }

    //添加学员
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("保存学员信息: {}", student);
        stuService.save(student);
        return Result.success();
    }

    //根据id查询学员信息
    @GetMapping("/{id}")
    public Result getInfoById(@PathVariable Integer id) {
        log.info("查询学员信息: {}", id);
        Student student = stuService.getInfo(id);
        return Result.success(student);
    }

    //更新学生信息
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("更新学员信息: {}", student);
        stuService.update(student);
        return Result.success();
    }

    //违纪信息
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("违纪信息: {}", id);
        stuService.violation(id, score);
        return Result.success();
    }
}
