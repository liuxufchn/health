package com.shaffer.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shaffer.health.constant.MessageConstant;
import com.shaffer.health.entity.Result;
import com.shaffer.health.pojo.OrderSetting;
import com.shaffer.health.service.OrderSettingService;
import com.shaffer.health.utils.POIUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @program: health
 * @Date: 2021/4/16 21:23
 * @Author: Shaffer
 * @Description:
 */
@RestController
@RequestMapping("/order-setting")
public class OrderSettingController {

    @Reference
    private OrderSettingService orderSettingService;

    @PostMapping("/upload")
    public Result upload(MultipartFile excelFile) {
        try {
            // 读取 excel 内容
            List<String[]> excelContents = POIUtils.readExcel(excelFile);

            // 转成 List<OrderSetting>
            List<OrderSetting> orderSettings = new ArrayList<>();

            // 日期格式解析
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(POIUtils.DATE_FORMAT);

            // 将 excel 内容存入 orderSettings
            OrderSetting orderSetting = null;
            for (String[] content : excelContents) {
                orderSetting = new OrderSetting();
                orderSetting.setOrderDate(simpleDateFormat.parse(content[0]));
                orderSetting.setNumber(Integer.parseInt(content[1]));
                orderSettings.add(orderSetting);
            }

            // 调用业务服务
            orderSettingService.add(orderSettings);

            // 返回结果
            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
        }
    }

    /**
     * 根据日期获取预约设置信息
     *
     * @param month 日期格式为 yyyy-MM
     * @return 预约设置信息
     */
    @GetMapping("/getOrderSettingByMonth")
    public Result getOrderSettingByMonth(String month) {
        try {
            List<Map<String, Integer>> list = orderSettingService.getOrderSettingByMonth(month);
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, list);
        } catch (Exception e) {
            return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
        }
    }

    @PostMapping("/editNumberByDate")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
        orderSettingService.editNumberByDate(orderSetting);
        return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
    }

}
