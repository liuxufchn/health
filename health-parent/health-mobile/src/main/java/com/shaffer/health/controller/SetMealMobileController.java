package com.shaffer.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shaffer.health.constant.MessageConstant;
import com.shaffer.health.entity.Result;
import com.shaffer.health.pojo.SetMeal;
import com.shaffer.health.service.SetMealService;
import com.shaffer.health.utils.QiNiuUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/27 14:29
 * @Author: Shaffer
 * @Description:
 */
@RestController
@RequestMapping("/set-meal")
public class SetMealMobileController {

    @Reference
    private SetMealService setMealService;

    @GetMapping("/getSetmeal")
    public Result getSetmeal() {
        // 查询所有套餐
        List<SetMeal> list = setMealService.findAll();
        // 拼接图片路径
        list.forEach(setMeal -> {
            setMeal.setImg(QiNiuUtil.DOMAIN + setMeal.getImg());
        });
        return new Result(true, MessageConstant.GET_SETMEAL_LIST_SUCCESS, list);
    }

    @GetMapping("/findDetailById")
    public Result findDetailById(int id) {
        SetMeal setMeal = setMealService.findDetailById(id);
        // 设置图片的完整路径
        setMeal.setImg(QiNiuUtil.DOMAIN + setMeal.getImg());
        return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, setMeal);
    }
}
