package com.shaffer.health.dao;

import com.github.pagehelper.Page;
import com.shaffer.health.pojo.CheckGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/13 21:34
 * @Author: Shaffer
 * @Description:
 */
public interface CheckGroupDao {

    /**
     * 根据条件查询
     *
     * @param queryString 查询条件
     * @return 分页数据
     */
    Page<CheckGroup> findByCondition(String queryString);

    /**
     * 添加检查组
     *
     * @param checkGroup 检查组
     */
    void add(CheckGroup checkGroup);

    /**
     * 添加检查组和检查项关系
     *
     * @param checkGroupId 检查组id
     * @param checkItemId  检查项id
     */
    void addCheckGroupCheckItem(@Param("checkGroupId") Integer checkGroupId, @Param("checkItemId") Integer checkItemId);

    CheckGroup findById(Integer id);

    /**
     * 通过检查组id查询相关联的检查项id
     *
     * @param id 检查组id
     * @return 相关联的检查项id列表
     */
    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    /**
     * 更新检查组
     *
     * @param checkGroup 检查组信息
     */
    void update(CheckGroup checkGroup);

    /**
     * 删除中间表中与该检查组相关联的检查项id
     *
     * @param id 检查组id
     */
    void deleteAssociation(Integer id);

    /**
     * 通过检查组id查询是否被套餐使用了
     * @param id 检查组id
     * @return 结果大于0则该检查组正在被使用
     */
    int findSetMealCountByCheckGroupId(Integer id);

    /**
     * 删除
     * @param id 检查组id
     */
    void deleteById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<CheckGroup> findAll();
}
