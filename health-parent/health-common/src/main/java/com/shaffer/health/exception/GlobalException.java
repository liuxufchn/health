package com.shaffer.health.exception;

/**
 * @program: health
 * @Date: 2021/4/13 20:59
 * @Author: Shaffer
 * @Description: 自定义全局异常
 */
public class GlobalException extends RuntimeException {

    public GlobalException(String message) {
        super(message);
    }

}
