package com.shaffer.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shaffer.health.constant.MessageConstant;
import com.shaffer.health.dao.CheckGroupDao;
import com.shaffer.health.entity.PageResult;
import com.shaffer.health.entity.QueryPageBean;
import com.shaffer.health.exception.GlobalException;
import com.shaffer.health.pojo.CheckGroup;
import com.shaffer.health.service.CheckGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/13 21:45
 * @Author: Shaffer
 * @Description:
 */
@Service(interfaceClass = CheckGroupService.class)
public class CheckGroupServiceImpl implements CheckGroupService {

    @Autowired
    private CheckGroupDao checkGroupDao;

    @Override
    public PageResult<CheckGroup> findPage(QueryPageBean queryPageBean) {
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        // 拼接条件
        String queryString = queryPageBean.getQueryString();
        if (!StringUtils.isEmpty(queryString)) {
            queryString = "%" + queryString + "%";
        }

        // 查询
        Page<CheckGroup> pageResult = checkGroupDao.findByCondition(queryString);

        // 封装结果
        return new PageResult<>(pageResult.getTotal(), pageResult.getResult());
    }

    @Override
    @Transactional(rollbackFor = {})
    public void add(CheckGroup checkGroup, Integer[] checkItemIds) {
        // 添加检查组
        checkGroupDao.add(checkGroup);
        // 获取 id
        Integer id = checkGroup.getId();
        // 关联检查项
        if (null != checkItemIds && checkItemIds.length > 0) {
            for (Integer checkItemId : checkItemIds) {
                checkGroupDao.addCheckGroupCheckItem(id, checkItemId);
            }
        }
    }

    @Override
    public CheckGroup findById(Integer id) {
        return checkGroupDao.findById(id);
    }

    @Override
    public List<Integer> findCheckItemIdsByCheckGroupId(Integer id) {
        return checkGroupDao.findCheckItemIdsByCheckGroupId(id);
    }

    @Override
    @Transactional(rollbackFor = {})
    public void update(CheckGroup checkGroup, Integer[] checkItemIds) {
        // 更新检查组信息
        checkGroupDao.update(checkGroup);
        // 清除检查组相关联的检查项
        checkGroupDao.deleteAssociation(checkGroup.getId());
        // 关联检查项
        if (null != checkItemIds && checkItemIds.length > 0) {
            for (Integer checkItemId : checkItemIds) {
                checkGroupDao.addCheckGroupCheckItem(checkGroup.getId(), checkItemId);
            }
        }
    }

    @Override
    @Transactional(rollbackFor = {})
    public void deleteById(Integer id) {
        // 检查 这个检查组是否被套餐使用了
        int count = checkGroupDao.findSetMealCountByCheckGroupId(id);
        if(count > 0){
            // 被使用了
            throw new GlobalException(MessageConstant.CHECKGROUP_IN_USE);
        }
        // 清除关联关系
        checkGroupDao.deleteAssociation(id);
        // 删除检查组
        checkGroupDao.deleteById(id);
    }
}
