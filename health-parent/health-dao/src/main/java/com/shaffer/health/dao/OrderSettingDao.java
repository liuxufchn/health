package com.shaffer.health.dao;

import com.shaffer.health.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: health
 * @Date: 2021/4/16 21:25
 * @Author: Shaffer
 * @Description:
 */
public interface OrderSettingDao {
    /**
     * 通过日期来查询预约设置
     * @param orderDate
     * @return
     */
    OrderSetting findByOrderDate(Date orderDate);

    /**
     * 更新可预约数量
     * @param orderSetting
     */
    void updateNumber(OrderSetting orderSetting);

    /**
     * 添加预约设置
     * @param orderSetting
     */
    void add(OrderSetting orderSetting);

    /**
     * 根据日期条件查询
     * @param date 起始日期
     * @return 预约设置信息
     */
    List<OrderSetting> getOrderSettingByMonth(String date);
}
