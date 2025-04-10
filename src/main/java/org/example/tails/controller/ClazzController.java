package org.example.tails.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tails.pojo.stu.Clazz;
import org.example.tails.pojo.stu.ClazzQueryParam;
import org.example.tails.pojo.utils.PageResult;
import org.example.tails.pojo.utils.Result;
import org.example.tails.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    //获取全部班级信息
    @GetMapping
    public Result getClazzs(ClazzQueryParam clazzQueryParam) {
        log.info("查询班级信息: {}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    //删除班级信息
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        log.info("删除班级信息: {}", id);
        clazzService.deleteById(id);
        return Result.success();
    }

    //添加班级
    @PostMapping
    public Result addClass(@RequestBody Clazz clazz) {
        log.info("添加班级信息: {}", clazz);
        clazzService.insert(clazz);
        return Result.success();
    }

    //根据ID查询
    @GetMapping("/{id}")
    public Result getClazzById(@PathVariable Integer id){
        log.info("根据id查询班级信息: {}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    //更新班级
    @PutMapping
    public Result updateClazz(@RequestBody Clazz clazz){
        log.info("修改班级信息: {}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    //查询所有班级
    @GetMapping("/list")
    public Result GetAllClazzs(){
        log.info("查询所有班级信息");
        List<Clazz> clazzList = clazzService.getAllClazzs();
        return Result.success(clazzList);
    }
}
