package org.example.tails.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tails.pojo.emp.Emp;
import org.example.tails.pojo.emp.EmpQueryParam;
import org.example.tails.pojo.utils.PageResult;
import org.example.tails.pojo.utils.Result;
import org.example.tails.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

    /**
     * 分页查询
     */
    /*@GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name, Integer gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end){
        log.info("分页查询: {},{},{},{},{},{}", page, pageSize,name, gender, begin, end);
        PageResult<Emp> pageResult = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageResult);
    }*/


    /**
     * 分页查询
     */
    @GetMapping
    public Result page(EmpQueryParam empQueryParam){
        log.info("分页查询: {}", empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("保存员工信息: {}", emp);
        empService.save(emp);
        return Result.success();
    }

    //删除员工信息
    @DeleteMapping
    public Result delete(@RequestParam List<Integer>ids){
        log.info("删除员工信息: {}", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    //查询回显
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询员工数据: {}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);

    }

    //更新员工信息
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("更新员工信息: {}", emp);
        empService.update(emp);
        return Result.success();
    }
}