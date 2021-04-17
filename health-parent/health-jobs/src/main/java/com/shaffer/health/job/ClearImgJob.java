package com.shaffer.health.job;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shaffer.health.service.SetMealService;
import com.shaffer.health.utils.QiNiuUtil;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @program: health
 * @Date: 2021/4/16 19:56
 * @Author: Shaffer
 * @Description:
 */
@Component("clearImgJob")
public class ClearImgJob {

    /**
     * 订阅服务
     */
    @Reference
    private SetMealService setMealService;

    public void clearImg() {
        // 获取七牛云上所有图片
        List<String> qiNiuImgs = QiNiuUtil.listFile();
        // 获取数据库中所有图片
        List<String> dbImgs = setMealService.findImgs();
        // 做差
        qiNiuImgs.removeAll(dbImgs);
        // 删除图片
        String[] strings = qiNiuImgs.toArray(new String[]{});
        QiNiuUtil.removeFiles(strings);
    }
}
