package org.example.tails.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.tails.mapper.StuMapper;
import org.example.tails.pojo.stu.StuQueryParam;
import org.example.tails.pojo.stu.Student;
import org.example.tails.pojo.utils.PageResult;
import org.example.tails.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StuServiceIml implements StuService {

    @Autowired
    private StuMapper stuMapper;

    @Override
    public PageResult<Student> page(StuQueryParam stuQueryParam) {
        //设置分页参数
        PageHelper.startPage(stuQueryParam.getPage(), stuQueryParam.getPageSize());

        //查询
        List<Student> students = stuMapper.list(stuQueryParam);

        //解析并包装
        Page<Student> p=(Page<Student>) students;
        return new PageResult<Student>(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        //批量删除
        stuMapper.deleteByIds(ids);
    }

    @Override
    public void save(Student student) {
        //添加学员
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.save(student);
    }

    @Override
    public Student getInfo(Integer id) {
        return stuMapper.getInfo(id);
    }

    @Override
    public void update(Student student) {
        //更新学员信息
        student.setUpdateTime(LocalDateTime.now());
        stuMapper.update(student);
    }

    @Override
    public void violation(Integer id, Integer score) {
        //违纪信息
        Student student = stuMapper.getInfo(id);
        if (student != null) {
            student.setViolationScore(Short.valueOf(score.toString()));
            short violationCount = student.getViolationCount();
            violationCount++;
            student.setViolationCount(violationCount);
            student.setUpdateTime(LocalDateTime.now());
            stuMapper.update(student);
        }
    }
}
