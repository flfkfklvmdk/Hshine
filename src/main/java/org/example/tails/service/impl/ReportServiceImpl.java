package org.example.tails.service.impl;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.example.tails.mapper.EmpMapper;
import org.example.tails.mapper.StuMapper;
import org.example.tails.pojo.emp.JobOption;
import org.example.tails.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@Slf4j
@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private StuMapper stuMapper;

    //日志
    private static final Log log = LogFactory.getLog(ReportServiceImpl.class);


    //统计各个职位员工数量
    @Override
    public JobOption GetJobEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList, dataList);
    }

    //统计员工性别信息
    @Override
    public List<Map> GetGenderEmpJobData() {
        return  empMapper.countEmpGenderData();
    }

    //统计学员学历信息
    @Override
    public List<Map> GetDegreeStudentData() {
        return stuMapper.countStudentDegreeData();
    }

    @Override
    public Map<String, Object> GetClassCountData() {
        List<Map> resultList = stuMapper.countClassData();

        List<String> clazzList = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();

        for (Map item : resultList) {
            clazzList.add((String) item.get("name"));
            dataList.add(((Number) item.get("value")).intValue());
        }

        Map<String, Object> result = new HashMap<>();
        result.put("clazzList", clazzList);
        result.put("dataList", dataList);

        return result;
    }
}