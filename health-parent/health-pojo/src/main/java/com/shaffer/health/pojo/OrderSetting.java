package com.shaffer.health.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @program: health
 * @Date: 2021/4/16 21:50
 * @Author: Shaffer
 * @Description: 预约设置
 */
@Data
public class OrderSetting implements Serializable {
    private Integer id ;
    /**
     * 预约设置日期
     */
    private Date orderDate;
    /**
     * 可预约人数
     */
    private int number;
    /**
     * 已预约人数
     */
    private int reservations ;
}
