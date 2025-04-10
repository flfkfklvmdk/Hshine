package org.example.tails.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.tails.pojo.emp.JobOption;
import org.example.tails.pojo.utils.Result;
import org.example.tails.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    //统计各个职位员工数量
    @GetMapping("/empJobData")
    public Result empJobData() {
        log.info("统计各个职位员工数量");
        JobOption jobOption = reportService.GetJobEmpJobData();
        return Result.success(jobOption);
    }

    //统计员工性别信息
    @GetMapping("/empGenderData")
    public Result empGenderData() {
        log.info("统计员工性别信息");
        List<Map> genderList = reportService.GetGenderEmpJobData();
        return Result.success(genderList);
    }

    //统计学院学历信息
    @GetMapping("/studentDegreeData")
    public Result studentDegreeData() {
        log.info("统计学院学历信息");
        List<Map> degreeList = reportService.GetDegreeStudentData();
        return Result.success(degreeList);
    }

    //统计班级人数
    @GetMapping("/studentCountData")
    public Result studentCountData() {
        log.info("统计班级人数");
        Map<String, Object> classCountList = reportService.GetClassCountData();
        return Result.success(classCountList);
    }
}
