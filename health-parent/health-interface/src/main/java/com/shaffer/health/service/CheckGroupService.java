package com.shaffer.health.service;

import com.shaffer.health.entity.PageResult;
import com.shaffer.health.entity.QueryPageBean;
import com.shaffer.health.pojo.CheckGroup;

import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/13 21:43
 * @Author: Shaffer
 * @Description:
 */
public interface CheckGroupService {
    /**
     * 分页查询
     *
     * @param queryPageBean 分页条件
     * @return 分页数据
     */
    PageResult<CheckGroup> findPage(QueryPageBean queryPageBean);

    /**
     * 添加检查组
     *
     * @param checkGroup   检查组信息
     * @param checkItemIds 相关联的检查项id
     */
    void add(CheckGroup checkGroup, Integer[] checkItemIds);

    /**
     * 根据 id 查询
     *
     * @param id 检查组 id
     * @return 检查组信息
     */
    CheckGroup findById(Integer id);

    /**
     * 通过检查组id查询相关联的检查项id
     *
     * @param id 检查组id
     * @return 相关联的检查项id列表
     */
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    /**
     * 更新
     *
     * @param checkGroup   检查组信息
     * @param checkItemIds 与检查组相关联的检查项信息
     */
    void update(CheckGroup checkGroup, Integer[] checkItemIds);

    /**
     * 删除
     *
     * @param id 检查组id
     */
    void deleteById(Integer id);
}
