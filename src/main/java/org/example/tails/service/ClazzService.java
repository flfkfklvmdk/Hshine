package org.example.tails.service;

import org.example.tails.pojo.stu.Clazz;
import org.example.tails.pojo.stu.ClazzPostParam;
import org.example.tails.pojo.stu.ClazzQueryParam;
import org.example.tails.pojo.utils.PageResult;

import java.util.List;


public interface ClazzService {
    // 分页查询班级信息
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    //根据id删除班级信息
    void deleteById(Integer id);

    //添加班级
    void insert(Clazz clazz);

    //根据id查询班级信息
    Clazz getById(Integer id);

    //修改班级信息
    void save(Clazz clazz);

    //查询所有班级
    List<Clazz> getAllClazzs();
}
