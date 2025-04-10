package org.example.tails.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.example.tails.mapper.EmpExprMapper;
import org.example.tails.mapper.EmpMapper;
import org.example.tails.pojo.emp.Emp;
import org.example.tails.pojo.emp.EmpExpr;
import org.example.tails.pojo.emp.EmpLog;
import org.example.tails.pojo.emp.EmpQueryParam;
import org.example.tails.pojo.login.LoginInfo;
import org.example.tails.pojo.utils.PageResult;
import org.example.tails.service.EmpLogService;
import org.example.tails.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * 员工管理
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;//注入事务层



    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        //1. 设置分页参数(PageHelper)
        PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());

        //2. 执行查询
        List<Emp> empList = empMapper.list(empQueryParam);

        //3. 解析查询结果, 并封装
        Page<Emp> p = (Page<Emp>) empList;
        return new PageResult<Emp>(p.getTotal(), p.getResult());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(Emp emp) {
        try {
            //基础属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            //2.保存员工基本信息
            empMapper.insert(emp);

            //3. 保存员工的工作经历信息 - 批量
            Integer empId = emp.getId();
            List<EmpExpr> exprList = emp.getExprLists();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr -> empExpr.setEmpId(empId));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
           //记录日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),emp.toString());
            empLogService.insertLog(empLog);
        }
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        //根据id批量删除员工信息
        empMapper.deleteByIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getInfo(id);
    }

    @Override
    public void update(Emp emp) {
        //根据id跟新员工信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //根据id删除老的员工信息
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //新增新的员工工作数据
        Integer empId=emp.getId();
        List<EmpExpr> exprList = emp.getExprLists();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        Emp empLogin = empMapper.getUsernameAndPassword(emp);
        if(empLogin != null){
            LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), null);
            return loginInfo;
        }
        return null;
    }


}
