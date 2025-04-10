package org.example.tails.service.impl;

import org.example.tails.exception.DeptOperationException;
import org.example.tails.mapper.DeptMapper;
import org.example.tails.mapper.EmpMapper;
import org.example.tails.pojo.dept.Dept;
import org.example.tails.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteById(Integer id) {
        //1. 先查询该部门下是否有员工
        int empCount = empMapper.countByDeptId(id);
        //2. 如果有员工, 则不允许删除
        if (empCount > 0) {
            throw new DeptOperationException("对不起，当前部门下有员工，不能直接删除！");
        }
        deptMapper.deleteById(id);
    }

    @Override
    public void add(Dept dept) {
        //1. 补全基础属性 - createTime, updateTime
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());

        //2. 调用Mapper接口方法插入数据
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        //1. 补全基础属性-updateTime
        dept.setUpdateTime(LocalDateTime.now());

        //2. 调用Mapper接口方法更新部门
        deptMapper.update(dept);
    }
}
