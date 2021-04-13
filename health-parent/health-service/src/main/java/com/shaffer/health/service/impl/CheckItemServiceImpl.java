package com.shaffer.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.shaffer.health.constant.MessageConstant;
import com.shaffer.health.dao.CheckItemDao;
import com.shaffer.health.entity.PageResult;
import com.shaffer.health.entity.QueryPageBean;
import com.shaffer.health.exception.GlobalException;
import com.shaffer.health.pojo.CheckItem;
import com.shaffer.health.service.CheckItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/12 21:29
 * @Author: Shaffer
 * @Description: dubbo 2.6.0
 * interfaceClass 发布出去服务的接口为这个CheckItemService.class
 * 没加interfaceClass, 调用No Provider 的异常
 */
@Service(interfaceClass = CheckItemService.class)
public class CheckItemServiceImpl implements CheckItemService {

    @Autowired
    private CheckItemDao checkItemDao;

    private static final Logger log = LoggerFactory.getLogger(CheckItemServiceImpl.class);

    @Override
    public List<CheckItem> findAll() {
        log.debug("Service：查询所有 CheckItem 数据。");
        return checkItemDao.findAll();
    }

    /**
     * 分页查询
     *
     * @return 分页结果
     */
    @Override
    public PageResult<CheckItem> findPage(QueryPageBean queryPageBean) {
        // 设置分页条件
        PageHelper.startPage(queryPageBean.getCurrentPage(), queryPageBean.getPageSize());

        String queryString = queryPageBean.getQueryString();
        // 有查询条件，拼接 SQL
        if (!StringUtils.isEmpty(queryString)) {
            queryString = "%" + queryString + "%";
        }

        // 分页查询
        Page<CheckItem> page = checkItemDao.findByCondition(queryString);

        // 封装查询结果
        return new PageResult<>(page.getTotal(), page.getResult());
    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    @Override
    public void update(CheckItem checkItem) {
        checkItemDao.update(checkItem);
    }

    @Override
    public void delete(int id) throws GlobalException {
        if (checkItemDao.findCountByCheckItemId(id) > 0) {
            throw new GlobalException(MessageConstant.CHECKITEM_IN_USE);
        }
        checkItemDao.delete(id);
    }
}
