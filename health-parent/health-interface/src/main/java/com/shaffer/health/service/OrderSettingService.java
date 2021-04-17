package com.shaffer.health.service;

import com.shaffer.health.exception.GlobalException;
import com.shaffer.health.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @program: health
 * @Date: 2021/4/16 21:24
 * @Author: Shaffer
 * @Description:
 */
public interface OrderSettingService {
    /**
     * 批量导入
     *
     * @param orderSettingList 预约设置列表
     */
    void add(List<OrderSetting> orderSettingList) throws GlobalException;


    /**
     * 根据日期获取预约设置信息
     *
     * @param date 日期格式为 yyyy-MM
     * @return 预约设置信息
     */
    List<Map<String, Integer>> getOrderSettingByMonth(String date);

    /**
     * 预约设置
     * @param orderSetting 预约设置信息
     */
    void editNumberByDate(OrderSetting orderSetting);
}
