package com.shaffer.health.service;

import com.shaffer.health.entity.PageResult;
import com.shaffer.health.entity.QueryPageBean;
import com.shaffer.health.exception.GlobalException;
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
     *
     * @return 检查项
     */
    List<CheckItem> findAll();

    /**
     * 分页查询
     *
     * @return 分页结果
     */
    PageResult<CheckItem> findPage(QueryPageBean queryPageBean);

    /**
     * 新增检查项
     * @param checkItem 新增
     */
    void add(CheckItem checkItem);

    /**
     * 通过 ID 查询
     * @param id 检查组 id
     * @return 检查组信息
     */
    CheckItem findById(Integer id);

    /**
     * 更新
     * @param checkItem 检查项
     */
    void update(CheckItem checkItem);

    /**
     * 删除
     * @param id 检查项 id
     */
    void delete(int id) throws GlobalException;
}
