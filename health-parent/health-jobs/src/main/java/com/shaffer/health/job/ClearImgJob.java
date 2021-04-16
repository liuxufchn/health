package com.shaffer.health.job;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shaffer.health.service.SetMealService;

/**
 * @program: health
 * @Date: 2021/4/16 19:56
 * @Author: Shaffer
 * @Description:
 */
public class ClearImgJob {

    @Reference
    private SetMealService setMealService;

    public void clearImg() {
        // 获取七牛云
    }
}
