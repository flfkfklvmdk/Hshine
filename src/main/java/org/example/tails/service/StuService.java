package org.example.tails.service;

import com.github.pagehelper.Page;
import org.example.tails.pojo.stu.StuQueryParam;
import org.example.tails.pojo.stu.Student;
import org.example.tails.pojo.utils.PageResult;

import java.util.List;

public interface StuService {

    //学员分页查询
    PageResult<Student> page(StuQueryParam stuQueryParam);

    //批量删除学员
    void deleteByIds(List<Integer>ids);

    //添加学员
    void save(Student student);

    //查询回显
    Student getInfo(Integer id);

    //更新学员信息
    void update(Student student);

    //违纪信息
    void violation(Integer id, Integer score);
}
