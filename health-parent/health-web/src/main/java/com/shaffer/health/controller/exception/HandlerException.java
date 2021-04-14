package com.shaffer.health.controller.exception;

import com.shaffer.health.constant.MessageConstant;
import com.shaffer.health.entity.Result;
import com.shaffer.health.exception.GlobalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @program: health
 * @Date: 2021/4/13 21:14
 * @Author: Shaffer
 * @Description:
 */
@RestControllerAdvice
public class HandlerException {

    private static final Logger log = LoggerFactory.getLogger(HandlerException.class);

    /**
     * 自定义全局异常处理
     * @param ge 全局异常
     * @return 封装提示信息
     */
    @ExceptionHandler(GlobalException.class)
    public Result handlerGlobalException(GlobalException ge) {
        return new Result(false, ge.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error(e.getMessage());
        return new Result(false, MessageConstant.UNKNOWN_ERROR);
    }
}
