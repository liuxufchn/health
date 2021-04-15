package com.shaffer.health.dao;

import com.github.pagehelper.Page;
import com.shaffer.health.pojo.SetMeal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/15 21:36
 * @Author: Shaffer
 * @Description:
 */
public interface SetMealDao {
    /**
     * 新增套餐
     * @param setMeal 套餐信息
     */
    void add(SetMeal setMeal);

    /**
     * 建立套餐和检查组的关系
     * @param id 套餐id
     * @param checkgroupId 检查组id
     */
    void setAssociation(@Param("id") Integer id, @Param("checkgroupId") Integer checkgroupId);

    /**
     * 条件查询
     * @param queryString 查询条件
     * @return 分页数据
     */
    Page<SetMeal> findByCondition(String queryString);

    /**
     * 根据id查询
     * @param id 套餐id
     * @return 套餐信息
     */
    SetMeal findById(Integer id);

    /**
     * 查询关联关系
     * @param id 套餐id
     * @return 套餐包含的检查组id
     */
    List<Integer> findCheckgroupIdsBySetmealId(Integer id);

    /**
     * 更新套餐数据
     * @param setMeal 套餐信息
     */
    void update(SetMeal setMeal);

    /**
     * 删除套餐和检查组关系
     * @param id 套餐id
     */
    void deleteAssociation(Integer id);

    /**
     * 查询套餐是否被占用
     * @param id 套餐id
     * @return 返回值大于0则表示该套餐正在被占用
     */
    int findOrderCountBySetMealId(Integer id);

    /**
     * 根据id删除
     * @param id 套餐id
     */
    void deleteById(Integer id);
}
