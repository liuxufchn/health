package com.shaffer.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shaffer.health.constant.MessageConstant;
import com.shaffer.health.dao.SetMealDao;
import com.shaffer.health.entity.PageResult;
import com.shaffer.health.entity.QueryPageBean;
import com.shaffer.health.exception.GlobalException;
import com.shaffer.health.pojo.SetMeal;
import com.shaffer.health.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/15 21:36
 * @Author: Shaffer
 * @Description:
 */
@Service(interfaceClass = SetMealService.class)
public class SetMealServiceImpl implements SetMealService {
    @Autowired
    private SetMealDao setMealDao;

    @Override
    @Transactional(rollbackFor = {})
    public void add(SetMeal setMeal, Integer[] checkgroupIds) {
        // 添加套餐
        setMealDao.add(setMeal);
        // 建立关联关系
        Integer id = setMeal.getId();
        setAssociation(id, checkgroupIds);
    }

    @Override
    public PageResult<SetMeal> findPage(QueryPageBean queryPageBean) {
        // 设置分页条件
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        // 拼接查询条件
        String queryString = queryPageBean.getQueryString();
        if (!StringUtils.isEmpty(queryString)) {
            queryString = "%" + queryString + "%";
        }

        // 查询
        Page<SetMeal> page = setMealDao.findByCondition(queryString);

        // 封装分页数据
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    public SetMeal findById(Integer id) {
        return setMealDao.findById(id);
    }

    @Override
    public List<Integer> findCheckgroupIdsBySetmealId(Integer id) {
        return setMealDao.findCheckgroupIdsBySetmealId(id);
    }

    @Override
    @Transactional(rollbackFor = {})
    public void update(SetMeal setMeal, Integer[] checkgroupIds) {
        // 修改套餐信息
        setMealDao.update(setMeal);
        // 删除相关联的数据
        Integer id = setMeal.getId();
        setMealDao.deleteAssociation(id);
        // 设置关联关系
        setAssociation(id, checkgroupIds);
    }

    @Override
    @Transactional(rollbackFor = {})
    public void deleteById(Integer id) {
        // 先检查套餐是否被占用
        int count = setMealDao.findOrderCountBySetMealId(id);
        // 被占用则抛出异常，不再删除
        if (count > 0) {
            throw new GlobalException(MessageConstant.DELETE_SETMEAL_FAIL);
        }

        // 删除关联的检查组
        setMealDao.deleteAssociation(id);

        // 删除套餐
        setMealDao.deleteById(id);
    }

    private void setAssociation(Integer setMealId, Integer[] checkGroupIds) {
        if (checkGroupIds != null && checkGroupIds.length > 0) {
            for (Integer checkgroupId : checkGroupIds) {
                setMealDao.setAssociation(setMealId, checkgroupId);
            }
        }
    }
}
