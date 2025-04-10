package org.example.tails.service;

import org.example.tails.pojo.emp.EmpLog;

public interface EmpLogService {
    //记录新增员工日志
    void insertLog(EmpLog empLog);
}