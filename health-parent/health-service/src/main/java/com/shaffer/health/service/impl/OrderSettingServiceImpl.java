package com.shaffer.health.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.shaffer.health.dao.OrderSettingDao;
import com.shaffer.health.exception.GlobalException;
import com.shaffer.health.pojo.OrderSetting;
import com.shaffer.health.service.OrderSettingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * @program: health
 * @Date: 2021/4/16 21:24
 * @Author: Shaffer
 * @Description:
 */
@Service(interfaceClass = OrderSettingService.class)
public class OrderSettingServiceImpl implements OrderSettingService {

    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void add(List<OrderSetting> orderSettingList) throws GlobalException {
        // 遍历
        for (OrderSetting orderSetting : orderSettingList) {
            editOrderSetting(orderSetting);
        }
    }



    @Override
    public List<Map<String, Integer>> getOrderSettingByMonth(String date) {
        // 拼接日期
        date = date + "-%";

        // 查询数据
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(date);

        // 将list组织为map列表
        List<Map<String, Integer>> result = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        for (OrderSetting orderSetting : list) {
            // { date: 1, number: 120, reservations: 1 }
            calendar.setTime(orderSetting.getOrderDate());
            Map<String, Integer> orderSettingMap = new HashMap<>(3);
            orderSettingMap.put("date", calendar.get(Calendar.DAY_OF_MONTH));
            orderSettingMap.put("number", orderSetting.getNumber());
            orderSettingMap.put("reservations", orderSetting.getReservations());
            result.add(orderSettingMap);
        }
        return result;
    }

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        editOrderSetting(orderSetting);
    }

    private void editOrderSetting(OrderSetting orderSetting) {
        // 判断是否存在, 通过日期来查询, 注意：日期里是否有时分秒，数据库里的日期是没有时分秒的
        OrderSetting orderSettingInDb = orderSettingDao.findByOrderDate(orderSetting.getOrderDate());
        if (null != orderSettingInDb) {
            // 数据库存在这个预约设置, 已预约数量不能大于可预约数量
            if (orderSettingInDb.getReservations() > orderSetting.getNumber()) {
                // 报错
                throw new GlobalException(orderSetting.getOrderDate() + "中已预约数量大于可预约数量");
            }
            orderSettingDao.updateNumber(orderSetting);
        }
        // 数据库中不存在该日期的记录，直接添加
        else {
            orderSettingDao.add(orderSetting);
        }
    }
}
