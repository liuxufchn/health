package com.shaffer.health.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/15 21:31
 * @Author: Shaffer
 * @Description:
 */
@Data
public class SetMeal implements Serializable {
    private Integer id;
    private String name;
    private String code;
    private String helpCode;
    /**
     * 套餐适用性别：0不限 1男 2女
     */
    private String sex;
    /**
     * 套餐适用年龄
     */
    private String age;
    /**
     * 套餐价格
     */
    private Float price;
    private String remark;
    private String attention;
    /**
     * 套餐对应图片存储路径
     */
    private String img;
    /**
     * 体检套餐对应的检查组，多对多关系
     */
    private List<CheckGroup> checkGroups;
}
