package com.shaffer.health.dao;

import com.github.pagehelper.Page;
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


    /**
     * 根据条件查询
     * @param queryString 查询条件
     * @return 返回分页数据
     */
    Page<CheckItem> findByCondition(String queryString);

    /**
     * 新增
     * @param checkItem 新增检查项
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
    void delete(int id);

    /**
     * 检查检查项是否被检查组使用
     * @param id 检查项id
     * @return 若返回值大于0，则该检查项被检查组使用
     */
    int findCountByCheckItemId(int id);
}
