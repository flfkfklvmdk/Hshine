package org.example.tails.exception;

import org.example.tails.pojo.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//全局异常捕捉类
@RestControllerAdvice
public class GlobalExceptionHander {
    //全局异常捕捉
    @ExceptionHandler(Exception.class)
    public Result ex(Exception e){
        e.printStackTrace();
        return Result.error("对不起，服务器异常，请稍后再试");
    }

    //处理部门操作异常
    @ExceptionHandler(DeptOperationException.class)
    public Result ex(DeptOperationException e){
        e.printStackTrace();
        return Result.error("对不起，当下部门有员工，不能直接删除");
    }
}
