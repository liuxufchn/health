package com.shaffer.health.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/12 20:39
 * @Author: Shaffer
 * @Description: 检查组
 */
@Data
public class CheckGroup implements Serializable {
    /**
     * 主键
     */
    private Integer id;
    /**
     * 编码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 助记
     */
    private String helpCode;
    /**
     * 适用性别
     */
    private String sex;
    /**
     * 介绍
     */
    private String remark;
    /**
     * 注意事项
     */
    private String attention;
    /**
     * 一个检查组合包含多个检查项
     */
    private List<CheckItem> checkItems;
}
