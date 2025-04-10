package org.example.tails.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.tails.pojo.emp.EmpExpr;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    /**
     * 批量插入员工工作经历信息
     */
    public void insertBatch(List<EmpExpr> exprList);

    //批量删除员工工作经历信息
    void deleteByEmpIds(List<Integer> empIds);
}