package com.shaffer.health.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: health
 * @Date: 2021/4/12 20:51
 * @Author: Shaffer
 * @Description:
 */
@Data
public class QueryPageBean implements Serializable {
    /**
     * 页码
     */
    private Integer currentPage;
    /**
     * 每页记录数
     */
    private Integer pageSize;
    /**
     * 查询条件
     */
    private String queryString;
}
