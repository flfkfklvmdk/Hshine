package org.example.tails.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.tails.mapper.ClazzMapper;
import org.example.tails.pojo.stu.Clazz;
import org.example.tails.pojo.stu.ClazzQueryParam;
import org.example.tails.pojo.utils.PageResult;
import org.example.tails.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceIml implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        //设置分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());

        //执行查询
        List<Clazz> clazzList= clazzMapper.list(clazzQueryParam);
        //判断是否结课逻辑
        LocalDate now = LocalDate.now();
        for (Clazz clazz : clazzList) {
            if (clazz.getEndDate() != null && now.isAfter(clazz.getEndDate())) {
                clazz.setStatus("已结课");
            } else if (clazz.getBeginDate() != null && now.isAfter(clazz.getBeginDate())) {
                clazz.setStatus("在读");
            } else {
                clazz.setStatus("未开班");
            }
        }

        //解析结果并封装
        Page<Clazz> p=(Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(), p.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void insert( Clazz clazz) {
       //基础属性
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        //保存班级信息
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void save(Clazz clazz) {
        //根据id跟新学生信息
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
    }

    @Override
    public List<Clazz> getAllClazzs() {
        return clazzMapper.getAllClazzs();
    }
}
