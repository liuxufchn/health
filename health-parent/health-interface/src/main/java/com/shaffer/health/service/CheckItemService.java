package com.shaffer.health.service;

import com.shaffer.health.pojo.CheckItem;

import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/12 21:28
 * @Author: Shaffer
 * @Description:
 */
public interface CheckItemService {
    /**
     * 查询所有检查项
     * @return 检查项
     */
    List<CheckItem> findAll();
}
