package com.shaffer.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shaffer.health.constant.MessageConstant;
import com.shaffer.health.entity.Result;
import com.shaffer.health.pojo.CheckItem;
import com.shaffer.health.service.CheckItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/12 21:25
 * @Author: Shaffer
 * @Description:
 */
@RestController
@RequestMapping("/check-item")
public class CheckItemController {

    @Reference
    private CheckItemService checkItemService;

    private static final Logger log = LoggerFactory.getLogger(CheckItemController.class);

    @GetMapping("/findAll")
    public Result findAll() {
        log.debug("进入 controller.findAll 方法++++++++++++++++++++++++++++++++++++++++++++++++++");
        List<CheckItem> list = checkItemService.findAll();
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, list);
    }

}
