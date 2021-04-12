package com.shaffer.health.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/12 20:49
 * @Author: Shaffer
 * @Description: 分页结果封装对象
 */
@Getter
@Setter
public class PageResult<T> implements Serializable {
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 当前页结果
     */
    private List<T> rows;

    /**
     * 分页结果
     *
     * @param total 总记录数
     * @param rows  当前页结果
     */
    public PageResult(Long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }

}
