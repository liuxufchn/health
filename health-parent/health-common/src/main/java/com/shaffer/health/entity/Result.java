package com.shaffer.health.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: health
 * @Date: 2021/4/12 20:46
 * @Author: Shaffer
 * @Description: 封装返回结果
 */
@Getter
@Setter
public class Result implements Serializable {
    /**
     * 执行结果，true为执行成功 false为执行失败
     */
    private boolean flag;
    /**
     * 返回结果信息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    /**
     * 保存、修改保存
     *
     * @param flag    执行结果
     * @param message 返回结果信息
     */
    public Result(boolean flag, String message) {
        super();
        this.flag = flag;
        this.message = message;
    }

    /**
     * 查询回显
     *
     * @param flag 执行结果
     * @param message 返回结果信息
     * @param data 返回数据
     */
    public Result(boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }
}
