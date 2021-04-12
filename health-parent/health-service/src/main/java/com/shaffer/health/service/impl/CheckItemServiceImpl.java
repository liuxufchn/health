package com.shaffer.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shaffer.health.dao.CheckItemDao;
import com.shaffer.health.pojo.CheckItem;
import com.shaffer.health.service.CheckItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
        log.debug("进入service.findAll方法。+++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        return checkItemDao.findAll();
    }
}
