package com.shaffer.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shaffer.health.constant.MessageConstant;
import com.shaffer.health.entity.PageResult;
import com.shaffer.health.entity.QueryPageBean;
import com.shaffer.health.entity.Result;
import com.shaffer.health.pojo.CheckItem;
import com.shaffer.health.service.CheckItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
        log.debug("查询所有 CheckItem 数据接口。");
        List<CheckItem> list = checkItemService.findAll();
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, list);
    }

    @PostMapping("/findPage")
    public Result findPage(@RequestBody QueryPageBean queryPageBean) {
        PageResult<CheckItem> pageResult = checkItemService.findPage(queryPageBean);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, pageResult);
    }

    @PostMapping("/add")
    public Result add(@RequestBody CheckItem checkItem) {
        checkItemService.add(checkItem);
        return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
    }

    @GetMapping("/findById")
    public Result findById(@RequestParam Integer id) {
        CheckItem checkItem = checkItemService.findById(id);
        return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
    }

    @PostMapping("/update")
    public Result update(@RequestBody CheckItem checkItem) {
        checkItemService.update(checkItem);
        return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
    }

    @PostMapping("/delete")
    public Result delete(int id) {
        checkItemService.delete(id);
        return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
    }

}
