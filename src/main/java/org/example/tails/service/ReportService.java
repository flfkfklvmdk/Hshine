package org.example.tails.service;

import org.example.tails.pojo.emp.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    //统计各个职位员工数量
    JobOption GetJobEmpJobData();

    //统计员工性别信息
    List<Map> GetGenderEmpJobData();

    //统计学员学历信息
    List<Map> GetDegreeStudentData();

    //统计班级人数
    Map<String, Object> GetClassCountData();
}
