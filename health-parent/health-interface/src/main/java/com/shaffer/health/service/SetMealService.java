package com.shaffer.health.service;

import com.shaffer.health.entity.PageResult;
import com.shaffer.health.entity.QueryPageBean;
import com.shaffer.health.pojo.SetMeal;

import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/15 21:33
 * @Author: Shaffer
 * @Description:
 */
public interface SetMealService {

    /**
     * 新增
     * @param setMeal 套餐
     * @param checkgroupIds 相关联的检查组id
     */
    void add(SetMeal setMeal, Integer[] checkgroupIds);

    /**
     * 分页查询
     * @param queryPageBean 查询条件
     * @return 分页数据
     */
    PageResult<SetMeal> findPage(QueryPageBean queryPageBean);

    /**
     * 根据id查询套餐数据
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
     * 更新
     * @param setMeal 套餐
     * @param checkgroupIds 相关联的检查组
     */
    void update(SetMeal setMeal, Integer[] checkgroupIds);

    /**
     * 删除套餐
     * @param id 套餐id
     */
    void deleteById(Integer id);

    /**
     * 查出数据库中所有图片
     * @return 图片名列表
     */
    List<String> findImgs();

    /**
     * 查询所有
     * @return 所有套餐数据
     */
    List<SetMeal> findAll();

    /**
     * 查询套餐详情
     * @param id 套餐id
     * @return 套餐详情
     */
    SetMeal findDetailById(int id);
}
