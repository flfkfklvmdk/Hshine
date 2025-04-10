package org.example.tails.service;

import org.example.tails.pojo.emp.Emp;
import org.example.tails.pojo.emp.EmpQueryParam;
import org.example.tails.pojo.login.LoginInfo;
import org.example.tails.pojo.utils.PageResult;

import java.util.List;

public interface EmpService {

    /**
     * 分页查询
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);


    /**
     * 添加员工
     */
    void save(Emp emp);

    //批量删除
    void deleteByIds(List<Integer> ids);

    //查询回显
    Emp getInfo(Integer id);

    //更新员工信息
    void update(Emp emp);

    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页记录数
     */
    //PageResult<Emp> page(Integer page, Integer pageSize, String name, Integer gender, LocalDate begin, LocalDate end);

    //登录

    LoginInfo login(Emp emp);
}
