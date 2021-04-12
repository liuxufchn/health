package com.shaffer.health.dao;

import com.shaffer.health.pojo.CheckItem;

import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/12 21:32
 * @Author: Shaffer
 * @Description:
 */
public interface CheckItemDao {
    /**
     * 查询所有
     * @return 检查项
     */
    List<CheckItem> findAll();
}
